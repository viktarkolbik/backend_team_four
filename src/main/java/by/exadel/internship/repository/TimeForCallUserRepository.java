package by.exadel.internship.repository;

import by.exadel.internship.entity.TimeForCallUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeForCallUserRepository extends JpaRepository<TimeForCallUser, UUID> {
}
