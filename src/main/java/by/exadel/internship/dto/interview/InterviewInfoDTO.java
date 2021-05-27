package by.exadel.internship.dto.interview;

import by.exadel.internship.dto.user.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewInfoDTO {
    private UUID id;
    private UserInfoDTO admin;
    private LocalDateTime adminInterviewDate;
    private String adminFeedback;
    private UserInfoDTO techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;
}
