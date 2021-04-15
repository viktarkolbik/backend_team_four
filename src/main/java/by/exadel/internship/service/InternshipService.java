package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internShipMapper;

    public GuestInternshipDTO getById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to get Internships with uuid= " + uuid);
        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get Internships with uuid=" + uuid);
        Internship internship = internshipRepository
                .findAllByIdAndDeletedFalse(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + uuid + " in DB", "uuid.invalid"));
        log.info("Successfully got Internships with uuid=" + uuid);

        return internShipMapper.toGuestInternshipDTO(internship);
    }

    public List<GuestInternshipDTO> getAll() {
        putClassNameInMDC();
        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get all Internships");
        List<Internship> internships = internshipRepository.findAllByDeletedFalse();
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        log.info("Successfully got list of Internships");
        log.info("Successfully list of Internships");
        return guestInternshipDTOList;

    }

    public List<GuestInternshipDTO> getAllDeleted() {
        putClassNameInMDC();
        log.info("Try to get deleted Internships");
        List<Internship> deletedInternships = internshipRepository.findAllByDeletedTrue();
        List<GuestInternshipDTO> guestDeletedInternshipDTOList = internShipMapper.map(deletedInternships);
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
        GuestInternshipDTO guestDeletedInternshipDTO = internShipMapper.toGuestInternshipDTO(deletedInternship);
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
        return internShipMapper.toGuestInternshipDTO(internship);

    }

    public void deleteInternshipById(UUID internshipId) {
        putClassNameInMDC();
        log.info("Try to delete Internship with uuid= " + internshipId);
        internshipRepository
                .findAllByIdAndDeletedFalse(internshipId)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + internshipId  +
                        " in DB", "uuid.invalid"));
        internshipRepository.deleteById(internshipId);
        log.info("Internship with uuid= " + internshipId + " was deleted");
    }

    private void putClassNameInMDC() {
        MDC.put("className", InternshipService.class.getSimpleName());
    }
}
