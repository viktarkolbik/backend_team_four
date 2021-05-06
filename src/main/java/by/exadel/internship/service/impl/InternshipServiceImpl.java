package by.exadel.internship.service.impl;


import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.internship.InternshipMapper;
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
    private final InternshipMapper internshipMapper;

    private static final String SIMPLE_CLASS_NAME = InternshipService.class.getSimpleName();

    public GuestInternshipDTO getGuestRepresentationOfInternshipById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all GuestFullInternshipDTO");

        return internshipMapper.toGuestInternshipDTO(getById(id));
    }

    public UserInternshipDTO getUserRepresentationOfInternshipById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all UserInternshipDTO");

        return internshipMapper.toUserInternshipDTO(getById(id));
    }


    public List<BaseInternshipDTO> getAll() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all Internships with skills");

        List<Internship> internships = internshipRepository.findAllWithSkill();

        log.info("Try to get all Internships like guestInternshipDTOs  with skills");

        List<BaseInternshipDTO> baseInternshipDTOList = internshipMapper.mapBaseInternshipDTOList(internships);

        log.info("Successfully list of guestInternshipDTOs with skills");

        return baseInternshipDTOList;
    }

    public List<UserInternshipDTO> getAllDeleted() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get deleted Internships");

        List<Internship> deletedInternships = internshipRepository.findAllByDeletedTrue();
        List<UserInternshipDTO> userDeletedInternshipDTOList = internshipMapper.mapUserInternshipDTOList(deletedInternships);

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

        UserInternshipDTO userDeletedInternshipDTO = internshipMapper.toUserInternshipDTO(deletedInternship);

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

        return internshipMapper.toUserInternshipDTO(internship);

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

        Internship internship = internshipMapper.toInternship(internshipDTO);
        UserInternshipDTO userInternshipDTO = internshipMapper
                .toUserInternshipDTO(internshipRepository.save(internship));

        log.info("Internship was save with uuid {}", internship.getId());

        return userInternshipDTO;
    }

    private Internship getById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get Internship with id= {}", id);

        Internship internship = internshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such Internship with id = " + id + " in DB", "id.invalid"));

        log.info("Successfully got Internship with id= {}", id);

        return internship;

    }

}
