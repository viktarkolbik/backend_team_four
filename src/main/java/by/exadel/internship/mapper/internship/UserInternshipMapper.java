package by.exadel.internship.mapper.internship;

import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface UserInternshipMapper {

    @Named(value = "internship")
    UserInternshipDTO toUserInternshipDTO(Internship internship);

    Internship toInternship(UserInternshipDTO userInternshipDTO);

    @IterableMapping(qualifiedByName = "internship")
    List<UserInternshipDTO> map(List<Internship> internshipList);

}
