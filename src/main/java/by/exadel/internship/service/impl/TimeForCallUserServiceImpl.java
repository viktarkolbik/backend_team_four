package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.InterviewTime;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.mapper.TimeForCallUserMapper;
import by.exadel.internship.repository.TimeForCallUserRepository;
import by.exadel.internship.service.TimeForCallUserServise;
import by.exadel.internship.service.UserService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TimeForCallUserServiceImpl implements TimeForCallUserServise {

    private final TimeForCallUserRepository timeForCallUserRepository;
    private final UserService userService;
    private final TimeForCallUserMapper mapper;

    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY = 30;
    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO = 0;
    private static int INTERVIEW_TIME = 30;
    private static final String SIMPLE_CLASS_NAME = TimeForCallUserServise.class.getSimpleName();


    private List<TimeForCallUserDTO> resultUSerTimeList;

    @Override
    public List<TimeForCallUserDTO> getAll() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        List<TimeForCallUser> timeForCallUsers = timeForCallUserRepository.findAll();
        return mapper.mapToDTO(timeForCallUsers);
    }

    @Override
    public void updateTime(TimeForCallUserDTO time) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        TimeForCallUser timeForCallUser = mapper.toTimeForCallUserEntity(time);
        timeForCallUserRepository.save(timeForCallUser);
    }

    @Override
    public List<TimeForCallUserDTO> getAllByUserId(UUID userId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        List<TimeForCallUser> timeForCallUsers = timeForCallUserRepository.findAllByUserId(userId);
        return mapper.mapToDTO(timeForCallUsers);
    }

    @Override
    public void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        resultUSerTimeList = new ArrayList<>();
        timeForCallUserDTOList.forEach(timeForCallUser -> {
            UserDTO user = userService.getById(timeForCallUser.getUserId());
            checkTime(timeForCallUser);
            separateTime(timeForCallUser, user.getInterviewTime());
        });
        timeForCallUserRepository.saveAll(mapper.map(resultUSerTimeList));
    }

    private void checkTime(TimeForCallUserDTO time) {
        if (time.getStartHour().getMinute() > 0 && time.getStartHour().getMinute() < 30) {
            LocalDateTime userTime = time.getStartHour();
            time.setStartHour(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour(),
                    DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY));
        }
        if (time.getStartHour().getMinute() > 30) {
            LocalDateTime userTime = time.getStartHour();
            time.setStartHour(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour() + 1,
                    DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO));
        }
        if (time.getEndHour().getMinute() > 0 && time.getEndHour().getMinute() < 30){
            LocalDateTime userTime = time.getEndHour();
            time.setEndHour(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour(),
                    DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY));
        }
        if (time.getEndHour().getMinute() > 30) {
            LocalDateTime userTime = time.getEndHour();
            time.setEndHour(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour() + 1,
                    DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO));
        }


    }

    private void separateTime(TimeForCallUserDTO time, InterviewTime interviewTimeUser) {
        List<TimeForCallUserDTO> newUserTimeList = new ArrayList<>();
        Duration duration = Duration.between(time.getStartHour(),time.getEndHour());
        long numberOfPeriods = determineTime(duration,interviewTimeUser);
        for (int i = 0; i < numberOfPeriods; i++){
            TimeForCallUserDTO tempUserTime = new TimeForCallUserDTO();
            tempUserTime.setUserId(time.getUserId());
            tempUserTime.setStartHour(time.getStartHour());
            tempUserTime.setEndHour(tempUserTime.getStartHour().plusMinutes(INTERVIEW_TIME));
            newUserTimeList.add(tempUserTime);
            time.setStartHour(tempUserTime.getEndHour());
        }
        resultUSerTimeList.addAll(newUserTimeList);
    }

    private long determineTime(Duration duration, InterviewTime interviewTimeUser){
        if (interviewTimeUser.equals(InterviewTime.HALF_HOUR)){
            INTERVIEW_TIME = 30;
            return duration.toMinutes()/30;
        }
        if (interviewTimeUser.equals(InterviewTime.HOUR)){
            INTERVIEW_TIME = 60;
            return duration.toMinutes()/60;
        }
        if (interviewTimeUser.equals(InterviewTime.HOUR_HALF)){
            INTERVIEW_TIME = 90;
            return duration.toMinutes()/90;
        }
        return duration.toMinutes()/30;
    }
}
