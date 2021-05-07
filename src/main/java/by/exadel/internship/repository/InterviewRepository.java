package by.exadel.internship.repository;

import by.exadel.internship.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID> {
    List<Interview> findAllByAdmin(UUID adminId);
    List<Interview> findAllByTechSpecialist(UUID techID);
}
