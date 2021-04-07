package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormService {

    private static final Logger infoLogger =
            LoggerFactory.getLogger("by.exadel.service.info");
    private static final Logger warningLogger =
            LoggerFactory.getLogger("by.exadel.service.warning");
    private static final Logger errorLogger =
            LoggerFactory.getLogger("by.exadel.service.error");

    private final FormMapper mapper;

    private final FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO) {

        Form form = mapper.toFormEntity(formRegisterDTO);
        formRepository.save(form);

    }
}