package by.exadel.internship.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    private String from = "test.exadel.test@gmail.com";
    private String subject = "Test Exadel";
    private String text = "Your form has been successfully submitted.";
}
