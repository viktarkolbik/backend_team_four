package by.exadel.internship.service.impl;

import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.service.FileService;
import by.exadel.internship.util.MDCLog;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final String DOT_SEPARATOR = ".";
    private static final String SIMPLE_CLASS_NAME = FileService.class.getSimpleName();

    @Value("${firebase.url}")
    private String downloadUrl;
    @Value("${firebase.bucket}")
    private String bucketName;
    @Value("${firebase.jsonFilePath}")
    private String jsonFilePath;

    @Override
    public String upload(byte[] fileContent, String originalFileName) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        String fileName = originalFileName;
        fileName = UUID.randomUUID().toString()
                .concat(this.getExtension(fileName));
        return this.uploadFile(fileContent, fileName);
    }

    private String uploadFile(byte[] file, String fileName){
        log.info("Try to upload file to cloud");
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = null;
        try {
            credentials = GoogleCredentials
                    .fromStream(new FileInputStream(jsonFilePath));
        } catch (IOException e) {
            log.error("File was not uploaded to cloud");
            throw new FileNotUploadException("File was not uploaded because: " + e.getMessage());
        }
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, file);
        log.info("File was uploaded to cloud");
        return String.format(downloadUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, DOT_SEPARATOR);
    }
}
