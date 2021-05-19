package by.exadel.internship.repository;

import by.exadel.internship.entity.UserTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserTimeSlotRepository extends JpaRepository<UserTimeSlot, UUID> {

    @Query(value = "SELECT ts.* FROM user_time_slot ts WHERE CAST (ts.ust_start_date as TIMESTAMP) >= current_timestamp AND ts.ust_u_id = :userId", nativeQuery = true)
    List<UserTimeSlot> getUserWithCurrentTimeSlot(@Param("userId") UUID userId);

}
