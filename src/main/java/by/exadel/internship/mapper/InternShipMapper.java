package by.exadel.internship.mapper;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternShipMapper {

    @Named(value = "intern")
    GuestInternshipDTO toGuestInternshipDTO(Internship internship);

    Internship toInternship(GuestInternshipDTO guestInternshipDTO);

    UserInternshipDTO toUserInternshipDTO(Internship internship);

    Internship toInternship(UserInternshipDTO userInternshipDTO);

    @IterableMapping(qualifiedByName = "intern")
    List<GuestInternshipDTO> map(List<Internship> internshipList);


}
