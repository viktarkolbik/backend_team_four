package by.exadel.internship.service.impl;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.interview.InterviewInfoDTO;
import by.exadel.internship.dto.interview.InterviewSaveDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.user.UserFullDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.repository.InterviewRepository;
import by.exadel.internship.repository.UserRepository;
import by.exadel.internship.service.InterviewService;
import by.exadel.internship.service.UserService;
import by.exadel.internship.service.UserTimeSlotService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    private static final String SIMPLE_CLASS_NAME = InterviewService.class.getSimpleName();

    private final InterviewRepository interviewRepository;
    private final InterviewMapper mapper;
    private final UserTimeSlotService userTimeSlotService;
    private final FormRepository formRepository;
    private final UserService userService;
    private final FormMapper formMapper;
    private final UserRepository userRepository;

    @Override
    public List<InterviewInfoDTO> getAllByUserId(UUID userId, UserRole userRole) {
        List<Interview> interviews;
        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
        switch (userRole) {
            case ADMIN: {
                interviews = interviewRepository.findAllByAdmin(user);
                break;
            }
            case TECH_EXPERT: {
                interviews = interviewRepository.findAllByTechSpecialist(user);
                break;
            }
            default: {
                throw new InappropriateRoleException("Inappropriate role", "user.role.invalid");
            }
        }

        return getAllInterviewWithUser(interviews);
    }

    public List<InterviewInfoDTO> getAllInterviewWithUser(List<Interview> interviews){
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Return interview with users");
        return  mapper.toInterviewInfoList(interviews);
    }


    @Override
    public void saveInterviewForForm(UUID formId, InterviewSaveDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = getFormById(formId);
        UserFullDTO userDTO = userService.getFullUserById(interviewDTO.getUserId());
        if (userDTO.getUserRole() == UserRole.ADMIN
                && formFullDTO.getFormStatus() == FormStatus.REGISTERED
                && formFullDTO.getInterview() == null) {
            InterviewDTO interviewFullDTO = new InterviewDTO();

            deleteTime(interviewDTO.getUserInterviewDate(), userDTO);

            interviewFullDTO.setAdmin(userDTO);
            interviewFullDTO.setAdminInterviewDate(interviewDTO.getUserInterviewDate());

            formFullDTO.setInterview(interviewFullDTO);
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            saveForm(formFullDTO);
        }else {
            if (formFullDTO.getFormStatus() != FormStatus.REGISTERED) {
                throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't has need Status");
            }
            if (userDTO.getUserRole()!=UserRole.ADMIN){
                throw new InappropriateRoleException("User with uuid = " + userDTO.getId() + " doesn't has need Role");

            }
            if (formFullDTO.getInterview() != null){
                throw new IllegalArgumentException("Form with uuid =" + formFullDTO.getId() + " has Interview");
            }
        }
    }

    private void saveForm(FormFullDTO formFullDTO){
        log.info("Try to update Form with uuid = {}", formFullDTO.getId());
        Form form = formRepository.findByIdAndDeletedFalse(formFullDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formFullDTO.getId() +
                        " Not Found in DB", "form.uuid.invalid"));
        form.setFormStatus(formFullDTO.getFormStatus());
        Interview interview = mapper.toInterview(formFullDTO.getInterview());
        form.setInterview(interview);
        formRepository.save(form);
        log.info("Successfully saved Form with uuid = {} and Interview with uuid = {}",
                form.getId(),interview.getId());
    }

    private FormFullDTO getFormById(UUID formId){
        Form form = formRepository.findByIdAndDeletedFalse(formId).
                orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        log.info("Return formFullDTO with uuid = {}", formId);
        return formMapper.toFormDto(form);
    }

    private void deleteTime(LocalDateTime localDateTime, UserFullDTO userDTO) {
        userDTO.getUserTimeSlots()
                .stream()
                .filter(userTimeSlotDTO -> userTimeSlotDTO.getStartDate()
                        .equals(localDateTime))
                .findFirst()
                .ifPresent(userTimeSlotDTO -> {
                    userTimeSlotService.deletedById(userTimeSlotDTO.getId());
                    userDTO.getUserTimeSlots().remove(userTimeSlotDTO);
                });
    }

    @Override
    public void rewriteInterviewTime(UUID formId, InterviewSaveDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = getFormById(formId);
        InterviewDTO interviewFullDTO = formFullDTO.getInterview();
        UserFullDTO userDTO = userService.getFullUserById(interviewDTO.getUserId());
        if(formFullDTO.getFormStatus() == FormStatus.ADMIN_INTERVIEW_ASSIGNED
                && userDTO.getUserRole() == UserRole.ADMIN){
            this.restoreTime(interviewFullDTO.getAdminInterviewDate(), userDTO.getId());

            interviewFullDTO.setAdmin(userDTO);
            interviewFullDTO.setAdminInterviewDate(interviewDTO.getUserInterviewDate());

            formFullDTO.setInterview(interviewFullDTO);
            saveForm(formFullDTO);

            this.deleteTime(interviewDTO.getUserInterviewDate(), userDTO);
            return;
        }
        if ((formFullDTO.getFormStatus() == FormStatus.ADMIN_INTERVIEW_PASSED
                || formFullDTO.getFormStatus() == FormStatus.TECH_INTERVIEW_ASSIGNED)
                && userDTO.getUserRole() == UserRole.TECH_EXPERT){
            if (formFullDTO.getFormStatus() == FormStatus.TECH_INTERVIEW_ASSIGNED) {
                this.restoreTime(interviewFullDTO.getTechInterviewDate(), userDTO.getId());
            }
            interviewFullDTO.setTechSpecialist(userDTO);
            interviewFullDTO.setTechInterviewDate(interviewDTO.getUserInterviewDate());

            formFullDTO.setInterview(interviewFullDTO);
            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            saveForm(formFullDTO);

            this.deleteTime(interviewDTO.getUserInterviewDate(),userDTO);
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " or user with uuid = "
                + userDTO.getId() +
                " doesn't have required status", "form.fromStatus.invalid");
    }

    private void restoreTime(LocalDateTime restoreLocalDateTime, UUID userId) {
        UserTimeSlotWithUserIdDTO restoreTime = new UserTimeSlotWithUserIdDTO();
        restoreTime.setStartDate(restoreLocalDateTime);
        restoreTime.setUserId(userId);
        userTimeSlotService.restoreUserTime(restoreTime);
    }


}
