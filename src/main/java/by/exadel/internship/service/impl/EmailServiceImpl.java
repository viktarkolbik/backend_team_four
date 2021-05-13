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
}