package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE form SET fm_deleted = false WHERE fm_id = :formId",
            nativeQuery = true)
    void updateDeletedById(UUID formId);

}