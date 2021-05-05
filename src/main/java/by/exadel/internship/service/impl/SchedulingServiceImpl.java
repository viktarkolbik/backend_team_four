package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;

import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserDTO;
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

import java.time.LocalDateTime;
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
    public void saveInterviewForForm(UUID formId, InterviewDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        formFullDTO.setInterview(interviewDTO);
        if (interviewDTO.getTechSpecialist() == null && interviewDTO.getAdmin() != null){
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            deleteTime(interviewDTO.getAdminInterviewDate(), interviewDTO.getAdmin());
            formService.updateForm(formFullDTO);
            log.info("Save HR interview date");
            return;
        }
        if (interviewDTO.getTechSpecialist() != null){
            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            deleteTime(interviewDTO.getTechInterviewDate(), interviewDTO.getTechSpecialist());
            formService.updateForm(formFullDTO);
            log.info("Save Tech Expert interview date");
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't has need Status");
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
    public void rewriteInterviewTime(UUID formId, InterviewDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        if (formFullDTO.getFormStatus().equals(FormStatus.ADMIN_INTERVIEW_ASSIGNED)) {
            InterviewDTO restoreInterviewDTO = formFullDTO.getInterview();
            restoreTime(restoreInterviewDTO.getAdminInterviewDate(), restoreInterviewDTO.getAdmin());

            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(interviewDTO.getAdminInterviewDate(),interviewDTO.getAdmin());
            return;
        }
        if (formFullDTO.getFormStatus().equals(FormStatus.TECH_INTERVIEW_ASSIGNED)) {
            InterviewDTO restoreInterviewDTO = formFullDTO.getInterview();
            restoreTime(restoreInterviewDTO.getTechInterviewDate(), restoreInterviewDTO.getTechSpecialist());

            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(interviewDTO.getTechInterviewDate(),interviewDTO.getTechSpecialist());
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't have required status",
                "form.fromStatus.invalid");
    }

    private void restoreTime(LocalDateTime restoreLocalDateTime, UUID userId) {
        UserTimeSlotWithUserIdDTO restoreTime = new UserTimeSlotWithUserIdDTO();
        restoreTime.setStartDate(restoreLocalDateTime);
        restoreTime.setUserId(userId);
        userTimeSlotService.restoreUserTime(restoreTime);
    }


}
