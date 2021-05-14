package by.exadel.internship.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFullDTO extends UserDTO{
    private String login;
    private String password;
}
