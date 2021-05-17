package by.exadel.internship.service.impl;

import by.exadel.internship.dto.FileInfoDTO;
import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.service.FileService;
import by.exadel.internship.util.MDCLog;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final String DOT_SEPARATOR = ".";
    private static final String DASH_SEPARATOR = "-";
    private static final String SIMPLE_CLASS_NAME = FileService.class.getSimpleName();

    @Value("${firebase.bucket}")
    private String bucketName;
    @Value("${firebase.jsonFilePath}")
    private String jsonFilePath;

    @Override
    public String upload(byte[] fileContent, String originalFileName) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        String fileName = originalFileName;
        fileName = UUID.randomUUID().toString()
                .concat(".")
                .concat(this.getExtension(fileName));
        return this.uploadFile(fileContent, fileName);
    }

    private String uploadFile(byte[] file, String fileName){
        log.info("Try to upload file to cloud");
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        Credentials credentials = getCredentialsByJSON();

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, file);
        log.info("File was uploaded to cloud");
        return fileName;
    }

    @Override
    public FileInfoDTO download(String fileName, String formLastName, String internshipName) {
        log.info("Try to download file from cloud");
        String destFileName = formLastName
                .concat(DASH_SEPARATOR)
                .concat(internshipName)
                .concat(DOT_SEPARATOR)
                .concat(this.getExtension(fileName));

        Credentials credentials = getCredentialsByJSON();

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of(bucketName, fileName));

        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        fileInfoDTO.setFileName(destFileName);
        fileInfoDTO.setResource(new ByteArrayResource(blob.getContent()));
        log.info("Return FileInfoDTO with name and resource");
        return fileInfoDTO;
    }


    private Credentials getCredentialsByJSON(){
        try {
            return GoogleCredentials.fromStream(new FileInputStream(jsonFilePath));
        } catch (IOException e) {
            log.error("File was not uploaded to cloud");
            throw new FileNotUploadException("File was not uploaded because: " + e.getMessage());
        }
    }

    private String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, DOT_SEPARATOR);
    }
}
