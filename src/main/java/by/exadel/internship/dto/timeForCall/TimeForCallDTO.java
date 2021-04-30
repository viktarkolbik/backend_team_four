package by.exadel.internship.dto.timeForCall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeForCallDTO {

    private int startHour;
    private int endHour;

}
