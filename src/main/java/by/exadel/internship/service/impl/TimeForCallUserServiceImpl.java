package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.mapper.TimeForCallUserMapper;
import by.exadel.internship.repository.TimeForCallUserRepository;
import by.exadel.internship.service.TimeForCallUserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TimeForCallUserServiceImpl implements TimeForCallUserServise {

    private final TimeForCallUserRepository timeForCallUserRepository;
    private final TimeForCallUserMapper mapper;

    @Override
    public List<TimeForCallUserDTO> getAll() {
        List<TimeForCallUser> timeForCallUsers = timeForCallUserRepository.findAll();
        timeForCallUsers.forEach(item -> item.setUser(null));
        return mapper.mapToDTO(timeForCallUsers);
    }
}
