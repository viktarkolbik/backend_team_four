package by.exadel.internship.formTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.FormRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FormTest extends InternshipApplicationTests {

    @Autowired
    private FormRepository formRepository;

    private MockMultipartFile formData() {
        return new MockMultipartFile("form", "form",
                MediaType.APPLICATION_JSON_VALUE,
                ("{\"firstName\": \"testName\",\"city\": {\"id\":\"69e6b47a-4c3d-4207-ac2d-801d9eda7ff1\",\"name\":\"Lviv\"}," +
                        "\"country\": {\"id\":\"de5e7623-c298-4033-8419-9e2fd12afdfa\",\"name\":\"Ukraine\"}," +
                        "\"education\": \"string\",\"email\": \"string\",\"englishLevel\": \"A0\"," +
                        "\"experience\": \"string\",\"filePath\": \"string\",\"lastName\": \"string\"," +
                        "\"middleName\": \"string\",\"phoneNumber\": \"string\",\"primarySkill\": \"string\"," +
                        "\"skype\": \"string\"}").getBytes());
    }

    @Test
    public void givenFormWithFile_checkTestData()
            throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "text.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, "some file".getBytes());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/forms")
                .file(formData())
                .file(file)
        )
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        FormFullDTO formFullDTO = objectMapper.readValue(content, FormFullDTO.class);
        assertEquals(formFullDTO.getFirstName(), "testName");
        assertEquals(formFullDTO.getLastName(), "string");
        assertEquals(formFullDTO.getCity(), new CityDTO(UUID.fromString("69e6b47a-4c3d-4207-ac2d-801d9eda7ff1"), "Lviv"));
        assertEquals(formFullDTO.getCountry(), new CountryDTO(UUID.fromString("de5e7623-c298-4033-8419-9e2fd12afdfa"), "Ukraine"));
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
        URI uri = UriComponentsBuilder.fromPath("/forms")
                .queryParam("internshipId", "68a051d7-6d82-4879-b0a3-1340e14db54d")
                .build().toUri();
        MvcResult result = getResult(HttpMethod.GET, uri, status().isOk());
        String content = result.getResponse().getContentAsString();
        List<FormFullDTO> fos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(fos.size(), 1);
    }

    @Test
    public void checkWhetherStatusIsUpdated() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/forms/updateStatus")
                .queryParam("formId", "7c0811d5-354b-4ebb-a65c-0b54efc53a80")
                .queryParam("status", FormStatus.NOT_MATCHED)
                .build().toUri();
        getResult(HttpMethod.PUT, uri, status().isOk());
        UUID uuid = UUID.fromString("7c0811d5-354b-4ebb-a65c-0b54efc53a80");
        Form form = formRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Form with uuid = " + uuid +
                " Not Found in DB", "form.uuid.invalid"));
        assertEquals(form.getFormStatus(), FormStatus.NOT_MATCHED);
    }


    @Test
    public void givenFormList_WhenDeleteForm_ThenCheckListSizeWithFlagTrue() throws Exception {

        UUID id = uuidOfCreatedAndPostTestDataForm();
        getResult(HttpMethod.DELETE, URI.create("/forms/" + id), status().isOk());
        List<Form> forms = formRepository.findAllByDeletedTrue();
        assertEquals(forms.size(), 1);
    }

    @Test
    public void givenForm_WhenDelete_ThenCheck_WhetherFormIsDeleted() throws Exception {

        UUID id = uuidOfCreatedAndPostTestDataForm();
        getResult(HttpMethod.DELETE, URI.create("/forms/" + id), status().isOk());
        Form form = formRepository.findByIdAndDeletedTrue(id)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + id +
                        " Not Found in DB", "form.uuid.invalid"));
        assertTrue(form.isDeleted());
    }


    @Test
    public void when_RestoreForm_Expect_FlagIsFalse() throws Exception {
        UUID id = uuidOfCreatedAndPostTestDataForm();
        getResult(HttpMethod.DELETE, URI.create("/forms/" + id), status().isOk());
        getResult(HttpMethod.PUT, URI.create("/forms/" + id + "/restore"), status().isOk());
        Form form = formRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + id +
                        " Not Found in DB", "form.uuid.invalid"));
        assertFalse(form.isDeleted());
    }

    private UUID uuidOfCreatedAndPostTestDataForm() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/forms").file(formData()))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        FormFullDTO formFullDTO = objectMapper.readValue(content, FormFullDTO.class);
        return formFullDTO.getId();
    }


}