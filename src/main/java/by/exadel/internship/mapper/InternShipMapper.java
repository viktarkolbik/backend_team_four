package by.exadel.internship.mapper;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternShipMapper {

    GuestInternshipDTO toGuestInternshipDTO(Internship internship);

    Internship toInternship(GuestInternshipDTO guestInternshipDTO);

    UserInternshipDTO toUserInternshipDTO(Internship internship);

    Internship toInternship(UserInternshipDTO userInternshipDTO);


}
