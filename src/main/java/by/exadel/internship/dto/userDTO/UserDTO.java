package by.exadel.internship.dto.userDTO;


import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import by.exadel.internship.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
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
//    private List<UserInternshipDTO> listOfInternships;
//    private List<LocalDateTime> freeInterviewDates;
}