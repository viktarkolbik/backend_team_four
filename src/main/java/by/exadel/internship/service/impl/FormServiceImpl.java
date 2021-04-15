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

    private final FormMapper mapper;
    private final FormRepository formRepository;

    @Value("${file.path}")
    private String filePath;

    @Override
    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {
        MDC.put("className", FormServiceImpl.class.getSimpleName());
        if (file != null) {
            form.setFilePath(file.getOriginalFilename());
            FormFullDTO createdForm = saveForm(form);
            log.info("Success to save form, id: "+ createdForm.getId());
            uploadFile(file, createdForm.getId());
            return createdForm;
        }
        FormFullDTO createdForm = saveForm(form);
        log.info("Success to save form, id: "+ createdForm.getId());
        return createdForm;
    }

    private FormFullDTO saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        form.setFormStatus(FormStatus.REGISTERED);
        log.info("The form status is " + FormStatus.REGISTERED);
        formRepository.save(form);
        return mapper.toFormDto(form);
    }

    private void uploadFile(MultipartFile file, UUID uuid) {
        try {
            Path path = Paths.get(filePath + File.separator + uuid);
            Files.createDirectories(path);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream
                            (new File(filePath + File.separator + uuid +
                                    File.separator + file.getOriginalFilename())));
            stream.write(bytes);
            log.info("Success to upload file, form id: "+ uuid);
            stream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Form> getAll() {
        return formRepository.findAll();
    }
}