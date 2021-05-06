package by.exadel.internship.dto.form;

import by.exadel.internship.dto.interview.InterviewDTO;
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
    private FormStatus formStatus;
    private InterviewDTO interview;
    private String primarySkill;
}
