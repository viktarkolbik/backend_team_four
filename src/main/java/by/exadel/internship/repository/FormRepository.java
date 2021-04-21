package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Query("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.timeForCallList")
    List<Form> findAllWithTimeForCallList();

    @Query("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.timeForCallList WHERE f.internshipId = :id")
    List<Form> findAllByInternship(@Param("id") UUID internshipID);

}