package by.exadel.internship.mapper.internship;

import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface InternshipMapper {

    @Named(value = "internshipFull")
    @Mapping(source = "locationList", target = "locations")
    GuestInternshipDTO toGuestInternshipDTO(Internship internship);

    Internship toInternship(GuestInternshipDTO guestInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipFull")
    List<GuestInternshipDTO> mapGuestInternshipDTOList(List<Internship> internshipList);

    @Named(value = "internshipShort")
    @Mapping(source = "locationList", target = "locations")
    BaseInternshipDTO toBaseInternshipDTO(Internship internship);

    Internship toInternship(BaseInternshipDTO baseInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipShort")
    List<BaseInternshipDTO> mapBaseInternshipDTOList(List<Internship> internshipList);

    @Named(value = "internship")
    @Mapping(source = "locationList", target = "locations")
    UserInternshipDTO toUserInternshipDTO(Internship internship);

    Internship toInternship(UserInternshipDTO userInternshipDTO);

    @IterableMapping(qualifiedByName = "internship")
    List<UserInternshipDTO> mapUserInternshipDTOList(List<Internship> internshipList);

}
