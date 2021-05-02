package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.InternshipRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InternshipTest extends InternshipApplicationTests {


    @Autowired
    private InternshipRepository internshipRepository;

    @Test
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
    public void checkListSize() throws Exception {
        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships"), status().isOk());
        String content = result.getResponse().getContentAsString();
        List<GuestInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(dtos.size(), 10);
    }

    @Test
    public void givenInternshipList_WhenDelete_ThenCheckSizeWithFlagFalse() throws Exception {
        MvcResult result = getResult(HttpMethod.DELETE, URI.create("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"), status().isOk());
        List<Internship> internships = internshipRepository.findAllByDeletedFalse();
        assertEquals(internships.size(), 9);
    }

    @Test
    public void givenInternshipList_WhenDelete_ThenCheckSizeWithFlagTrue() throws Exception {
        MvcResult result = getResult(HttpMethod.DELETE, URI.create("/internships/55fdfdd2-cac7-4851-a0a6-ba93113e926b"), status().isOk());
        List<Internship> internships = internshipRepository.findAllByDeletedTrue();
        assertEquals(internships.size(), 1);
    }

    @Test
    public void givenInternship_WhenDelete_ThenCheck_WhetherInternshipIsDeleted() throws Exception {
        MvcResult result = getResult(HttpMethod.DELETE, URI.create("/internships/0137afd2-42ca-49e4-9018-3499199fdf43"), status().isOk());
        UUID uuid = UUID.fromString("0137afd2-42ca-49e4-9018-3499199fdf43");
        Internship internship = internshipRepository.findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("Internship with uuid = " + uuid +
                        " Not Found in DB", "form.uuid.invalid"));
        assertTrue(internship.isDeleted());
    }


}