package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private static final Logger logger =
            LoggerFactory.getLogger("by.exadel.service");

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internShipMapper;

    public GuestInternshipDTO getById(UUID uuid) {
        logger.info("Try to get Internships with uuid=" + uuid, InternshipService.class.getSimpleName());
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = "+ uuid + " in DB",
                        InternshipService.class.getSimpleName()));
        logger.info("Successfully got Internships with uuid=" + uuid, InternshipService.class.getSimpleName());
        return internShipMapper.toGuestInternshipDTO(internship);
    }

    public List<GuestInternshipDTO> getAll() {
        logger.info("Try to get all Internships", InternshipService.class.getSimpleName());
        List<Internship> internships = internshipRepository.findAll();
        if (internships.isEmpty()){
            throw new NotFoundException("List of Internships is Empty", InternshipService.class.getSimpleName());
        }
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        logger.info("Successfully list of Internships", InternshipService.class.getSimpleName());
        return guestInternshipDTOList;

    }
}
