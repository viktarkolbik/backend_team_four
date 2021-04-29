package by.exadel.internship.service;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.TimeForCallWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface SchedulingService {
    List<UserDTO> getAdminTimeForForm(UUID formId);

    void saveUserTime(UserDTO userDTO);

    void saveInterviewForForm(UUID formId, TimeForCallWithUserIdDTO time);
}
