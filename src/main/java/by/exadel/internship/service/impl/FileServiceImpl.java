package by.exadel.internship.service.impl;

import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.service.FileService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final String DOT_SEPARATOR = ".";

    @Value("${firebase.url}")
    private String downloadUrl;
    @Value("${firebase.bucket}")
    private  String bucketName;
    @Value("${firebase.jsonFilePath}")
    private String jsonFilePath;

    @Override
    public String upload(byte[] multipartFileInByte, String originalFileName) {
        try {
            String fileName = originalFileName;                                             // to get original file name
            fileName = UUID.randomUUID().toString()
                    .concat(this.getExtension(fileName));                                   // to generated random string values for file name.

            String tempURL = this.uploadFile(multipartFileInByte, fileName);                // to get uploaded file link
            return tempURL;
        } catch (IOException e) {
            throw new FileNotUploadException(e.getMessage());
        }

    }

    private String uploadFile(byte[] file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(jsonFilePath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, file);
        return String.format(downloadUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName,DOT_SEPARATOR);
    }
}
