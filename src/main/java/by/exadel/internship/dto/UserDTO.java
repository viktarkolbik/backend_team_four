package by.exadel.internship.dto;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.timeForCall.UserTimeSlotDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
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
    private int interviewTime;
    private Set<Skill> skills;
    private List<UserTimeSlotDTO> userTimeSlots;
}