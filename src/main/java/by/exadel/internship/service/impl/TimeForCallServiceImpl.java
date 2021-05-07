package by.exadel.internship.service.impl;

import by.exadel.internship.dto.time_for_call.TimeForCallDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.TimeForCallMapper;
import by.exadel.internship.repository.TimeForCallRepository;
import by.exadel.internship.service.TimeForCallService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TimeForCallServiceImpl implements TimeForCallService {

    private final TimeForCallRepository timeForCallRepository;
    private final TimeForCallMapper mapper;

    private static final String SIMPLE_CLASS_NAME = TimeForCallService.class.getSimpleName();

    @Override
    public List<TimeForCallDTO> getAll() {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all TimeForCall");
        List<TimeForCall> timeForCalls = timeForCallRepository.findAll();
        log.info("Return all TimeForCall");
        return mapper.mapToDTO(timeForCalls);
    }

    @Override
    public TimeForCallDTO getByFormId(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get Form TimeForCall");
        TimeForCall timeForCall = timeForCallRepository.findByFormId(formId)
                .orElseThrow(() -> new NotFoundException("No such TimeForCall with FormId = " + formId + " in DB",
                        "formId.invalid"));
        log.info("Return Form TimeForCall");
        return mapper.toTimeForCallDTO(timeForCall);
    }
}
