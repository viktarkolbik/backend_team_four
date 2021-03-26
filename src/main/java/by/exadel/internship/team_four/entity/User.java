package by.exadel.internship.team_four.entity;

import by.exadel.internship.team_four.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //Автоинкрементация? ограничения на id аннотациями?
    private UUID id;

    @NotEmpty(message = "Lastname should be not empty")
    @Size(min = 1, max = 30, message = "Lastname should be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Email should be not valid")
    @Email(message = "Email should be valid")
    private String email;

    private String login;

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, message = "Password should be greater than 6 characters")
    private String password;


    //Role of user
    private UserRole userRole;

    private List<Internship> listOfInternships;
    private List<LocalDateTime> freeInterviewDates;
    private List<LocalDateTime> scheduledInterviewDates;

}
