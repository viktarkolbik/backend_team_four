package by.exadel.internship.service;

import by.exadel.internship.dto.timeForCall.TimeForCallDTO;

import java.util.List;
import java.util.UUID;

public interface TimeForCallService {
    List<TimeForCallDTO> getAll();
    TimeForCallDTO getByFormId(UUID formId);
}
