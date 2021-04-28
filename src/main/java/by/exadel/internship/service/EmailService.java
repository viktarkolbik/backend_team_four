package by.exadel.internship.service;


import by.exadel.internship.dto.formDTO.FormRegisterDTO;

public interface EmailService {
    void sendSimpleMessage(FormRegisterDTO formRegisterDTO);
}
