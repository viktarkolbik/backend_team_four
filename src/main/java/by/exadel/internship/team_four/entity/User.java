package by.exadel.internship.team_four.entity;

import by.exadel.internship.team_four.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //Автоинкрементация? ограничения на id аннотациями?
    private Long id;
    @NotEmpty(message = "Lastname should be not empty")
    @Size(min = 1, max = 30, message = "Lastname should be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Email should be not valid")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, message = "Password should be greater than 6 characters")
    private String password;

    private UserStatus userStatus;

    private List<Internship> listOfInternships;
    private List<Calendar> freeInterviewDates;
    private List<Calendar> unfreeInterviewDates;

}
