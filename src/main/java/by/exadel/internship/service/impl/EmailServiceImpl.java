package by.exadel.internship.service.impl;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.mail.EmailProperties;
import by.exadel.internship.mail.EmailTemplate;
import by.exadel.internship.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailProperties emailProperties;

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendSimpleMessage(FormRegisterDTO formRegisterDTO) {
        EmailTemplate emailTemplate = new EmailTemplate();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailTemplate.getFrom());
        message.setTo(formRegisterDTO.getEmail());
        message.setSubject(emailTemplate.getSubject());
        message.setText(emailTemplate.getText());

        getJavaMailSender().send(message);
    }
}
