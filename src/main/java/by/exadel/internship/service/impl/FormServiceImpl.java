package by.exadel.internship.service.impl;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.pojo.FeedbackRequest;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.service.FormService;
import by.exadel.internship.service.UserService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class FormServiceImpl implements FormService {

    private final FormMapper mapper;
    private final InterviewMapper interviewMapper;
    private final FormRepository formRepository;
    private final UserService userService;

    private static final String SIMPLE_CLASS_NAME = FormService.class.getSimpleName();

    @Value("${file.path}")
    private String filePath;

    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);

        if (file != null) {

            form.setFilePath(file.getOriginalFilename());
            FormFullDTO createdForm = saveForm(form);

            log.info("Success to save form, id: {}", createdForm.getId());

            uploadFile(file, createdForm.getId());

            return createdForm;
        }

        FormFullDTO createdForm = saveForm(form);

        log.info("Success to save form, id: {}", createdForm.getId());

        return createdForm;
    }

    private FormFullDTO saveForm(FormRegisterDTO formRegisterDTO) {

        Form form = mapper.toFormEntity(formRegisterDTO);
        form.setFormStatus(FormStatus.REGISTERED);

        log.info("The form status is {}", FormStatus.REGISTERED);

        formRepository.save(form);

        return mapper.toFormDto(form);

    }

    private void uploadFile(MultipartFile multipartFile, UUID id) {

        try {

            Path path = Paths.get(filePath, id.toString(), multipartFile.getOriginalFilename());

            FileUtils.forceMkdirParent(path.toFile());

            FileUtils.writeByteArrayToFile(path.toFile(), multipartFile.getBytes());

            log.info("Success to upload file, form id: {}", id);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<FormFullDTO> getAll() {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms");

        List<Form> formList = formRepository.findAllWithTimeForCallList();

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }

    @Override
    public FormFullDTO getById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get From with uuid = {}", formId);
        Form form = formRepository.findByIdAndDeletedFalse(formId).
                orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        log.info("Return formFullDTO with uuid = {}", formId);
        return mapper.toFormDto(form);
    }


    public List<FormFullDTO> getAllByInternshipId(UUID internshipId) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms by internship id");

        List<Form> formList = formRepository.findAllByInternship(internshipId);

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }

    @Override
    public void updateFeedback(UUID formId, FeedbackRequest feedbackRequest) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save feedback for Form with uuid = {}", formId);
        Form form = formRepository.findByIdAndDeletedFalse(formId).
                orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        UserDTO userDTO = userService.getById(feedbackRequest.getUserId());
        setFeedbackByUserRole(userDTO,form,feedbackRequest);

    }

    private void setFeedbackByUserRole(UserDTO userDTO, Form form, FeedbackRequest feedbackRequest){
        if (userDTO.getUserRole().equals(UserRole.ADMIN)){
            if (form.getInterview().getAdmin().equals(feedbackRequest.getUserId())){
                form.getInterview().setAdminFeedback(feedbackRequest.getFeedback());
                form.setFormStatus(FormStatus.ADMIN_INTERVIEW_PASSED);
                formRepository.save(form);
            }else{
                throw new NotFoundException("Admin with uuid = "
                        + userDTO.getId() + " did not interview Form with uuid = "
                        + form.getId(), "user.uuid.invalid");
            }
        }
        if (userDTO.getUserRole().equals(UserRole.TECH_EXPERT)){
            if (form.getInterview().getTechSpecialist().equals(feedbackRequest.getUserId())){
                form.getInterview().setTechFeedback(feedbackRequest.getFeedback());
                form.setFormStatus(FormStatus.TECH_INTERVIEW_PASSED);
                formRepository.save(form);
            }else{
                throw new NotFoundException("Tech expert with uuid = "
                        + userDTO.getId() + " did not interview Form with uuid = "
                        + form.getId(), "user.uuid.invalid");
            }
        }
    }

    public void restoreFormById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to activate form with uuid: {}", formId);
        formRepository
                .findByIdAndDeletedTrue(formId)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        formRepository.activateFormById(formId);
        log.info("Successfully returned deleted Form with uuid: {}", formId);
    }

    @Override
    public void updateForm(FormFullDTO formFullDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to update Form with uuid = {}", formFullDTO.getId());
        Form form = formRepository.findByIdAndDeletedFalse(formFullDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formFullDTO.getId() +
                " Not Found in DB", "form.uuid.invalid"));
        form.setFormStatus(formFullDTO.getFormStatus());
        Interview interview = interviewMapper.toInterview(formFullDTO.getInterview());
        form.setInterview(interview);
        formRepository.save(form);
        log.info("Successfully saved Form with uuid = {} and Interview with uuid = {}",
                form.getId(),interview.getId());
    }


    public void deleteById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to delete form with uuid: {} ", formId);
        formRepository
                .findByIdAndDeletedFalse(formId)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        formRepository.deleteById(formId);
        log.info("Successfully deleted Form with uuid: {}", formId);
    }

}