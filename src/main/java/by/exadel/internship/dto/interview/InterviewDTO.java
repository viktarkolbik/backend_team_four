package by.exadel.internship.dto.interview;

import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.dto.user.UserFullDTO;
import by.exadel.internship.dto.user.UserInfoDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {

    private UUID id;
    private UserFullDTO admin;
    private LocalDateTime adminInterviewDate;
    private String adminFeedback;
    private UserFullDTO techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;

}
