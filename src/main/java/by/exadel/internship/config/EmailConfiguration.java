package by.exadel.internship.config;

import by.exadel.internship.mail.EmailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

@Configuration
public class EmailConfiguration {

    private static final String PROTOCOL = "mail.transport.protocol";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String DEBUG = "mail.debug";

    @Bean
    public JavaMailSenderImpl javaMailSender(EmailProperties emailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Map<String, String> MAIL_PROPS = Map.of(PROTOCOL, emailProperties.getProtocol(),
                SMTP_AUTH, emailProperties.getSmtpAuth(),
                SMTP_STARTTLS, emailProperties.getSmtpStarttls(),
                DEBUG, emailProperties.getDebug());

        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.putAll(MAIL_PROPS);

        return mailSender;
    }
}
