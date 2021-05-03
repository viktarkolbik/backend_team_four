package by.exadel.internship.service.impl;


import by.exadel.internship.dto.timeForCall.UserTimeSlotWithUserDTO;
import by.exadel.internship.dto.timeForCall.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.timeForCall.UserTimeSlotDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.InterviewTime;
import by.exadel.internship.mapper.UserTimeSlotMapper;
import by.exadel.internship.repository.UserTimeSlotRepository;
import by.exadel.internship.service.UserTimeSlotService;
import by.exadel.internship.service.UserService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserTimeSlotServiceImpl implements UserTimeSlotService {

    private final UserTimeSlotRepository userTimeSlotRepository;
    private final UserService userService;
    private final UserTimeSlotMapper mapper;

    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY = 30;
    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO = 0;
    private static int INTERVIEW_TIME = 30;
    private static final String SIMPLE_CLASS_NAME = UserTimeSlotService.class.getSimpleName();


    private List<UserTimeSlotWithUserDTO> resultUSerTimeList;

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
        userTimeSlotRepository.saveAll(mapper.mapToEntity(resultUSerTimeList));
        log.info("Free time list was saved in DB, user uuid = {}", userDTO.getId());
    }

    @Override
    public void deletedById(UUID timeId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to remove time with uuid = {} from user free time", timeId);
        userTimeSlotRepository.deleteById(timeId);
        log.info("Time with uuid = {} was deleted", timeId);
    }

    @Override
    public void restoreUserTime(UserTimeSlotWithUserIdDTO time) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Restoring time with uuid = {} for user with uuid ={}", time.getId(), time.getUserId());
        UserDTO userDTO = userService.getById(time.getUserId());
        UserTimeSlotWithUserDTO userTimeSlotWithUserDTO = new UserTimeSlotWithUserDTO();
        userTimeSlotWithUserDTO.setUser(userDTO);
        userTimeSlotWithUserDTO.setStartDate(time.getStartDate());
        switch (userDTO.getInterviewTime()) {
            case HALF_HOUR: {
                userTimeSlotWithUserDTO.setEndDate(time.getStartDate().plusMinutes(30));
                break;
            }
            case HOUR: {
                userTimeSlotWithUserDTO.setEndDate(time.getStartDate().plusMinutes(60));
                break;
            }
            case HOUR_HALF: {
                userTimeSlotWithUserDTO.setEndDate(time.getStartDate().plusMinutes(90));
                break;
            }
        }
        userTimeSlotRepository.save(mapper.toTimeForCallUserEntity(userTimeSlotWithUserDTO));
        log.info("User time was restoring");
    }

    private void checkTime(UserTimeSlotDTO time) {
        log.info("Bringing time to a common form");
        if (time.getStartDate().getMinute() > 0 && time.getStartDate().getMinute() < 30) {
            LocalDateTime userTime = time.getStartDate();
            time.setStartDate(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour(),
                    DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY));
        }
        if (time.getStartDate().getMinute() > 30) {
            LocalDateTime userTime = time.getStartDate();
            time.setStartDate(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour() + 1,
                    DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO));
        }
        if (time.getEndDate().getMinute() > 0 && time.getEndDate().getMinute() < 30) {
            LocalDateTime userTime = time.getEndDate();
            time.setEndDate(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour(),
                    DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY));
        }
        if (time.getEndDate().getMinute() > 30) {
            LocalDateTime userTime = time.getEndDate();
            time.setEndDate(LocalDateTime.of(userTime.getYear(), userTime.getMonth(),
                    userTime.getDayOfMonth(), userTime.getHour() + 1,
                    DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO));
        }
        log.info("Time brought to a common form");
    }

    private void separateTime(UserTimeSlotDTO time, InterviewTime interviewTimeUser) {
        log.info("Separate time on part");
        List<UserTimeSlotWithUserDTO> newUserTimeList = new ArrayList<>();
        Duration duration = Duration.between(time.getStartDate(), time.getEndDate());
        long numberOfPeriods = determineTime(duration, interviewTimeUser);
        for (int i = 0; i < numberOfPeriods; i++) {
            UserTimeSlotWithUserDTO tempUserTime = new UserTimeSlotWithUserDTO();
            tempUserTime.setStartDate(time.getStartDate());
            tempUserTime.setEndDate(tempUserTime.getStartDate().plusMinutes(INTERVIEW_TIME));
            newUserTimeList.add(tempUserTime);
            time.setStartDate(tempUserTime.getEndDate());
        }
        resultUSerTimeList.addAll(newUserTimeList);
        log.info("Time separated on part");
    }

    private long determineTime(Duration duration, InterviewTime interviewTimeUser) {
        log.info("Return of temporary interview");
        switch (interviewTimeUser) {
            case HALF_HOUR: {
                INTERVIEW_TIME = 30;
                return duration.toMinutes() / INTERVIEW_TIME;
            }
            case HOUR: {
                INTERVIEW_TIME = 60;
                return duration.toMinutes() / INTERVIEW_TIME;
            }
            case HOUR_HALF: {
                INTERVIEW_TIME = 90;
                return duration.toMinutes() / INTERVIEW_TIME;
            }
            default: {
                return duration.toMinutes() / 30;
            }
        }

    }
}
