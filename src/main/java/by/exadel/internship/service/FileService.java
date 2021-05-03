package by.exadel.internship.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Object upload(MultipartFile multipartFile);
}
