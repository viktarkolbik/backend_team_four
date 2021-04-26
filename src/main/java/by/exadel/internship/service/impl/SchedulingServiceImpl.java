package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallService;
import by.exadel.internship.service.TimeForCallUserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {
    private final TimeForCallService timeForCallService;
    private final TimeForCallUserServise timeForCallUserServise;

    private List<TimeForCallDTO> timeForCallDTOList;
    private List<TimeForCallUserDTO> timeForCallUserDTOList;

    private static final long INTERVIEW_TIME = 30;


    @Override
    public void makeSchedule() {
        fillLists();
        simpleWay();
    }

    private void fillLists() {
        timeForCallDTOList = timeForCallService.getAll();
        timeForCallUserDTOList = timeForCallUserServise.getAll();
    }

    private void simpleWay(){
        for (TimeForCallUserDTO userTime : timeForCallUserDTOList) {
            for (TimeForCallDTO formTime : timeForCallDTOList) {
                if (userTime.getStartHour().getHour() == formTime.getStartHour()) {
                    System.out.println("Start : " + userTime.getUser().getLogin() + " " + formTime.getStartHour());
                    System.out.println(userTime.getStartHour());
                    userTime.setStartHour(userTime.getStartHour().plusMinutes(INTERVIEW_TIME));
                    System.out.println(userTime.getStartHour());
                }
                if (userTime.getEndHour().getHour() == formTime.getEndHour()) {
                    System.out.println("Finish : " + userTime.getUser().getLogin() + " " + formTime.getEndHour());
                    System.out.println(userTime.getEndHour());
                    userTime.setEndHour(userTime.getEndHour().minusMinutes(INTERVIEW_TIME));
                    System.out.println(userTime.getEndHour());
                }
                if (userTime.getStartHour().getHour() <= formTime.getStartHour() ||
                        userTime.getEndHour().getHour() >= formTime.getEndHour()) {
/*
                    System.out.println("None : " + userTime.getUser().getLogin() + " " + formTime.getStartHour() + " " + formTime.getEndHour());
*/
                }
            }
        }
    }

}
