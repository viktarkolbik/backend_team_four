package by.exadel.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    private UUID id;
    private User admin;
    private LocalDateTime adminInterviewDate;
    private String adminFeedback;
    private User techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;

}
