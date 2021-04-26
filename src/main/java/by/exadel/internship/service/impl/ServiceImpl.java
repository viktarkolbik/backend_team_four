package by.exadel.internship.service.impl;

import by.exadel.internship.dto.FormTemplateData;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.service.Service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class ServiceImpl implements Service {

    private final ObjectMapper objectMapper;

    @Value("${ses.accessKey}")
    private String accessKey;

    @Value("${ses.secretKey}")
    private String secretKey;

    @Value("${ses.region}")
    private String region;

    @Value("${ses.email}")
    private String from;

    @Value("${ses.template}")
    private String templateName;


    @Override
    public void sendEmail(FormRegisterDTO form) {
        FormTemplateData templateData = new FormTemplateData(form.getFirstName());

        String sendName = null;
        try {
            sendName = objectMapper.writeValueAsString(templateData);

            String to = form.getEmail();

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
                    .standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();

            Destination destination = new Destination();

            destination.setToAddresses(Collections.singleton(to));
            SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
            templatedEmailRequest.withDestination(destination);
            templatedEmailRequest.withTemplate(templateName);
            templatedEmailRequest.withTemplateData(sendName);
            templatedEmailRequest.withSource(from);
            client.sendTemplatedEmail(templatedEmailRequest);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}