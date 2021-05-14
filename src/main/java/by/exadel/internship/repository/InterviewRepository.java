package by.exadel.internship.repository;

import by.exadel.internship.entity.Interview;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, UUID> {
    List<Interview> findAllByAdmin(User admin);
    List<Interview> findAllByTechSpecialist(User tech);
}
