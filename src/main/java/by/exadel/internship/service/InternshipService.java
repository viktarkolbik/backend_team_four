package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception.NotFoundException;
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

    private static final Logger infoLogger =
            LoggerFactory.getLogger("by.exadel.service.info");
    private static final Logger warningLogger =
            LoggerFactory.getLogger("by.exadel.service.warning");
    private static final Logger errorLogger =
            LoggerFactory.getLogger("by.exadel.service.error");

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internShipMapper;

    public GuestInternshipDTO getById(UUID uuid) {
        infoLogger.info("Try to get Internships with uuid=" + uuid, InternshipService.class.getSimpleName());
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format("Internship with id %s was not found!", uuid)));
        infoLogger.info("Successfully got Internships with uuid=" + uuid, InternshipService.class.getSimpleName());
        return internShipMapper.toGuestInternshipDTO(internship);

    }

    public List<GuestInternshipDTO> getAll() {
        infoLogger.info("Try to get all Internships", InternshipService.class.getSimpleName());
        List<Internship> internships = internshipRepository.findAll();
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        infoLogger.info("Successfully list of Internships", InternshipService.class.getSimpleName());
        return guestInternshipDTOList;

    }
}
