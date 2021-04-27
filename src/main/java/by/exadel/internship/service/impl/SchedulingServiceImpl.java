package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.TimeForCallDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulingServiceImpl implements SchedulingService {
    private final TimeForCallService timeForCallService;
    private final TimeForCallUserServise timeForCallUserServise;
    private final FormService formService;
    private final UserService userService;

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
        if (status.name().equals("REGISTERED")){
            return getTimeForInterviewWithHR();
        }
        if (status.name().equals("ADMIN_INTERVIEW_PASSED")){
            System.out.println("ADMIN_INTERVIEW_PASSED form");
        }
        if (!status.name().equals("REGISTERED") && !status.name().equals("ADMIN_INTERVIEW_PASSED")){
            System.out.println("Cancel form");
        }
        throw new NotFoundException("No such form status for Form with id = " + formFullDTO.getId(),
                "form.formStatus.invalid");
    }

    private List<TimeForCallUserDTO> getTimeForInterviewWithHR() {
        return findHRFreeTimeForInterview();
    }

    private List<TimeForCallUserDTO> findHRFreeTimeForInterview() {
        List<UserDTO> users = userService.getAllByUserRole(UserRole.ADMIN);
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
        InterviewDTO interviewDTO = new InterviewDTO();
        interviewDTO.setAdmin(userDataTime.getUserId());
        interviewDTO.setAdminInterviewDate(userDataTime.getStartHour());
        formFullDTO.setInterview(interviewDTO);
        formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
        formService.updateForm(formFullDTO);
    }

}
