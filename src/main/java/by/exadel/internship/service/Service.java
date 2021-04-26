package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface Service {

    void sendEmail(FormRegisterDTO form) throws JsonProcessingException;

}