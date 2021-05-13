package by.exadel.internship.service.impl;

import by.exadel.internship.dto.CalendarRequest;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.mail.EmailTemplate;
import by.exadel.internship.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NAME = "${name}";
    private static final String DATE = "${date}";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final JavaMailSenderImpl mailSender;

    @Override
    public boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO) {
        String text = EmailTemplate.TEXT.replace(NAME, formRegisterDTO.getFirstName());
        return defaultSetupForMail(text,formRegisterDTO.getEmail());
    }

    @Override
    public void sendHRInterviewEmail(FormFullDTO formFullDTO, LocalDateTime dateTime) {
        String text = EmailTemplate.HR_INTERVIEW_EMAIL.replace(NAME, formFullDTO.getFirstName());
        String formatDate = dateTime.format(DATE_TIME_FORMATTER);
        text = text.replace(DATE, formatDate);
        defaultSetupForMail(text, formFullDTO.getEmail());
    }

    @Override
    public void sendTechInterviewEmail(FormFullDTO formFullDTO, LocalDateTime dateTime) {
        String text = EmailTemplate.TECH_INTERVIEW_EMAIL.replace(NAME, formFullDTO.getFirstName());
        String formatDate = dateTime.format(DATE_TIME_FORMATTER);
        text = text.replace(DATE, formatDate);
        defaultSetupForMail(text, formFullDTO.getEmail());
    }

    private boolean defaultSetupForMail(String text, String formEmail){
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
    public void sendCalendarInvite(String fromEmail, CalendarRequest calendarRequest) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addHeaderLine("method=REQUEST");
        mimeMessage.addHeaderLine("charset=UTF-8");
        mimeMessage.addHeaderLine("component=VEVENT");
        mimeMessage.setFrom(new InternetAddress(fromEmail));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(calendarRequest.getToEmail()));
        mimeMessage.setSubject(calendarRequest.getSubject());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        StringBuilder builder = new StringBuilder();
        builder.append("BEGIN:VCALENDAR\n" +
                "METHOD:REQUEST\n" +
                "PRODID:Microsoft Exchange Server 2010\n" +
                "VERSION:2.0\n" +
                "BEGIN:VTIMEZONE\n" +
                "TZID:Europe/Minsk\n" +
                "END:VTIMEZONE\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + calendarRequest.getToEmail() + "\n" +
                "ORGANIZER;CN=Foo:MAILTO:" + fromEmail + "\n" +
                "DESCRIPTION;LANGUAGE=en-US:" + calendarRequest.getBody() + "\n" +
                "UID:"+calendarRequest.getUid()+"\n" +
                "SUMMARY;LANGUAGE=en-US:Discussion\n" +
                "DTSTART:" + formatter.format(calendarRequest.getMeetingStartTime()).replace(" ", "T") + "\n" +
                "DTEND:" + formatter.format(calendarRequest.getMeetingEndTime()).replace(" ", "T") + "\n" +
                "CLASS:PUBLIC\n" +
                "PRIORITY:5\n" +
                "DTSTAMP:20200922T105302Z\n" +
                "TRANSP:OPAQUE\n" +
                "STATUS:CONFIRMED\n" +
                "SEQUENCE:$sequenceNumber\n" +
                "LOCATION;LANGUAGE=en-US:Microsoft Teams Meeting\n" +
                "BEGIN:VALARM\n" +
                "DESCRIPTION:REMINDER\n" +
                "TRIGGER;RELATED=START:-PT15M\n" +
                "ACTION:DISPLAY\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(builder.toString(), "text/calendar;method=REQUEST;name=\"invite.ics\"")));

        MimeMultipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        mimeMessage.setContent(multipart);

        System.out.println(builder.toString());

        mailSender.send(mimeMessage);
        System.out.println("Calendar invite sent");

    }
}