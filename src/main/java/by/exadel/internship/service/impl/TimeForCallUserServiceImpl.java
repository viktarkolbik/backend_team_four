package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.TimeForCallWithUserDTO;
import by.exadel.internship.dto.TimeForCallWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.InterviewTime;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.entity.User;
import by.exadel.internship.mapper.TimeForCallUserMapper;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.repository.TimeForCallUserRepository;
import by.exadel.internship.repository.UserRepository;
import by.exadel.internship.service.TimeForCallUserServise;
import by.exadel.internship.service.UserService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TimeForCallUserServiceImpl implements TimeForCallUserServise {

    private final TimeForCallUserRepository timeForCallUserRepository;
    private final UserService userService;
    private final TimeForCallUserMapper mapper;

    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY = 30;
    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO = 0;
    private static int INTERVIEW_TIME = 30;
    private static final String SIMPLE_CLASS_NAME = TimeForCallUserServise.class.getSimpleName();


    private List<TimeForCallWithUserDTO> resultUSerTimeList;

    @Override
    public void saveUserTime(UserDTO userDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save free time list for user with uuid = {} in DB", userDTO.getId());
        resultUSerTimeList = new ArrayList<>();
        userDTO.getTimeForCall().forEach(timeForCallUser -> {
            checkTime(timeForCallUser);
            separateTime(timeForCallUser, userDTO.getInterviewTime());
        });
        resultUSerTimeList.forEach(time -> {
            time.setUser(userDTO);
        });
        timeForCallUserRepository.saveAll(mapper.mapToEntity(resultUSerTimeList));
        log.info("Free time list was saved in DB, user uuid = {}", userDTO.getId());
    }

    @Override
    public void deletedById(UUID timeId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to remove time with uuid = {} from user free time", timeId);
        timeForCallUserRepository.deleteById(timeId);
        log.info("Time with uuid = {} was deleted", timeId);
    }

    @Override
    public void restoreUserTime(TimeForCallWithUserIdDTO time) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        UserDTO userDTO = userService.getById(time.getUserId());
        TimeForCallWithUserDTO timeForCallWithUserDTO = new TimeForCallWithUserDTO();
        timeForCallWithUserDTO.setUser(userDTO);
        timeForCallWithUserDTO.setStartHour(time.getStartHour());
        switch (userDTO.getInterviewTime()) {
            case HALF_HOUR: {
                timeForCallWithUserDTO.setEndHour(time.getStartHour().plusMinutes(30));
                break;
            }
            case HOUR: {
                timeForCallWithUserDTO.setEndHour(time.getStartHour().plusMinutes(60));
                break;
            }
            case HOUR_HALF:{
                timeForCallWithUserDTO.setEndHour(time.getStartHour().plusMinutes(90));
                break;
            }
        }
        timeForCallUserRepository.save(mapper.toTimeForCallUserEntity(timeForCallWithUserDTO));
    }

    private void checkTime(TimeForCallUserDTO time) {
        log.info("Bringing time to a common form");
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
        if (time.getEndHour().getMinute() > 0 && time.getEndHour().getMinute() < 30) {
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
        System.out.println("Time brought to a common form");

    }

    private void separateTime(TimeForCallUserDTO time, InterviewTime interviewTimeUser) {
        log.info("Separate time on part");
        List<TimeForCallWithUserDTO> newUserTimeList = new ArrayList<>();
        Duration duration = Duration.between(time.getStartHour(), time.getEndHour());
        long numberOfPeriods = determineTime(duration, interviewTimeUser);
        for (int i = 0; i < numberOfPeriods; i++) {
            TimeForCallWithUserDTO tempUserTime = new TimeForCallWithUserDTO();
            tempUserTime.setStartHour(time.getStartHour());
            tempUserTime.setEndHour(tempUserTime.getStartHour().plusMinutes(INTERVIEW_TIME));
            newUserTimeList.add(tempUserTime);
            time.setStartHour(tempUserTime.getEndHour());
        }
        resultUSerTimeList.addAll(newUserTimeList);
        System.out.println("Time separated on part");
    }

    private long determineTime(Duration duration, InterviewTime interviewTimeUser) {
        log.info("Return of temporary interview");
        if (interviewTimeUser.equals(InterviewTime.HALF_HOUR)) {
            INTERVIEW_TIME = 30;
            return duration.toMinutes() / INTERVIEW_TIME;
        }
        if (interviewTimeUser.equals(InterviewTime.HOUR)) {
            INTERVIEW_TIME = 60;
            return duration.toMinutes() / INTERVIEW_TIME;
        }
        if (interviewTimeUser.equals(InterviewTime.HOUR_HALF)) {
            INTERVIEW_TIME = 90;
            return duration.toMinutes() / INTERVIEW_TIME;
        }
        return duration.toMinutes() / 30;
    }
}
