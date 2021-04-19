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

import javax.transaction.Transactional;
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

    public List<GuestInternshipDTO> getAllDeleted() {
        putClassNameInMDC();
        log.info("Try to get deleted Internships");
        List<Internship> deletedInternships = internshipRepository.findAllByDeletedTrue();
        List<GuestInternshipDTO> guestDeletedInternshipDTOList = mapper.map(deletedInternships);
        log.info("Successfully got list of deleted Internships");
        return guestDeletedInternshipDTOList;
    }

    public GuestInternshipDTO getDeletedInternshipById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to get deleted Internships with uuid= " + uuid);
        Internship deletedInternship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));
        log.info("Successfully got deleted Internships with uuid= " + uuid);
        GuestInternshipDTO guestDeletedInternshipDTO = mapper.toGuestInternshipDTO(deletedInternship);
        return guestDeletedInternshipDTO;
    }

    @Transactional
    public GuestInternshipDTO restoreInternshipById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to return deleted Internship with uuid= " + uuid + " to List if Internships");
        Internship internship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));
        internshipRepository.activateInternshipById(uuid);
        internship.setDeleted(false);
        log.info("Successfully returned deleted Internship with uuid= " + uuid);
        return mapper.toGuestInternshipDTO(internship);

    }

    public void deleteInternshipById(UUID internshipId) {
        putClassNameInMDC();
        log.info("Try to delete Internship with uuid= " + internshipId);
        internshipRepository
                .findAllByIdAndDeletedFalse(internshipId)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + internshipId  +
                        " in DB", "uuid.invalid"));
        internshipRepository.deleteById(internshipId);
        log.info("Internship with uuid= " + internshipId + " was deleted");
    }

    private void putClassNameInMDC() {
        MDC.put("className", InternshipService.class.getSimpleName());
    }
}
