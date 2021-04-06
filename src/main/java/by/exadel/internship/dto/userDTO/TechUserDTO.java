package by.exadel.internship.dto.userDTO;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TechUserDTO extends UserDTO {

    private Technology techTechnology;
}