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

    private static final Logger logger =
            LoggerFactory.getLogger("by.exadel.service");

    private final FormMapper mapper;

    private final FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO) {

        Form form = mapper.toFormEntity(formRegisterDTO);
        formRepository.save(form);

    }
}