package by.exadel.internship.service;

import by.exadel.internship.dto.TimeForCallUserDTO;

import java.util.List;
import java.util.UUID;

public interface SchedulingService {
    List<TimeForCallUserDTO> makeSchedule(UUID internshipId);

    void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList);

    void saveInterviewForForm(UUID formId, TimeForCallUserDTO userDataTime);
}
