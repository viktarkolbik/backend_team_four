package by.exadel.internship.formTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FormTest extends InternshipApplicationTests {

// TO DO
//    @Test
//    public void givenFormWithFile_checkTestData()
//            throws Exception {
//
//        MockMultipartFile file = new MockMultipartFile("file", "text.txt",
//                MediaType.MULTIPART_FORM_DATA_VALUE, "some file".getBytes());
//        MockMultipartFile form = new MockMultipartFile("form", "form",
//                MediaType.APPLICATION_JSON_VALUE,
//                ("{\"firstName\": \"testName\",\"city\": \"Gomel\",\"country\": \"Belarus\"," +
//                        "\"education\": \"string\",\"email\": \"string\",\"englishLevel\": \"A0\"," +
//                        "\"experience\": \"string\",\"filePath\": \"string\",\"lastName\": \"string\"," +
//                        "\"middleName\": \"string\",\"phoneNumber\": \"string\",\"primarySkill\": \"string\"," +
//                        "\"skype\": \"string\"}").getBytes());
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/forms")
//                .file(form)
//                .file(file)
//        )
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//        FormFullDTO formFullDTO = objectMapper.readValue(content, FormFullDTO.class);
//
//        assertEquals(formFullDTO.getFirstName(), "testName");
//        assertEquals(formFullDTO.getLastName(), "string");
////        assertEquals(formFullDTO.getCity(), new CityDTO(UUID.fromString("69e6b47a-4c3d-4207-ac2d-801d9eda7ff1"), "Lviv"));
////        assertEquals(formFullDTO.getCountry(), new CountryDTO(UUID.fromString("de5e7623-c298-4033-8419-9e2fd12afdfa"), "Ukraine"));
//        assertEquals(formFullDTO.getEducation(), "string");
//        assertEquals(formFullDTO.getEmail(), "string");
//        assertEquals(formFullDTO.getEnglishLevel(), EnglishLevel.A0);
//        assertEquals(formFullDTO.getExperience(), "string");
//        assertEquals(formFullDTO.getFilePath(), "text.txt");
//        assertEquals(formFullDTO.getMiddleName(), "string");
//        assertEquals(formFullDTO.getPhoneNumber(), "string");
//        assertEquals(formFullDTO.getPrimarySkill(), "string");
//        assertEquals(formFullDTO.getSkype(), "string");
//    }


// TO DO

//    @Test
//    public void checkListSize() throws Exception {
//
//        MvcResult result = getResult(HttpMethod.GET, URI.create("/forms"), status().isOk());
//
//        String content = result.getResponse().getContentAsString();
//        List<FormFullDTO> fos = objectMapper.readValue(content, new TypeReference<>() {
//        });
//        assertEquals(fos.size(), 10);
//    }

}