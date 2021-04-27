package by.exadel.internship.service.impl;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.TimeForCallMapper;
import by.exadel.internship.repository.TimeForCallRepository;
import by.exadel.internship.service.TimeForCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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

    @Override
    public TimeForCallDTO getByFormId(UUID formId) {
        TimeForCall timeForCall = timeForCallRepository.findByFormId(formId)
                .orElseThrow(() -> new NotFoundException("No such TimeForCall with FormId = " + formId + " in DB",
                        "formId.invalid"));
        return mapper.toTimeForCallDTO(timeForCall);
    }
}
