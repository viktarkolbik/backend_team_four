package by.exadel.internship.formTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FormTest extends InternshipApplicationTests {


    @Test
    public void givenFormWithFile_checkTestData()
            throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "text.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, "some file".getBytes());
        MockMultipartFile form = new MockMultipartFile("form", "form",
                MediaType.APPLICATION_JSON_VALUE,
                ("{\"firstName\": \"testName\",\"city\": \"string\",\"country\": \"string\"," +
                        "\"education\": \"string\",\"email\": \"string\",\"englishLevel\": \"A0\"," +
                        "\"experience\": \"string\",\"filePath\": \"string\",\"lastName\": \"string\"," +
                        "\"middleName\": \"string\",\"phoneNumber\": \"string\",\"primarySkill\": \"string\"," +
                        "\"skype\": \"string\"}").getBytes());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/forms")
                .file(form)
                .file(file)
        )
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        FormFullDTO formFullDTO = objectMapper.readValue(content, FormFullDTO.class);

        assertEquals(formFullDTO.getFirstName(), "testName");
        assertEquals(formFullDTO.getLastName(), "string");
        assertEquals(formFullDTO.getCity(), "string");
        assertEquals(formFullDTO.getCountry(), "string");
        assertEquals(formFullDTO.getEducation(), "string");
        assertEquals(formFullDTO.getEmail(), "string");
        assertEquals(formFullDTO.getEnglishLevel(), EnglishLevel.A0);
        assertEquals(formFullDTO.getExperience(), "string");
        assertEquals(formFullDTO.getFilePath(), "text.txt");
        assertEquals(formFullDTO.getMiddleName(), "string");
        assertEquals(formFullDTO.getPhoneNumber(), "string");
        assertEquals(formFullDTO.getPrimarySkill(), "string");
        assertEquals(formFullDTO.getSkype(), "string");
    }

    @Test
    public void checkListSize() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/forms"), status().isOk());

        String content = result.getResponse().getContentAsString();
        List<FormFullDTO> fos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(fos.size(), 10);
    }

}