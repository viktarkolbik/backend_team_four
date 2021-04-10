package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.InterviewDTO;
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

}
