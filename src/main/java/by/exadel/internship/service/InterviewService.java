package by.exadel.internship.service;

import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.interview.InterviewSaveDTO;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
    List<InterviewDTO> getAllByUserId(UUID userId, UserRole userRole);
    void saveInterviewForForm(UUID formId, InterviewSaveDTO interviewDTO);
    void rewriteInterviewTime(UUID formId, InterviewSaveDTO interviewDTO);
}
