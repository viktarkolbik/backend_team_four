package by.exadel.internship.service;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface SchedulingService {
    List<UserDTO> getAdminTimeForForm(UUID formId);

    void saveInterviewForForm(UUID formId, InterviewDTO interviewDTO);

    void rewriteInterviewTime(UUID formId, InterviewDTO interviewDTO);
}
