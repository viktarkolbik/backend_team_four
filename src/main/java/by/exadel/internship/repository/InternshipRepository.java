package by.exadel.internship.repository;

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
public interface InternshipRepository extends JpaRepository<Internship, UUID> {

    @Query("SELECT DISTINCT i FROM Internship i LEFT JOIN FETCH i.skills  WHERE i.deleted = true")
    List<Internship> findAllByDeletedTrue();

    List<Internship> findAllByDeletedFalse();

    Optional<Internship> findByIdAndDeletedFalse(UUID internshipId);

    Optional<Internship> findByIdAndDeletedTrue(UUID internshipId);

    @Modifying
    @Query("update Internship i set i.deleted = false WHERE i.id = :internshipId")
    void activateInternshipById(@Param("internshipId") UUID internshipId);

    @Query("SELECT distinct i FROM Internship i LEFT JOIN FETCH i.skills WHERE i.deleted = false")
    List<Internship> findAllWithSkill();

    @Modifying
    @Query(value = "UPDATE Internship i SET i.deleted=true WHERE i.id= :internshipId")
    void deleteById(@Param("internshipId") UUID internshipId);
}
