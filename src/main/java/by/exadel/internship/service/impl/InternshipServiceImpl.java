package by.exadel.internship.service.impl;


import by.exadel.internship.dto.internship.GuestFullInternshipDTO;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.internship.GuestInternshipMapper;
import by.exadel.internship.mapper.internship.UserInternshipMapper;
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
    private final UserInternshipMapper userInternshipMapper;
    private final GuestInternshipMapper guestInternshipMapper;

    private static final String SIMPLE_CLASS_NAME = InternshipService.class.getSimpleName();

    public GuestFullInternshipDTO getById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get Internship with id= {}", id);

        Internship internship = internshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such Internship with id = " + id + " in DB", "id.invalid"));

        log.info("Successfully got Internship with id= {}", id);

        return guestInternshipMapper.toGuestFullInternshipDTO(internship);

    }

    public List<GuestShortInternshipDTO> getAll() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all Internships with skills");

        List<Internship> internships = internshipRepository.findAllWithSkill();

        log.info("Try to get all Internships like guestInternshipDTOs  with skills");

        List<GuestShortInternshipDTO> guestShortInternshipDTOList = guestInternshipMapper.mapGuestShortInternshipDTOList(internships);

        log.info("Successfully list of guestInternshipDTOs with skills");

        return guestShortInternshipDTOList;
    }

    public List<UserInternshipDTO> getAllDeleted() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get deleted Internships");

        List<Internship> deletedInternships = internshipRepository.findAllByDeletedTrue();
        List<UserInternshipDTO> userDeletedInternshipDTOList = userInternshipMapper.map(deletedInternships);

        log.info("Successfully got list of deleted Internships");
        return userDeletedInternshipDTOList;
    }

    public UserInternshipDTO getDeletedInternshipById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get deleted Internships with uuid: {}", uuid);

        Internship deletedInternship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));

        log.info("Successfully got deleted Internships with uuid: {}", uuid);

        UserInternshipDTO userDeletedInternshipDTO = userInternshipMapper.toUserInternshipDTO(deletedInternship);

        return userDeletedInternshipDTO;
    }

    @Transactional
    public UserInternshipDTO restoreInternshipById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to restore Internship with uuid: {}", uuid);

        Internship internship = internshipRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("No such deleted Internship with uuid = " + uuid +
                        " in DB", "uuid.invalid"));

        internshipRepository.activateInternshipById(uuid);
        internship.setDeleted(false);

        log.info("Successfully restore Internship with uuid: {}", uuid);

        return userInternshipMapper.toUserInternshipDTO(internship);

    }

    public void deleteInternshipById(UUID internshipId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to delete Internship with uuid: {}", internshipId);

        internshipRepository
                .findByIdAndDeletedFalse(internshipId)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = " + internshipId +
                        " in DB", "uuid.invalid"));
        internshipRepository.deleteById(internshipId);

        log.info("Internship with uuid: {} was deleted", internshipId);

    }

    public UserInternshipDTO saveInternship(UserInternshipDTO internshipDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save new Internship int DB");

        Internship internship = userInternshipMapper.toInternship(internshipDTO);
        UserInternshipDTO userInternshipDTO = userInternshipMapper
                .toUserInternshipDTO(internshipRepository.save(internship));

        log.info("Internship was save with uuid {}", internship.getId());

        return userInternshipDTO;
    }

}
