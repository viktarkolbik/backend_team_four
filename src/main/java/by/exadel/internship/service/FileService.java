package by.exadel.internship.service;

import org.springframework.core.io.ByteArrayResource;

public interface FileService {
    String upload(byte[] fileContent, String originalFileName);
    String download(String fileName, String formLastName);
    ByteArrayResource getFile(String filePath);
}
