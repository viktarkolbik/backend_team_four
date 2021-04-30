package by.exadel.internship.mapper.internship;

import by.exadel.internship.dto.internship.GuestFullInternshipDTO;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface GuestInternshipMapper {

    @Named(value = "internshipFull")
    @Mapping(source = "locationList", target = "locations")
    GuestFullInternshipDTO toGuestFullInternshipDTO(Internship internship);

    Internship toInternship(GuestFullInternshipDTO guestShortInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipFull")
    List<GuestFullInternshipDTO> mapGuestFullInternshipDTOList(List<Internship> internshipList);

    @Named(value = "internshipShort")
    @Mapping(source = "locationList", target = "locations")
    GuestShortInternshipDTO toGuestShortInternshipDTO(Internship internship);

    Internship toInternship(GuestShortInternshipDTO guestShortInternshipDTO);

    @IterableMapping(qualifiedByName = "internshipShort")
    List<GuestShortInternshipDTO> mapGuestShortInternshipDTOList(List<Internship> internshipList);

}
