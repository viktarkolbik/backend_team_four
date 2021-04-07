package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
        MDC.clear();
        MDC.put("className", InternshipService.class.getSimpleName());
        logger.info("Try to get Internships with uuid=" + uuid);
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = "+ uuid + " in DB"));
        logger.info("Successfully got Internships with uuid=" + uuid);
        return internShipMapper.toGuestInternshipDTO(internship);
    }

    public List<GuestInternshipDTO> getAll() {
        MDC.clear();
        MDC.put("className", InternshipService.class.getSimpleName());
        logger.info("Try to get all Internships");
        List<Internship> internships = internshipRepository.findAll();
        if (internships.isEmpty()){
            throw new NotFoundException("List of Internships is Empty");
        }
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        logger.info("Successfully list of Internships");
        return guestInternshipDTOList;

    }
}
