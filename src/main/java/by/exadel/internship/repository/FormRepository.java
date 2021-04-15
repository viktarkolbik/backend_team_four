package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {
    @Modifying
    @Query("UPDATE Form f SET f.deleted = false WHERE f.id = :formId")
    void activateFormById(UUID formId);

    Optional<Form> findByIdAndDeletedTrue(UUID formId);
    Optional<Form> findByIdAndDeletedFalse(UUID formId);
    List<Form> findAllByDeletedFalse();
    List<Form> findAllByDeletedTrue();

}