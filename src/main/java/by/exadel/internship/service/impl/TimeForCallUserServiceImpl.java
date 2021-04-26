package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.mapper.TimeForCallUserMapper;
import by.exadel.internship.repository.TimeForCallUserRepository;
import by.exadel.internship.service.TimeForCallUserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TimeForCallUserServiceImpl implements TimeForCallUserServise {

    private final TimeForCallUserRepository timeForCallUserRepository;
    private final TimeForCallUserMapper mapper;

    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_ZERO_THIRTY = 30;
    private static final int DEFAULT_START_MINUTES_IF_BETWEEN_THIRTY_ZERO = 0;
    private static final int INTERVIEW_TIME = 30;

    private List<TimeForCallUserDTO> resultUSerTimeList = new ArrayList<>();

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
    public void saveUserTime(List<TimeForCallUserDTO> timeForCallUserDTOList) {
        timeForCallUserDTOList.forEach(timeForCallUser -> {
            checkTime(timeForCallUser);
            separateTime(timeForCallUser);
        });
        System.out.println(resultUSerTimeList.size());
        resultUSerTimeList.forEach(timeForCallUserDTO -> {
            System.out.println("Start " + timeForCallUserDTO.getStartHour() + " End " + timeForCallUserDTO.getEndHour());
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

    private void separateTime(TimeForCallUserDTO time) {
        List<TimeForCallUserDTO> newUserTimeList = new ArrayList<>();
        Duration duration = Duration.between(time.getStartHour(),time.getEndHour());
        long numberOfPeriods = duration.toMinutes()/30;
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
}
