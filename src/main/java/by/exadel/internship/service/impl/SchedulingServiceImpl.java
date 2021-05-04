package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;

import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.service.*;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulingServiceImpl implements SchedulingService {
    private final UserTimeSlotService userTimeSlotService;
    private final FormService formService;
    private final UserService userService;

    private static final Map<FormStatus, UserRole> STATUS_USER_ROLE_MAP = new HashMap<>() {
        {
            put(FormStatus.REGISTERED, UserRole.ADMIN);
            put(FormStatus.ADMIN_INTERVIEW_PASSED, UserRole.TECH_EXPERT);
        }
    };

    private static final String SIMPLE_CLASS_NAME = SchedulingService.class.getSimpleName();


    @Override
    public List<UserDTO> getAdminTimeForForm(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get free users time for Form");
        FormFullDTO formFullDTO = formService.getById(formId);
        return checkInterviewOver(formFullDTO);
    }

    private List<UserDTO> checkInterviewOver(FormFullDTO formFullDTO) {
        FormStatus status = formFullDTO.getFormStatus();
        if (status.equals(FormStatus.REGISTERED) || status.equals(FormStatus.ADMIN_INTERVIEW_PASSED)) {
            return findAdminForInterview(status);
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId()
                + " doesn't need to get users time because Form has "
                + formFullDTO.getFormStatus() + " status");
    }

    private List<UserDTO> findAdminForInterview(FormStatus formStatus) {
        log.info("Finding a user with the desired status");
        List<UserDTO> userDTOList = userService.getAllByUserRole(STATUS_USER_ROLE_MAP.get(formStatus));
        userDTOList = userDTOList.stream()
                .filter(userDTO -> userDTO.getUserTimeSlots().size() != 0)
                .collect(Collectors.toList());
        log.info("Return user list with desired status");
        return userDTOList;
    }


    @Override
    public void saveInterviewForForm(UUID formId, UserTimeSlotWithUserIdDTO userDataTime) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        UserDTO userDTO = userService.getById(userDataTime.getUserId());
        if (formFullDTO.getInterview() != null &&
                formFullDTO.getFormStatus().equals(FormStatus.ADMIN_INTERVIEW_PASSED) &&
                userDTO.getUserRole().equals(UserRole.TECH_EXPERT)) {
            InterviewDTO interviewDTO = formFullDTO.getInterview();

            interviewDTO.setTechSpecialist(userDataTime.getUserId());
            interviewDTO.setTechInterviewDate(userDataTime.getStartDate());

            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            deleteTime(userDataTime.getId());
            formService.updateForm(formFullDTO);
            log.info("Save HR interview date");
            return;
        }
        if (formFullDTO.getFormStatus().equals(FormStatus.REGISTERED) &&
                userDTO.getUserRole().equals(UserRole.ADMIN)) {
            InterviewDTO interviewDTO = new InterviewDTO();

            interviewDTO.setAdmin(userDataTime.getUserId());
            interviewDTO.setAdminInterviewDate(userDataTime.getStartDate());

            formFullDTO.setInterview(interviewDTO);
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            deleteTime(userDataTime.getId());
            formService.updateForm(formFullDTO);
            log.info("Save Tech Expert interview date");
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't has need Status");
    }

    private void deleteTime(UUID timeId) {
        userTimeSlotService.deletedById(timeId);
    }

    @Override
    public void rewriteInterviewTime(UUID formId, UserTimeSlotWithUserIdDTO time) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        if (formFullDTO.getFormStatus().equals(FormStatus.ADMIN_INTERVIEW_ASSIGNED)) {
            InterviewDTO interviewDTO = formFullDTO.getInterview();
            restoreTime(interviewDTO);

            interviewDTO.setAdminInterviewDate(time.getStartDate());
            interviewDTO.setAdmin(time.getUserId());
            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(time.getId());
            return;
        }
        if (formFullDTO.getFormStatus().equals(FormStatus.TECH_INTERVIEW_ASSIGNED)) {
            InterviewDTO interviewDTO = formFullDTO.getInterview();
            restoreTime(interviewDTO);

            interviewDTO.setTechInterviewDate(time.getStartDate());
            interviewDTO.setTechSpecialist(time.getUserId());
            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(time.getId());
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't have required status",
                "form.fromStatus.invalid");
    }

    private void restoreTime(InterviewDTO interviewDTO) {
        UserTimeSlotWithUserIdDTO restoreTime = new UserTimeSlotWithUserIdDTO();
        restoreTime.setStartDate(interviewDTO.getAdminInterviewDate());
        restoreTime.setUserId(interviewDTO.getAdmin());
        userTimeSlotService.restoreUserTime(restoreTime);
    }


}
