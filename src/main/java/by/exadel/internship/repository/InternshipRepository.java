package by.exadel.internship.repository;

import by.exadel.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, UUID> {

    @Query(value = "SELECT * FROM Internship WHERE inship_deleted = true",
            nativeQuery = true)
    List<Internship> findAllDeleted();

    @Query(value = "SELECT * FROM Internship WHERE inship_deleted = true AND inship_id = :internshipId",
            nativeQuery = true)
    Internship findDeletedById(@Param("internshipId") UUID internshipId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE internship SET inship_deleted = false WHERE inship_id = :internshipId",
            nativeQuery = true)
    void updateDeletedById(@Param("internshipId") UUID internshipId);
}
