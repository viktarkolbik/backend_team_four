package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InternshipTest extends InternshipApplicationTests {

    @Test
    public void checkInternshipsName() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        GuestInternshipDTO guestInternshipDTO = objectMapper
                .readValue(content, new TypeReference<>() {
                });

        assertEquals(guestInternshipDTO.getName(), "some name");
    }

    @Test
    public void checkListSize() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/internships"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<UserInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(dtos.size(), 10);
    }
}