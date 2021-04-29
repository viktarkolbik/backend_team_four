package by.exadel.internship.mapper;


import by.exadel.internship.dto.internship.ShortInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)

public interface ShortInternshipMapper {

    @Named(value = "internship")
    @Mapping(source = "locationList", target = "locations")
    ShortInternshipDTO toGuestInternshipDTO(Internship internship);

    Internship toInternship(ShortInternshipDTO shortInternshipDTO);

    @IterableMapping(qualifiedByName = "internship")
    List<ShortInternshipDTO> map(List<Internship> internshipList);

}
