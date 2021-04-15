package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FormService {

    FormFullDTO process(FormRegisterDTO form, MultipartFile file);
    List<Form> getAll();
}
