package by.exadel.internship.pojo;

import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String login;
    private String email;
    private List<String> role;

    public JwtResponse(String token, UUID id, String login, String email, List<String> role) {
        this.token = token;
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
    }
}