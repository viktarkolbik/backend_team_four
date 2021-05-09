package by.exadel.internship.dto.interview;

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
    private UUID admin;
    private LocalDateTime adminInterviewDate;
    private String adminFeedback;
    private UUID techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;

}
