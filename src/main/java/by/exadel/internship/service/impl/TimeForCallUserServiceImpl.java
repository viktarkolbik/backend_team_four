package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.InterviewTime;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.mapper.TimeForCallUserMapper;
import by.exadel.internship.repository.TimeForCallUserRepository;
import by.exadel.internship.service.TimeForCallUserServise;
import by.exadel.internship.service.UserService;
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
    private static final String HALF_HOUR = "HALF_HOUR";
    private static final String HOUR = "HOUR";
    private static final String HOUR_HALF = "HOUR_HALF";


    private int interviewTime = 30;
    private List<TimeForCallUserDTO> resultUSerTimeList;

    @Override
    public List<TimeForCallUserDTO> getAll() {
        List<TimeForCallUser> timeForCallUsers = timeForCallUserRepository.findAll();
        return mapper.mapToDTO(timeForCallUsers);
    }

    @Override
    public void updateTime(TimeForCallUserDTO time) {
        TimeForCallUser timeForCallUser = mapper.toTimeForCallUserEntity(time);
        timeForCallUserRepository.save(timeForCallUser);
    }

    @Override
    public List<TimeForCallUserDTO> getAllByUserId(UUID userId) {
        List<TimeForCallUser> timeForCallUsers = timeForCallUserRepository.findAllByUserId(userId);
        return mapper.mapToDTO(timeForCallUsers);
    }

    @Override
    public void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList) {
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
            tempUserTime.setEndHour(tempUserTime.getStartHour().plusMinutes(interviewTime));
            newUserTimeList.add(tempUserTime);
            time.setStartHour(tempUserTime.getEndHour());
        }
        resultUSerTimeList.addAll(newUserTimeList);
    }

    private long determineTime(Duration duration, InterviewTime interviewTimeUser){
        if (interviewTimeUser.name().equals(HALF_HOUR)){
            interviewTime = 30;
            return duration.toMinutes()/30;
        }
        if (interviewTimeUser.name().equals(HOUR)){
            interviewTime = 60;
            return duration.toMinutes()/60;
        }
        if (interviewTimeUser.name().equals(HOUR_HALF)){
            interviewTime = 90;
            return duration.toMinutes()/90;
        }
        return duration.toMinutes()/30;
    }
}
