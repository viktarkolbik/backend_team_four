package by.exadel.internship.service;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.enums.UserRole;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
    List<InterviewDTO> getAllByUserId(UUID userId, UserRole userRole);
    void saveInterviewForForm(UUID formId, InterviewDTO interviewDTO);
    void rewriteInterviewTime(UUID formId, InterviewDTO interviewDTO);
}
