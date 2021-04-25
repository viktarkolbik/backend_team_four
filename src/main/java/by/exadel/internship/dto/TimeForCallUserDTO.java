package by.exadel.internship.dto;

import by.exadel.internship.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeForCallUserDTO {
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private User user;
}
