package by.exadel.internship.service;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FormService {

    FormFullDTO process(FormRegisterDTO form, MultipartFile file);

    List<FormFullDTO> getAll();

    void deleteById(UUID formId);

    void restoreFormById(UUID formId);

    List<FormFullDTO> getAllByInternshipId(UUID internshipId);

    void updateStatusById(UUID formId, FormStatus status);
}
