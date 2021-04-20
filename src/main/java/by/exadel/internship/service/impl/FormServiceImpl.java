package by.exadel.internship.service.impl;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.service.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class FormServiceImpl implements FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    @Value("${file.path}")
    private String filePath;

    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {

        MDC.put("className", FormService.class.getSimpleName());

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

            File file = new File(filePath + File.separator + id +
                    File.separator + multipartFile.getOriginalFilename());

            FileUtils.forceMkdirParent(file);

            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

            log.info("Success to upload file, form id: {}", id);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<FormFullDTO> getAll() {

        MDC.put("className", FormService.class.getSimpleName());
        log.info("Try to get all forms");

        List<Form> formList = formRepository.findAllWithTimeForCallList();

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }


    public List<FormFullDTO> getAllByInternshipId(UUID internshipId) {

        MDC.put("className", FormService.class.getSimpleName());
        log.info("Try to get all forms by internship id");

        List<Form> formList = formRepository.findAllByInternship(internshipId);

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }
}