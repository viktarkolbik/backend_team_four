package by.exadel.internship.service;


import by.exadel.internship.dto.formDTO.FormRegisterDTO;

public interface EmailService {

    boolean sendSimpleMessage(FormRegisterDTO formRegisterDTO);
}
