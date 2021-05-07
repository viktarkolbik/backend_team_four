package by.exadel.internship.dto;

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
    private UUID id;

    public JwtResponse(String token, UUID id) {
        this.token = token;
        this.id = id;
    }
}
