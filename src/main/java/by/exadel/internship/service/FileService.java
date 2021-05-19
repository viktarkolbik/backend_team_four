package by.exadel.internship.service;

import by.exadel.internship.dto.FileInfoDTO;

public interface FileService {
    String upload(byte[] fileContent, String originalFileName);
    FileInfoDTO download(String fileName, String formLastName, String internshipName);
}
