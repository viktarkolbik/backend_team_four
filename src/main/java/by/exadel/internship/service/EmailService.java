package by.exadel.internship.service;


import by.exadel.internship.dto.CalendarRequest;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;

import java.time.LocalDateTime;

public interface EmailService {

    boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO);
    void sendHRInterviewEmail(FormFullDTO formFullDTO, LocalDateTime dateTime);
    void sendTechInterviewEmail(FormFullDTO formFullDTO, LocalDateTime dateTime);
    void sendCalendarInvite(String fromEmail, CalendarRequest calendarRequest) throws Exception;
}
