package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.entity.FormEntity;
import by.exadel.internship.mappers.FormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    public FormMapper mapper;

    public FormFullDTO convertToDTO(FormEntity formEntity){
        return mapper.toFormDto(formEntity);
    }

    public FormEntity convertToEntity(FormFullDTO formFullDTO){
        return mapper.toFormEntity(formFullDTO);
    }

}
