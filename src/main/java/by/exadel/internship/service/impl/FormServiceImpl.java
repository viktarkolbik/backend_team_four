package by.exadel.internship.service.impl;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.service.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormServiceImpl implements FormService {

    private final FormMapper MAPPER;
    private final FormRepository FORM_REPOSITORY;

    @Value("${file.path}")
    private String filePath;

    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {

        MDC.put("className", FormService.class.getSimpleName());

        if (file != null) {

            form.setFilePath(file.getOriginalFilename());
            FormFullDTO createdForm = saveForm(form);

            log.info("Success to save form, id: {}",createdForm.getId());

            uploadFile(file, createdForm.getId());

            return createdForm;
        }
        FormFullDTO createdForm = saveForm(form);

        log.info("Success to save form, id: {}", createdForm.getId());

        return createdForm;
    }

    private FormFullDTO saveForm(FormRegisterDTO formRegisterDTO) {

        Form form = MAPPER.toFormEntity(formRegisterDTO);
        form.setFormStatus(FormStatus.REGISTERED);

        log.info("The form status is {}", FormStatus.REGISTERED);

        FORM_REPOSITORY.save(form);

        return MAPPER.toFormDto(form);

    }

    private void uploadFile(MultipartFile file, UUID id) {

        try {
            Path path = Paths.get(filePath, id.toString());
            Files.createDirectories(path);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream
                            (new File(filePath + File.separator + id +
                                    File.separator + file.getOriginalFilename())));
            stream.write(bytes);

            log.info("Success to upload file, form id: {}", id);

            stream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<FormFullDTO> getAll() {

        MDC.put("className", FormService.class.getSimpleName());
        log.info("Try to get all forms");

        List<Form> formList = FORM_REPOSITORY.findAllWithTimeForCallList();

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = MAPPER.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }
}