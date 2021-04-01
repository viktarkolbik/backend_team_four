package by.exadel.internship.dto.internshipDTO;


import by.exadel.internship.dto.FormDTO;
import by.exadel.internship.dto.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInternshipDTO extends GuestInternshipDTO {

    private List<FormDTO> formList;
    private List<UserDTO> techList;
    private List<UserDTO> adminList;

}
