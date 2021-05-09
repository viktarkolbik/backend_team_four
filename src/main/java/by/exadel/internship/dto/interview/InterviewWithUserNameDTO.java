package by.exadel.internship.dto.interview;


import by.exadel.internship.dto.user.UserInfoDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewWithUserNameDTO extends InterviewDTO {
    private UserInfoDTO adminUser;
    private UserInfoDTO techSpecialistUser;

}
