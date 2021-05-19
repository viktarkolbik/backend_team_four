package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Modifying
    @Query("UPDATE Form f SET f.deleted = false WHERE f.id = :formId")
    void activateFormById(@Param("formId") UUID formId);

    @EntityGraph(attributePaths = {"timeForCallList", "country", "city", "interview"})
    @Query("SELECT DISTINCT f FROM Form f WHERE f.deleted = true AND f.id = :id")
    Optional<Form> findByIdAndDeletedTrue(@Param("id") UUID formId);

    @EntityGraph(attributePaths = {"timeForCallList", "interview.admin.skills",
            "interview.techSpecialist.skills","interview.techSpecialist.userTimeSlots",
            "interview.admin.userTimeSlots" , "country", "city"})
    Optional<Form> findByIdAndDeletedFalse(UUID formId);

    List<Form> findAllByDeletedFalse();

    List<Form> findAllByDeletedTrue();

    @EntityGraph(attributePaths = {"timeForCallList", "interview.admin.skills",
            "interview.techSpecialist.skills","interview.techSpecialist.userTimeSlots",
            "interview.admin.userTimeSlots" , "country", "city"})
    @Query("SELECT distinct f FROM Form f WHERE f.deleted = false")
    List<Form> findAllWithTimeForCallList();

    @EntityGraph(attributePaths = {"timeForCallList", "interview.admin.skills",
            "interview.techSpecialist.skills","interview.techSpecialist.userTimeSlots",
            "interview.admin.userTimeSlots" , "country", "city"})
    @Query("SELECT DISTINCT f FROM Form f WHERE f.internshipId = :id" +
            " AND f.deleted = false")
    List<Form> findAllByInternship(@Param("id") UUID internshipID);

    @Modifying
    @Query(value = "UPDATE Form f SET f.deleted=true WHERE f.id= :formId")
    void deleteById(@Param("formId") UUID formId);

    @EntityGraph(attributePaths = {"timeForCallList", "interview.admin.skills",
            "interview.techSpecialist.skills","interview.techSpecialist.userTimeSlots",
            "interview.admin.userTimeSlots" , "country", "city"})
    @Query("select distinct f from Form f " +
            " where f.interview.admin.id = :userId or f.interview.techSpecialist.id = :userId")
    List<Form> findAllByUserId(@Param("userId") UUID userId);

}