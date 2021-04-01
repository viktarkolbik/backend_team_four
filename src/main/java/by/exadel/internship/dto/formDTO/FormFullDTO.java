package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.enums.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormFullDTO extends FormRegisterDTO {

    private UUID id;
    private InterviewDTO interview;
    private FormStatus formStatus;
//    this field is questionable
    private TimeForCallDTO timeForCall;
}
