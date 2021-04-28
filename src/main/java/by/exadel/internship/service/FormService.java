package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.pojo.FeedbackRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FormService {

    FormFullDTO process(FormRegisterDTO form, MultipartFile file);

    List<FormFullDTO> getAll();

    FormFullDTO getById(UUID formId);

    void deleteById(UUID formId);

    void restoreFormById(UUID formId);

    void updateForm(FormFullDTO formFullDTO);

    List<FormFullDTO> getAllByInternshipId(UUID internshipId);

    void updateFeedback(UUID formId, FeedbackRequest feedbackRequest);
}
