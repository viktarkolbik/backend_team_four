package by.exadel.internship.repository;

import by.exadel.internship.entity.UserTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTimeSlotRepository extends JpaRepository<UserTimeSlot, UUID> {
}
