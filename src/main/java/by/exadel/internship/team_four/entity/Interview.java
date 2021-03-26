package by.exadel.internship.team_four.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    private UUID id;
    private User admin;
    private LocalDateTime adminInterviewDate;
    private String feedback;
    private User techSpecialist;
    private LocalDateTime techInterviewDate;
    private String techFeedback;

}
