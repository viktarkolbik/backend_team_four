package by.exadel.internship.service;

import by.exadel.internship.dto.TimeForCallUserDTO;

import java.util.List;

public interface SchedulingService {
    public void makeSchedule();

    void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList);
}
