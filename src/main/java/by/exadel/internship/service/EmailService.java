package by.exadel.internship.service;


import by.exadel.internship.dto.form.FormRegisterDTO;

public interface EmailService {

    boolean sendFormSubmissionEmail(FormRegisterDTO formRegisterDTO);
}
