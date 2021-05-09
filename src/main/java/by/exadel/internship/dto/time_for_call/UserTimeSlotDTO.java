package by.exadel.internship.dto.time_for_call;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTimeSlotDTO {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean roundUp;
}
