package by.exadel.internship.service;


import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.TimeForCallWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.entity.TimeForCallUser;

import java.util.List;
import java.util.UUID;

public interface TimeForCallUserServise {
    void saveUserTime(UserDTO userDTO);
    void deletedById(UUID timeId);
    void restoreUserTime(TimeForCallWithUserIdDTO time);
}
