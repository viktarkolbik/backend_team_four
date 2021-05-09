package by.exadel.internship.service;

import by.exadel.internship.dto.FeedbackRequest;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormFullWithInterviewFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
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

    List<FormFullWithInterviewFullDTO> getAllByInternshipId(UUID internshipId);

    void updateFeedback(UUID formId, FeedbackRequest feedbackRequest);
    void updateStatusById(UUID formId, FormStatus status);
}
