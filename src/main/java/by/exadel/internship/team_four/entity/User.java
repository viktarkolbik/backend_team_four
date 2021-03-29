package by.exadel.internship.team_four.entity;

import by.exadel.internship.team_four.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String lastName;
    private String name;
    private String email;
    private String login;
    private String password;
    private UserRole userRole;
    private List<Internship> listOfInternships;
    private List<LocalDateTime> freeInterviewDates;


}
