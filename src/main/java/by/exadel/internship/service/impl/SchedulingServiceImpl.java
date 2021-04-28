package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.service.*;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulingServiceImpl implements SchedulingService {
    private final TimeForCallUserServise timeForCallUserServise;
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
    public List<TimeForCallUserDTO> getFreeTimeForForm(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get free users time for Form");
        FormFullDTO formFullDTO = formService.getById(formId);
        return checkInterviewOver(formFullDTO);
    }

    private List<TimeForCallUserDTO> checkInterviewOver(FormFullDTO formFullDTO) {
        FormStatus status = formFullDTO.getFormStatus();
        if (status.name().equals("REGISTERED")) {
            return findAdminFreeTimeForInterview(status);
        }
        if (status.name().equals("ADMIN_INTERVIEW_PASSED")) {
            return findAdminFreeTimeForInterview(status);
        }
        throw new NotFoundException("Form with uuid = " + formFullDTO.getId()
                + " doesn't need to get users time because Form has "
                + formFullDTO.getFormStatus() + " status");
    }

    private List<TimeForCallUserDTO> findAdminFreeTimeForInterview(FormStatus formStatus) {
        List<UserDTO> users = userService.getAllByUserRole(STATUS_USER_ROLE_MAP.get(formStatus));
        List<TimeForCallUserDTO> timeForCallUserDTOList = new ArrayList<>();
        users.forEach(userDTO -> {
            timeForCallUserDTOList.addAll(timeForCallUserServise.getAllByUserId(userDTO.getId()));
        });
        return timeForCallUserDTOList;
    }


    @Override
    public void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save users free time in DB");
        timeForCallUserServise.saveUserTime(timeForCallUserDTOList);
        log.info("Successfully saved time in DB");
    }

    @Override
    public void saveInterviewForForm(UUID formId, TimeForCallUserDTO userDataTime) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        if (formFullDTO.getInterview() != null &&
                formFullDTO.getFormStatus().equals(FormStatus.ADMIN_INTERVIEW_PASSED)) {
            InterviewDTO interviewDTO = formFullDTO.getInterview();
            interviewDTO.setTechSpecialist(userDataTime.getUserId());
            interviewDTO.setTechInterviewDate(userDataTime.getStartHour());
            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            formService.updateForm(formFullDTO);
            return;
        }
        if (formFullDTO.getFormStatus().equals(FormStatus.REGISTERED)) {
            InterviewDTO interviewDTO = new InterviewDTO();
            interviewDTO.setAdmin(userDataTime.getUserId());
            interviewDTO.setAdminInterviewDate(userDataTime.getStartHour());
            formFullDTO.setInterview(interviewDTO);
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            formService.updateForm(formFullDTO);
            return;
        }
        throw new NotFoundException("Form with uuid = " + formFullDTO.getId() + " doesn't has need Status");
    }

}
