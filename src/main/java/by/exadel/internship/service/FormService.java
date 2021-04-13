package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    @Value("${file.path}")
    private String filePath;

    public Form process(FormRegisterDTO form, MultipartFile file) {
        MDC.put("className", FormService.class.getSimpleName());

        if (file != null) {
            Form createdForm = saveForm(form);
            updateFilePath(createdForm, file);
            uploadFile(file, createdForm);
            log.info("Success to save form with file");
            return createdForm;
        }
        log.info("Success to save form without file");
        return saveForm(form);
    }

    private Form saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        return formRepository.save(form);
    }

    private void uploadFile(MultipartFile file, Form createdForm) {
        try {
            Path path = Paths.get(filePath + File.separator + createdForm.getId());
            Files.createDirectories(path);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream
                            (new File(filePath + createdForm.getId() +
                                    File.separator + file.getOriginalFilename())));
            stream.write(bytes);
            log.info("Success to upload file");
            stream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void updateFilePath(Form createdForm, MultipartFile file) {
        createdForm.setFilePath(filePath +
                createdForm.getId() + File.separator + file.getOriginalFilename());
        log.info("Success to update filePath");
        formRepository.save(createdForm);
    }

    public void restoreFormById(UUID formId){
        putClassNameInMDC();
        log.info("Try to activate form with uuid= " + formId);
        formRepository.updateDeletedById(formId);
        log.info("Successfully returned deleted Form with uuid= " + formId);
    }

    public void deleteById(UUID formId){
        putClassNameInMDC();
        log.info("Try to delete form with uuid= " + formId);
        formRepository.deleteById(formId);
        log.info("Successfully deleted Form with uuid= " + formId);
    }

    private void putClassNameInMDC(){
        MDC.put("className", FormService.class.getSimpleName());
    }
}