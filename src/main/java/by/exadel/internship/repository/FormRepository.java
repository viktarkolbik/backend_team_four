package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE form SET fm_deleted = false WHERE fm_id = :formId",
            nativeQuery = true)
    void updateDeletedById(UUID formId);

    @Query(value = "SELECT * FROM form WHERE fm_deleted = true AND fm_id = :formId",
            nativeQuery = true)
    Optional<Form> findDeletedById(@Param("formId") UUID formId);

}