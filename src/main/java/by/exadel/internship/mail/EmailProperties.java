package by.exadel.internship.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
    @Value("${spring.mail.smtp.auth}")
    private String smtpAuth;
    @Value("${spring.mail.smtp.starttls.enable}")
    private String smtpStarttls;
    private String debug;

}
