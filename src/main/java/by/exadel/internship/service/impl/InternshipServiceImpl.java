package by.exadel.internship.service.impl;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import by.exadel.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper mapper;

    public GuestInternshipDTO getById(UUID id) {

        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get Internship with id= {}", id);

        Internship internship = internshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such Internship with id = " + id + " in DB", "id.invalid"));

        log.info("Successfully got Internship with id= {}", id);

        return mapper.toGuestInternshipDTO(internship);

    }

    public List<GuestInternshipDTO> getAll() {

        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get all Internships with skills");

        List<Internship> internships = internshipRepository.findAllWithSkill();

        log.info("Try to get all Internships like guestInternshipDTOs  with skills");

        List<GuestInternshipDTO> guestInternshipDTOList = mapper.map(internships);

        log.info("Successfully list of guestInternshipDTOs with skills");

        return guestInternshipDTOList;
    }
}
