package by.exadel.internship.service.impl;

import by.exadel.internship.config.CalendarConfig;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mail.EmailTemplate;
import by.exadel.internship.service.EmailService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NAME = "${name}";
    private static final String CALENDAR_ID = "primary";
    private static final String SEND_NOTIFICATION_ALL = "all";
    private static final String DEFAULT_TIME_ZONE = "Europe/Minsk";

    private final JavaMailSenderImpl mailSender;
    private final CalendarConfig calendarConfig;

    @Override
    public boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO) {
        String text = EmailTemplate.TEXT.replace(NAME, formRegisterDTO.getFirstName());
        return defaultSetupForMail(text, formRegisterDTO.getEmail());
    }

    private void setDefaultConfigInEvent(String summary,
                                         String description,
                                         LocalDateTime startTime,
                                         LocalDateTime finishTime,
                                         List<String> emails) {

        Calendar service = calendarConfig.getDefaultCalendar()
                .orElseThrow(() -> new NotFoundException("Unsuccessful attempt to get calendar", "calendar.invalid"));

        Event event = new Event();
        event.setSummary(summary);
        event.setDescription(description);
        event.setLocation(DEFAULT_TIME_ZONE);

        Date startDate = Timestamp.valueOf(startTime);

        DateTime startDateTime = new DateTime(startDate);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone(DEFAULT_TIME_ZONE);
        event.setStart(start);

        Date endDate = Timestamp.valueOf(finishTime);

        DateTime endDateTime = new DateTime(endDate);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone(DEFAULT_TIME_ZONE);
        event.setEnd(end);

        List<EventAttendee> attendees = new ArrayList<>();
        emails.forEach(email -> attendees.add(new EventAttendee().setEmail(email)));
        event.setAttendees(attendees);

        try {
            event = service.events().insert(CALENDAR_ID, event).setSendUpdates(SEND_NOTIFICATION_ALL).execute();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Cannot create event in calendar");
        }
        log.info("Event created : {}", event.getHtmlLink());

    }

    @Override
    public void sendInterviewDateOnEmail(String formMail, String userMail,
                                         UserRole userRole, LocalDateTime dateTime,
                                         int interviewTime) {

        if (userRole == UserRole.TECH_EXPERT) {
            setDefaultConfigInEvent(EmailTemplate.TECH_INTERVIEW_SUMMARY,
                    EmailTemplate.TECH_INTERVIEW_EMAIL, dateTime,
                    dateTime.plusMinutes(interviewTime),
                    new ArrayList<>() {{
                        add(userMail);
                        add(formMail);
                    }});
        } else {
            setDefaultConfigInEvent(EmailTemplate.HR_INTERVIEW_SUMMARY,
                    EmailTemplate.HR_INTERVIEW_EMAIL, dateTime,
                    dateTime.plusMinutes(interviewTime),
                    new ArrayList<>() {{
                        add(userMail);
                        add(formMail);
                    }});
        }
    }

    private boolean defaultSetupForMail(String text, String formEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailTemplate.FROM);
        message.setTo(formEmail);
        message.setSubject(EmailTemplate.SUBJECT);
        message.setText(text);

        try {
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}