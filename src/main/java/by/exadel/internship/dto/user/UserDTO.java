package by.exadel.internship.dto.user;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private UserRole userRole;
    private List<Skill> skills;

//    private List<UserInternshipDTO> listOfInternships;
//    private List<LocalDateTime> freeInterviewDates;
}