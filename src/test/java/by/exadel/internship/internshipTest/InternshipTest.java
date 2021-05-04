package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InternshipTest extends InternshipApplicationTests {


    @Test
    public void checkInternshipsTestData() throws Exception {


        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"), status().isOk());

        String content = result.getResponse().getContentAsString();

        UserInternshipDTO userInternshipDTO = objectMapper
                .readValue(content, new TypeReference<>() {
                });

        assertEquals(userInternshipDTO.getName(), "some name");
        assertEquals(userInternshipDTO.getDescription(), "description1");
        assertEquals(userInternshipDTO.getRequirements(), "requirements1");
        assertEquals(userInternshipDTO.getTechSkills(), "skills1");
        assertEquals(userInternshipDTO.getCapacity(), 300);
        assertEquals(userInternshipDTO.getStartDate(), LocalDate.of(2021, 7, 21));
        assertEquals(userInternshipDTO.getEndDate(), LocalDate.of(2021, 9, 21));
        assertEquals(userInternshipDTO.getPublicationDate(), LocalDate.of(2021, 7, 01));
        assertEquals(userInternshipDTO.getRegistrationStartDate(), LocalDate.of(2021, 7, 01));
        assertEquals(userInternshipDTO.getRegistrationEndDate(), LocalDate.of(2021, 7, 01));
    }

    @Test
    public void checkListSize() throws Exception {
        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships"), status().isOk());
        String content = result.getResponse().getContentAsString();
        List<GuestShortInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(dtos.size(), 10);
    }

}