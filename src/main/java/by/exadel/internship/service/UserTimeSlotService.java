package by.exadel.internship.service;


import by.exadel.internship.dto.timeForCall.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;

import java.util.UUID;

public interface UserTimeSlotService {
    void saveUserTime(UserDTO userDTO);
    void deletedById(UUID timeId);
    void restoreUserTime(UserTimeSlotWithUserIdDTO time);
}
