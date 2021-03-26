package by.exadel.internship.team_four.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    private User admin;
    private Date InterviewDate;
    private String feedback;
    private User techSpecialist;
    private Date techInterviewDate;
    private String techFeedback;
}
