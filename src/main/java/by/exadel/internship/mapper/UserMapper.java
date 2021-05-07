package by.exadel.internship.mapper;

import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.dto.user.UserInfoDTO;
import by.exadel.internship.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named(value = "user")
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    @IterableMapping(qualifiedByName = "user")
    List<UserDTO> map(List<User> userList);

    @Named(value = "userInfo")
    UserInfoDTO toUserInfo(User user);

}
