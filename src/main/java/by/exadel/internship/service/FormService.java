package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    @Value("${file.path}")
    private String filePath;

    public Form process(FormRegisterDTO form, MultipartFile file) {
        if (file != null) {
            form.setFilePath(filePath + file.getOriginalFilename());
            Form createdForm = saveForm(form);
            uploadFile(file, createdForm);
            return createdForm;
        }
        return saveForm(form);
    }

    private Form saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        return formRepository.save(form);
    }

    private void uploadFile(MultipartFile file, Form createdForm) {
        try {
            Path path = Paths.get(filePath + createdForm.getId());
            Files.createDirectories(path);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream
                            (new File(filePath + createdForm.getId() +
                                    File.separator + file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}