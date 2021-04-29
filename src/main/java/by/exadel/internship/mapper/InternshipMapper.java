package by.exadel.internship.mapper;


import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)

public interface InternshipMapper {

    @Named(value = "internship")
    @Mapping(source = "locationList", target = "locations")
    GuestInternshipDTO toGuestInternshipDTO(Internship internship);

    Internship toInternship(GuestInternshipDTO guestInternshipDTO);

    @IterableMapping(qualifiedByName = "internship")
    List<GuestInternshipDTO> map(List<Internship> internshipList);

}
