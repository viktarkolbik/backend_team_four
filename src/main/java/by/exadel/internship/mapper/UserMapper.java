package by.exadel.internship.mapper;

import by.exadel.internship.dto.userDTO.TechUserDTO;
import by.exadel.internship.dto.userDTO.UserDTO;
import by.exadel.internship.entity.TechUser;
import by.exadel.internship.entity.User;
import jdk.jfr.Name;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Name(value = "user")
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    @IterableMapping(qualifiedByName = "techUser")
    TechUserDTO toTechUserDTO(User user);

    User toTechUser(TechUserDTO user);

    @IterableMapping(qualifiedByName = "user")
    List<UserDTO> map(List<User> userList);
    @IterableMapping(qualifiedByName = "techUser")
    List<TechUserDTO> mapToTech(List<TechUser> techUserList);
}
