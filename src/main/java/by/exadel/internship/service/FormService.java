package by.exadel.internship.service;


import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FormService {

    @Autowired
    public FormMapper mapper;

    @Autowired
    public FormRepository formRepository;

    public void saveForm(FormRegisterDTO formRegisterDTO){
        Form form = mapper.toFormEntity(formRegisterDTO);
        formRepository.save(form);
    }
}
