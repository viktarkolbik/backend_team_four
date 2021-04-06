package by.exadel.internship.dto.internshipDTO;


import by.exadel.internship.dto.userDTO.UserDTO;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInternshipDTO extends GuestInternshipDTO {

    private List<FormFullDTO> formList;
    private List<UserDTO> techList;
    private List<UserDTO> adminList;

}
