package by.exadel.internship.repository;

import by.exadel.internship.entity.UserTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTimeSlotRepository extends JpaRepository<UserTimeSlot, UUID> {
}
