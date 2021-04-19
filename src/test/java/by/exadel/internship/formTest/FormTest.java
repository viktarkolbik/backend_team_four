package by.exadel.internship.formTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.formDTO.FormFullDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FormTest extends InternshipApplicationTests {


    @Test
    public void givenFormWithFile_checkFirstName()
            throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "text.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, "some file".getBytes());
        MockMultipartFile form = new MockMultipartFile("form", "form",
                MediaType.APPLICATION_JSON_VALUE,
                "{\"firstName\": \"testName\"}".getBytes());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/forms")
                .file(form)
                .file(file)
        )
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        FormFullDTO formFullDTO = objectMapper.readValue(content, FormFullDTO.class);

        assertEquals(formFullDTO.getFirstName(), "testName");
    }

    @Test
    public void checkListSize() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/forms"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<FormFullDTO> fos = objectMapper.readValue(content, new TypeReference<>() {});
        assertEquals(fos.size(), 10);
    }
}