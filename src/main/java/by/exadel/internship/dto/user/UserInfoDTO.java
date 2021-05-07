package by.exadel.internship.dto.user;

import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private UUID id;
    private String lastName;
    private String firstName;
    private UserRole userRole;
}
