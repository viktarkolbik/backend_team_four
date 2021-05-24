package by.exadel.internship.service;



import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;

import java.time.LocalDateTime;

public interface EmailService {

    boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO);
    void sendInterviewDateOnEmail(String formMail, String userMail,
                                  UserRole userRole, LocalDateTime dateTime,
                                  int interviewTime);
}
