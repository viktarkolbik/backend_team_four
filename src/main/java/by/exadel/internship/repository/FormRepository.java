package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Internship;
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

    @Query("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.timeForCallList")
    Optional<Form> findByIdAndDeletedTrue(UUID formId);

    Optional<Form> findByIdAndDeletedFalse(UUID formId);

    List<Form> findAllByDeletedFalse();

    List<Form> findAllByDeletedTrue();

    @Query("SELECT distinct f FROM Form f LEFT JOIN FETCH f.timeForCallList WHERE f.deleted = false")
    List<Form> findAllWithTimeForCallList();

    @Query("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.timeForCallList WHERE f.internshipId = :id")
    List<Form> findAllByInternship(@Param("id") UUID internshipID);

    @Modifying
    @Query(value = "UPDATE Form f SET f.deleted=true WHERE f.id= :formId")
    void deleteById(@Param("formId") UUID formId);

}