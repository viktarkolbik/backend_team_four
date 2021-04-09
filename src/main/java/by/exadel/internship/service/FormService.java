package by.exadel.internship.service;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    public Form saveForm(FormRegisterDTO formRegisterDTO) {
        Form form = mapper.toFormEntity(formRegisterDTO);
        return formRepository.save(form);
    }

    public void uploadFile(MultipartFile file) {
        try {
            Path path = Paths.get("src/main/resources/files/");
            Files.createDirectories(path);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream
                            (new File("src/main/resources/files/" + file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}