package by.exadel.internship.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(byte[] fileContent, String originalFileName);
    String download(String fileName, String formLastName);
    ByteArrayResource getFile(String filePath);
}
