package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.mapper.TimeForCallMapper;
import by.exadel.internship.repository.TimeForCallRepository;
import by.exadel.internship.service.TimeForCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TimeForCallServiceImpl implements TimeForCallService {

    private final TimeForCallRepository timeForCallRepository;
    private final TimeForCallMapper mapper;

    @Override
    public List<TimeForCallDTO> getAll() {
        List<TimeForCall> timeForCalls = timeForCallRepository.findAll();
        return mapper.mapToDTO(timeForCalls);
    }
}
