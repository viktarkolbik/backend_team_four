package by.exadel.internship.service.impl;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.service.FormService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.MDC;
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
    private final FormRepository formRepository;

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


    public List<FormFullDTO> getAllByInternshipId(UUID internshipId) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms by internship id");

        List<Form> formList = formRepository.findAllByInternship(internshipId);

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

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