package by.exadel.internship.service;


import by.exadel.internship.dto.timeForCall.TimeForCallWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;

import java.util.UUID;

public interface TimeForCallUserServise {
    void saveUserTime(UserDTO userDTO);
    void deletedById(UUID timeId);
    void restoreUserTime(TimeForCallWithUserIdDTO time);
}
