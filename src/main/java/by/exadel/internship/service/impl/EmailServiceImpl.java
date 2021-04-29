package by.exadel.internship.service.impl;

import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.mail.EmailTemplate;
import by.exadel.internship.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NAME = "${name}";
    private final JavaMailSenderImpl mailSender;

    public boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        String text = EmailTemplate.TEXT.replace(NAME, formRegisterDTO.getFirstName());
        message.setFrom(EmailTemplate.FROM);
        message.setTo(formRegisterDTO.getEmail());
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