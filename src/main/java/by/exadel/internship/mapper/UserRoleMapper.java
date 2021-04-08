package by.exadel.internship.mapper;
import by.exadel.internship.dto.formDTO.UserRoleDTO;
import by.exadel.internship.entity.user.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO toUserDTO(UserRole userRole);
    UserRole toUserRole(UserRoleDTO userRoleDTO);
}
