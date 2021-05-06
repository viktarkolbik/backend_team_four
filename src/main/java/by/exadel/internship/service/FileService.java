package by.exadel.internship.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(byte[] fileContent, String originalFileName);
}
