package by.exadel.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeForCallWithUserDTO {
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private UserDTO user;
}
