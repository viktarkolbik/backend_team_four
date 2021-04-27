package by.exadel.internship.service;


import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCallUser;

import java.util.List;
import java.util.UUID;

public interface TimeForCallUserServise {
    List<TimeForCallUserDTO> getAll();
    void updateTime(TimeForCallUserDTO time);
    List<TimeForCallUserDTO> getAllByUserId(UUID userId);


    void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList);
}
