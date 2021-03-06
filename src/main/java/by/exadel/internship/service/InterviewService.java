package by.exadel.internship.service;

import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.interview.InterviewInfoDTO;
import by.exadel.internship.dto.interview.InterviewSaveDTO;
import by.exadel.internship.entity.Interview;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
    List<InterviewInfoDTO> getAllByUserId(UUID userId, UserRole userRole);
    void saveInterviewForForm(UUID formId, InterviewSaveDTO interviewDTO);
    void rewriteInterviewTime(UUID formId, InterviewSaveDTO interviewDTO);
    List<InterviewInfoDTO> getAllInterviewWithUser(List<Interview> interviews);

}
