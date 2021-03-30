package by.exadel.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {

    private UUID id;
    private UserDTO admin;
    private LocalDateTime adminInterviewDate;
    private String adminFeedback;
    private UserDTO techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;

}
