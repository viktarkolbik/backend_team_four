package by.exadel.internship.service.impl;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.mail.EmailProperties;
import by.exadel.internship.mail.EmailTemplate;
import by.exadel.internship.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailProperties emailProperties;
    private JavaMailSenderImpl mailSender;

    private static final Map<String,String> MAIL_PROPS = Map.of("mail.transport.protocol", "smtp",
                                                                "mail.smtp.auth", "true",
                                                                "mail.smtp.starttls.enable", "true",
                                                                "mail.debug", "true");

    @PostConstruct
    public void init() {
        mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.putAll(MAIL_PROPS);
    }

    public boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailTemplate.FROM);
        message.setTo(formRegisterDTO.getEmail());
        message.setSubject(EmailTemplate.SUBJECT);
        message.setText(EmailTemplate.TEXT);

        try {
            mailSender.send(message);
            return true;
        }catch (MailException e){
            log.error(e.getMessage());
            return false;
        }
    }
}