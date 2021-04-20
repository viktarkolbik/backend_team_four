package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class InternshipTest extends InternshipApplicationTests {

    @Test
    @DirtiesContext
    public void checkInternshipsTestData() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"), status().isOk());

        String content = result.getResponse().getContentAsString();

        GuestInternshipDTO guestInternshipDTO = objectMapper
                .readValue(content, new TypeReference<>() {
                });

        assertEquals(guestInternshipDTO.getName(), "some name");
        assertEquals(guestInternshipDTO.getDescription(), "description1");
        assertEquals(guestInternshipDTO.getRequirements(), "requirements1");
        assertEquals(guestInternshipDTO.getTechSkills(), "skills1");
        assertEquals(guestInternshipDTO.getCapacity(), 300);
        assertEquals(guestInternshipDTO.getStartDate(), LocalDate.of(2021, 7, 21));
        assertEquals(guestInternshipDTO.getEndDate(), LocalDate.of(2021, 9, 21));
        assertEquals(guestInternshipDTO.getPublicationDate(), LocalDate.of(2021, 7, 01));
        assertEquals(guestInternshipDTO.getRegistrationStartDate(), LocalDate.of(2021, 7, 01));
        assertEquals(guestInternshipDTO.getRegistrationEndDate(), LocalDate.of(2021, 7, 01));
    }

    @Test
    @DirtiesContext
    public void checkListSize() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships"), status().isOk());

        String content = result.getResponse().getContentAsString();

        List<UserInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(dtos.size(), 10);
    }
}