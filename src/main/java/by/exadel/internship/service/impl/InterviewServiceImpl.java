package by.exadel.internship.service.impl;

import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.interview.InterviewWithUserNameDTO;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.interview.InterviewSaveDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.repository.InterviewRepository;
import by.exadel.internship.service.FormService;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    private static final String SIMPLE_CLASS_NAME = InterviewService.class.getSimpleName();

    private final InterviewRepository interviewRepository;
    private final InterviewMapper mapper;
    private final UserTimeSlotService userTimeSlotService;
    private final FormService formService;
    private final UserService userService;


    @Override
    public List<InterviewWithUserNameDTO> getAllByUserId(UUID userId, UserRole userRole) {
        List<Interview> interviews;
        switch (userRole) {
            case ADMIN: {
                interviews = interviewRepository.findAllByAdmin(userId);
                break;
            }
            case TECH_EXPERT: {
                interviews = interviewRepository.findAllByTechSpecialist(userId);
                break;
            }
            default: {
                throw new InappropriateRoleException("Inappropriate role", "user.role.invalid");
            }
        }
        List<InterviewWithUserNameDTO> interviewWithUserNameDTOList =
                mapper.mapToInterviewWithUserDTO(interviews);
        interviewWithUserNameDTOList.forEach(interview -> {
            interview.setAdminUser(userService.getSimpleUserById(interview.getAdmin()));
            interview.setTechSpecialistUser(userService.getSimpleUserById(interview.getTechSpecialist()));
        });
        return interviewWithUserNameDTOList;
    }


    @Override
    public void saveInterviewForForm(UUID formId, InterviewSaveDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        UserDTO userDTO = userService.getById(interviewDTO.getUserId());
        if (userDTO.getUserRole() == UserRole.ADMIN
                && formFullDTO.getFormStatus() == FormStatus.REGISTERED
                && formFullDTO.getInterview() == null) {
            InterviewDTO interviewFullDTO = new InterviewDTO();
            interviewFullDTO.setAdmin(interviewDTO.getUserId());
            interviewFullDTO.setAdminInterviewDate(interviewDTO.getUserInterviewDate());

            deleteTime(interviewDTO.getUserInterviewDate(), interviewDTO.getUserId());

            formFullDTO.setInterview(interviewFullDTO);
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            formService.updateForm(formFullDTO);
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

    private void deleteTime(LocalDateTime localDateTime, UUID userId) {
        UserDTO userDTO = userService.getById(userId);
        List<UserTimeSlotDTO> userTimeSlotDTOList = userDTO.getUserTimeSlots()
                .stream()
                .filter(userTimeSlotDTO -> userTimeSlotDTO.getStartDate()
                        .equals(localDateTime)).collect(Collectors.toList());
        userTimeSlotService.deletedById(userTimeSlotDTOList.get(0).getId());
    }

    @Override
    public void rewriteInterviewTime(UUID formId, InterviewSaveDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        InterviewDTO interviewFullDTO = formFullDTO.getInterview();
        UserDTO userDTO = userService.getById(interviewDTO.getUserId());
        if(formFullDTO.getFormStatus() == FormStatus.ADMIN_INTERVIEW_ASSIGNED
                && userDTO.getUserRole() == UserRole.ADMIN){
            this.restoreTime(interviewFullDTO.getAdminInterviewDate(), interviewFullDTO.getAdmin());

            interviewFullDTO.setAdmin(interviewDTO.getUserId());
            interviewFullDTO.setAdminInterviewDate(interviewDTO.getUserInterviewDate());

            formFullDTO.setInterview(interviewFullDTO);
            formService.updateForm(formFullDTO);

            this.deleteTime(interviewDTO.getUserInterviewDate(),interviewDTO.getUserId());
            return;
        }
        if ((formFullDTO.getFormStatus() == FormStatus.ADMIN_INTERVIEW_PASSED
                || formFullDTO.getFormStatus() == FormStatus.TECH_INTERVIEW_ASSIGNED)
                && userDTO.getUserRole() == UserRole.TECH_EXPERT){
            if (formFullDTO.getFormStatus() == FormStatus.TECH_INTERVIEW_ASSIGNED) {
                this.restoreTime(interviewFullDTO.getTechInterviewDate(), interviewFullDTO.getTechSpecialist());
            }
            interviewFullDTO.setTechSpecialist(interviewDTO.getUserId());
            interviewFullDTO.setTechInterviewDate(interviewDTO.getUserInterviewDate());

            formFullDTO.setInterview(interviewFullDTO);
            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            formService.updateForm(formFullDTO);

            this.deleteTime(interviewDTO.getUserInterviewDate(),interviewDTO.getUserId());
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