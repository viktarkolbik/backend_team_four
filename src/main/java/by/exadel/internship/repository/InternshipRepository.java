package by.exadel.internship.repository;

import by.exadel.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InternshipRepository extends JpaRepository<Internship, UUID> {
}
