package by.exadel.internship.service.impl;

import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallService;
import by.exadel.internship.service.TimeForCallUserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {
    private final TimeForCallService timeForCallService;
    private final TimeForCallUserServise timeForCallUserServise;


    @Override
    public void makeSchedule() {

    }
}
