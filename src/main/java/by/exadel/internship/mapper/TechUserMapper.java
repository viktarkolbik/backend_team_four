package by.exadel.internship.mapper;
import by.exadel.internship.dto.userDTO.TechUserDTO;
import by.exadel.internship.entity.user.TechUser;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TechUserMapper {

    @Named(value = "techUser")
    TechUserDTO toTechUserDTO(TechUser techUser);

    TechUser toTechUser(TechUserDTO user);

    @IterableMapping(qualifiedByName = "techUser")
    List<TechUserDTO> map(List<TechUser> techUserList);
}
