package by.exadel.internship.dto.user;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechUserDTO extends UserDTO {

    private Technology techTechnology;
}