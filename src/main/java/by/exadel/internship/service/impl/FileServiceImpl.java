package by.exadel.internship.service.impl;

import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.service.FileService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final String DOT_SEPARATOR = ".";

    @Value("${firebase.url}")
    private String downloadUrl;
    @Value("${firebase.bucket}")
    private  String bucketName;

    @Override
    public String upload(MultipartFile multipartFile) {
        File file = null;
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String tempURL = this.uploadFile(file, fileName);                             // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return tempURL;
        } catch (IOException e) {
            if (file != null){
                file.delete();
            }
            throw new FileNotUploadException(e.getMessage());
        }

    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials
                .fromStream(new FileInputStream("internship-cloud/internship-project-e202a.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(downloadUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        FileUtils.writeByteArrayToFile(tempFile,multipartFile.getBytes());
        return tempFile;
    }

    private String getExtension(String fileName) {
        return StringUtils.substringAfter(fileName,DOT_SEPARATOR);
    }
}
