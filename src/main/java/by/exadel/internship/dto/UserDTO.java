package by.exadel.internship.dto;

import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
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
    private List<UserInternshipDTO> listOfInternships;
    private List<LocalDateTime> freeInterviewDates;

}
