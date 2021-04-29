package by.exadel.internship.service.impl;


import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.ShortInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.GuestInternshipMapper;
import by.exadel.internship.mapper.ShortInternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import by.exadel.internship.service.InternshipService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final GuestInternshipMapper guestInternshipMapper;
    private final ShortInternshipMapper shortInternshipMapper;

    private static final String SIMPLE_CLASS_NAME = InternshipService.class.getSimpleName();

    public GuestInternshipDTO getById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get Internship with id= {}", id);

        Internship internship = internshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such Internship with id = " + id + " in DB", "id.invalid"));

        log.info("Successfully got Internship with id= {}", id);

        return guestInternshipMapper.toGuestInternshipDTO(internship);

    }

    public List<ShortInternshipDTO> getAll() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all Internships with skills");

        List<Internship> internships = internshipRepository.findAllWithSkill();

        log.info("Try to get all Internships like guestInternshipDTOs  with skills");

        List<ShortInternshipDTO> shortInternshipDTOList = shortInternshipMapper.map(internships);

        log.info("Successfully list of guestInternshipDTOs with skills");

        return shortInternshipDTOList;
    }

    public List<GuestInternshipDTO> getAllDeleted() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get deleted Internships");

        List<Internship> deletedInternships = internshipRepository.findAllByDeletedTrue();
        List<GuestInternshipDTO> guestDeletedInternshipDTOList = guestInternshipMapper.map(deletedInternships);

        log.info("Successfully got list of deleted Internships");
        return guestDeletedInternshipDTOList;
    }

    public GuestInternshipDTO getDeletedInternshipById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get deleted Internships with uuid: {}", uuid);

        Internship deletedInternship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));

        log.info("Successfully got deleted Internships with uuid: {}", uuid);

        GuestInternshipDTO guestDeletedInternshipDTO = guestInternshipMapper.toGuestInternshipDTO(deletedInternship);

        return guestDeletedInternshipDTO;
    }

    @Transactional
    public GuestInternshipDTO restoreInternshipById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to restore Internship with uuid: {}", uuid);

        Internship internship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));

        internshipRepository.activateInternshipById(uuid);
        internship.setDeleted(false);

        log.info("Successfully restore Internship with uuid: {}", uuid);

        return guestInternshipMapper.toGuestInternshipDTO(internship);

    }

    public void deleteInternshipById(UUID internshipId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to delete Internship with uuid: {}", internshipId);

        internshipRepository
                .findAllByIdAndDeletedFalse(internshipId)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + internshipId +
                        " in DB", "uuid.invalid"));
        internshipRepository.deleteById(internshipId);

        log.info("Internship with uuid: {} was deleted", internshipId);

    }

    public GuestInternshipDTO saveInternship(GuestInternshipDTO internshipDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save new Internship int DB");

        Internship internship = guestInternshipMapper.toInternship(internshipDTO);
        internshipRepository.save(internship);

        log.info("Internship was save with uuid {}", internship.getId());

        return guestInternshipMapper.toGuestInternshipDTO(internship);
    }

}
