package by.exadel.internship.repository;

import by.exadel.internship.entity.TimeForCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TimeForCallRepository extends JpaRepository<TimeForCall, UUID> {
    Optional<TimeForCall> findByFormId(UUID formId);
}
