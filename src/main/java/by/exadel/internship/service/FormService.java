package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormMapper mapper;

    private final FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        formRepository.save(form);
    }

    public List<Form> getAll() {
        return formRepository.findAll();
    }
}