package by.exadel.internship.mapper.internship;

import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface InternshipMapper {

    @Named(value = "internshipFull")
    @Mapping(source = "locationList", target = "locations")
    GuestInternshipDTO toGuestInternshipDTO(Internship internship);

    @Named(value = "guestInternshipDTO_ToInternship")
    @Mapping(source = "locations", target = "locationList")
    Internship toInternship(GuestInternshipDTO guestInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipFull")
    List<GuestInternshipDTO> mapGuestInternshipDTOList(List<Internship> internshipList);

    @Named(value = "internshipShort")
    @Mapping(source = "locationList", target = "locations")
    BaseInternshipDTO toBaseInternshipDTO(Internship internship);

    @Named(value = "baseInternshipDTO_ToInternship")
    @Mapping(source = "locations", target = "locationList")
    Internship toInternship(BaseInternshipDTO baseInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipShort")
    List<BaseInternshipDTO> mapBaseInternshipDTOList(List<Internship> internshipList);

    @Named(value = "internship")
    @Mapping(source = "locationList", target = "locations")
    UserInternshipDTO toUserInternshipDTO(Internship internship);

    @Named(value = "userInternshipDTO_ToInternship")
    @Mapping(source = "locations", target = "locationList")
    Internship toInternship(UserInternshipDTO userInternshipDTO);

    @IterableMapping(qualifiedByName = "internship")
    List<UserInternshipDTO> mapUserInternshipDTOList(List<Internship> internshipList);

    @Named(value = "updateInternship")
    @Mapping(source = "locations", target = "locationList")
    Internship updateInternship(UserInternshipDTO userInternshipDTO, @MappingTarget Internship internship);

}
