package by.exadel.internship.service;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormService {

    private final FormMapper mapper;

    private final FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        form.setFormStatus(FormStatus.REGISTERED);
        log.info("The form status is " + FormStatus.REGISTERED);
        formRepository.save(form);
    }

    public List<Form> getAll() {
        return formRepository.findAll();
    }
}