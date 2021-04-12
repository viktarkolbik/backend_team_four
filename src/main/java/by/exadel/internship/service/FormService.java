package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO) {

        Form form = mapper.toFormEntity(formRegisterDTO);
        formRepository.save(form);

    }

    public void doActiveDeletedFormById(UUID formId){
        putClassNameInMDC();
        log.info("Try to activate form with uuid= " + formId);
        formRepository.updateDeletedById(formId);
        log.info("Successfully returned deleted Form with uuid= " + formId);
    }

    public void deleteById(UUID formId){
        putClassNameInMDC();
        log.info("Try to delete form with uuid= " + formId);
        formRepository.deleteById(formId);
        log.info("Successfully deleted Form with uuid= " + formId);
    }

    private void putClassNameInMDC(){
        MDC.put("className", FormService.class.getSimpleName());
    }
}