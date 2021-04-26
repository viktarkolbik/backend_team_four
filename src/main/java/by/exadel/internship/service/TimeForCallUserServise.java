package by.exadel.internship.service;


import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCallUser;

import java.util.List;

public interface TimeForCallUserServise {
    List<TimeForCallUserDTO> getAll();
    void updateTime(TimeForCallUserDTO time);


    void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList);
}
