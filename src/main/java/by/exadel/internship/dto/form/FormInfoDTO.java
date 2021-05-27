package by.exadel.internship.dto.form;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.interview.InterviewInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormInfoDTO extends FormRegisterDTO {

    private UUID id;
    private FormStatus formStatus;
    private InterviewInfoDTO interview;
    private String primarySkill;
}
