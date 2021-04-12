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
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));
        log.info("Successfully got Internships with uuid= " + uuid);
        return internShipMapper.toGuestInternshipDTO(internship);
    }

    public List<GuestInternshipDTO> getAll() {
        putClassNameInMDC();
        log.info("Try to get all Internships");
        List<Internship> internships = internshipRepository.findAll();
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        log.info("Successfully got list of Internships");
        return guestInternshipDTOList;

    }

    public List<GuestInternshipDTO> gellAllDeleted() {
        putClassNameInMDC();
        log.info("Try to get deleted Internships");
        List<Internship> deletedInternships = internshipRepository.findAllDeleted();
        List<GuestInternshipDTO> guestDeletedInternshipDTOList = internShipMapper.map(deletedInternships);
        log.info("Successfully got list of deleted Internships");
        return guestDeletedInternshipDTOList;
    }

    public GuestInternshipDTO getDeletedInternshipById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to get deleted Internships with uuid= " + uuid);
        Internship deletedInternship = internshipRepository.findDeletedById(uuid);
        if (deletedInternship == null) {
            throw new NotFoundException("No such deleted Internship with uuid = " + uuid +
                    " in DB", "uuid.invalid");
        }
        log.info("Successfully got deleted Internships with uuid= " + uuid);
        GuestInternshipDTO guestDeletedInternshipDTO = internShipMapper.toGuestInternshipDTO(deletedInternship);
        return guestDeletedInternshipDTO;
    }

    public GuestInternshipDTO doActiveDeletedInternshipById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to return deleted Internship with uuid= " + uuid + " to List if Internships");
        internshipRepository.updateDeletedById(uuid);
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));
        log.info("Successfully returned deleted Internship with uuid= " + uuid);
        return internShipMapper.toGuestInternshipDTO(internship);

    }

    public void deleteInternshipById(UUID internshipId) {
        putClassNameInMDC();
        log.info("Try to delete Internship with uuid= " + internshipId);
        internshipRepository.deleteById(internshipId);
        //Method "deletedById" return void and we can't understand internship was deleted or not
        log.info("Internship with uuid= " + internshipId + " was deleted");
    }

    private void putClassNameInMDC() {
        MDC.put("className", InternshipService.class.getSimpleName());
    }
}
