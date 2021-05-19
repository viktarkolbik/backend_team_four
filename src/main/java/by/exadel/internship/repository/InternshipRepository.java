package by.exadel.internship.repository;

import by.exadel.internship.entity.Internship;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, UUID> {


    @EntityGraph(attributePaths = {"skills", "locationList.country", "locationList.city"})
    @Query("SELECT DISTINCT i FROM Internship i WHERE i.deleted = true")
    List<Internship> findAllByDeletedTrue();

    List<Internship> findAllByDeletedFalse();

    @EntityGraph(attributePaths = {"skills", "locationList.country", "locationList.city"})
    @Query("SELECT DISTINCT i FROM Internship i WHERE i.id = :id ")
    Optional<Internship> findByIdAndDeletedFalse(@Param("id")UUID internshipId);

    @EntityGraph(attributePaths = {"skills", "locationList.country", "locationList.city"})
    Optional<Internship> findByIdAndDeletedTrue(UUID internshipId);

    @Modifying
    @Query("update Internship i set i.deleted = false WHERE i.id = :internshipId")
    void activateInternshipById(@Param("internshipId") UUID internshipId);

    @EntityGraph(attributePaths = {"skills", "locationList.country", "locationList.city"})
    @Query("SELECT distinct i FROM Internship i WHERE i.deleted = false")
    List<Internship> findAllWithSkill();

    @Modifying
    @Query(value = "UPDATE Internship i SET i.deleted=true WHERE i.id= :internshipId")
    void deleteById(@Param("internshipId") UUID internshipId);

    @Modifying
    @Query(value = "INSERT INTO user_internship(ui_u_id, ui_inship_id) VALUES(:user , :internship)", nativeQuery = true)
    void addUserToInternship(@Param("user") UUID userId, @Param("internship") UUID internshipId);

    @Query(value = "SELECT count(*) FROM user_internship WHERE ui_inship_id = :internship AND ui_u_id = :user", nativeQuery = true)
    int checkUserAssign(@Param("user") UUID userId, @Param("internship") UUID internshipId);
}
