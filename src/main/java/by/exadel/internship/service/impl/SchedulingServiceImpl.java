package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {
    private final TimeForCallService timeForCallService;
    private final TimeForCallUserServise timeForCallUserServise;
    private final FormService formService;
    private final UserService userService;

    private List<TimeForCallDTO> timeForCallDTOList;
    private List<TimeForCallUserDTO> timeForCallUserDTOList;


    @Override
    public List<TimeForCallUserDTO> makeSchedule(UUID formId) {
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
        return null;
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
        timeForCallUserServise.saveUserTime(timeForCallUserDTOList);
    }

    @Override
    public void saveInterviewForForm(UUID formId, TimeForCallUserDTO userDataTime) {
        FormFullDTO formFullDTO = formService.getById(formId);
        InterviewDTO interviewDTO = new InterviewDTO();
        interviewDTO.setAdmin(userDataTime.getUserId());
        interviewDTO.setAdminInterviewDate(userDataTime.getStartHour());
        formFullDTO.setInterview(interviewDTO);
        formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
        formService.updateForm(formFullDTO);
    }

}
