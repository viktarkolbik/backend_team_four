package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.dto.location.LocationDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.InternshipRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InternshipTest extends InternshipApplicationTests {

    @Autowired
    private InternshipRepository internshipRepository;

    @Test
    public void checkInternshipsTestData() throws Exception {


        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"), status().isOk());

        String content = result.getResponse().getContentAsString();

        GuestInternshipDTO guestFullInternshipDTO = objectMapper
                .readValue(content, new TypeReference<>() {
                });

        LocationDTO locationDTO1 = new LocationDTO(new CountryDTO(UUID.fromString("21aab798-2d48-459c-bb84-789d2237f933"), "Belarus"), new CityDTO(UUID.fromString("8c48af72-358a-49d4-a786-ce723eefc384"), "Minsk"));
        LocationDTO locationDTO2 = new LocationDTO(new CountryDTO(UUID.fromString("21aab798-2d48-459c-bb84-789d2237f933"), "Belarus"), new CityDTO(UUID.fromString("b38d8113-d5e8-4967-b8ff-e1df0844790f"), "Gomel"));
        LocationDTO locationDTO3 = new LocationDTO(new CountryDTO(UUID.fromString("de5e7623-c298-4033-8419-9e2fd12afdfa"), "Ukraine"), new CityDTO(UUID.fromString("dceb0743-e262-42b8-9d58-bbdb4e5cd729"), "Kiev"));
        assertEquals(guestFullInternshipDTO.getName(), "JAVA, JS internship");
        assertEquals(guestFullInternshipDTO.getDescription(), "During the training, you will learn how to write simple and \"clean\" code, create programs that run in a web browser and have web services, develop server-side applications for forums, online stores or surveys, and create multifunctional applications");
        assertEquals(guestFullInternshipDTO.getRequirements(), "OOP knowledge: classes, encapsulation, polymorphism, inheritance;\n" +
                "knowledge of the basics of SQL and the basics of database design is desirable;\n" +
                "knowledge of English at a level not lower than A2;");
        assertEquals(guestFullInternshipDTO.getTechSkills(), "Docker; GitHub; Swagger UI");
        assertEquals(guestFullInternshipDTO.getSkills(), Set.of(Skill.JAVA,Skill.JS));
        assertEquals(guestFullInternshipDTO.getInternshipFormat(), InternshipFormat.ONLINE);
        assertEquals(guestFullInternshipDTO.getLocations(), List.of(locationDTO1,locationDTO2,locationDTO3));
        assertEquals(guestFullInternshipDTO.getStartDate(), LocalDate.of(2021, 7, 21));
        assertEquals(guestFullInternshipDTO.getEndDate(), LocalDate.of(2021, 9, 21));

    }

    @Test
    public void checkListSize() throws Exception {
        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships"), status().isOk());
        String content = result.getResponse().getContentAsString();
        List<BaseInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(dtos.size(), 10);
    }

    @Test
    public void createInternship_ThenSave_checkData() throws Exception {

        UserInternshipDTO dto = postTestInternshipData();

        assertEquals(dto.getName(), "Internship");
        assertEquals(dto.getDescription(), "string");
        assertEquals(dto.getRequirements(), "string");
        assertEquals(dto.getTechSkills(), "string");
        assertEquals(dto.getInternshipFormat(), InternshipFormat.ONLINE);
        assertEquals(dto.getSkills(), Set.of(Skill.JAVA, Skill.JS));
        assertEquals(dto.getLocations(), dto.getLocations());
        assertEquals(dto.getCapacity(), 200);
        assertEquals(dto.getStartDate(), LocalDate.of(2021, 7, 21));
        assertEquals(dto.getEndDate(), LocalDate.of(2021, 9, 21));
        assertEquals(dto.getPublicationDate(), LocalDate.of(2021, 7, 01));
        assertEquals(dto.getRegistrationStartDate(), LocalDate.of(2021, 7, 01));
        assertEquals(dto.getRegistrationEndDate(), LocalDate.of(2021, 7, 20));
    }

    private UserInternshipDTO testInternshipData() {
        LocationDTO locationDTO1 = new LocationDTO(new CountryDTO(UUID.fromString("21aab798-2d48-459c-bb84-789d2237f933"), "Belarus"), new CityDTO(UUID.fromString("8c48af72-358a-49d4-a786-ce723eefc384"), "Minsk"));
        return UserInternshipDTO.builder().name("Internship")
                .id(UUID.fromString("11111111-2d48-459c-bb84-789d2237f933"))
                .internshipFormat(InternshipFormat.ONLINE)
                .capacity(200).description("string")
                .skills(Set.of(Skill.JAVA, Skill.JS)).requirements("string").techSkills("string")
                .locations(List.of(locationDTO1))
                .startDate(LocalDate.of(2021, 7, 21))
                .endDate(LocalDate.of(2021, 9, 21))
                .publicationDate(LocalDate.of(2021, 7, 01))
                .registrationStartDate(LocalDate.of(2021, 7, 01))
                .registrationEndDate(LocalDate.of(2021, 7, 20)).build();
    }

    private UserInternshipDTO postTestInternshipData() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/internships")
                .content(objectMapper.writeValueAsString(testInternshipData()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        return objectMapper.readValue(content, UserInternshipDTO.class);
    }

    @Test
    public void givenInternshipList_WhenDelete_ThenCheckSizeWithFlagFalse() throws Exception {
        UUID id = postTestInternshipData().getId();
        getResult(HttpMethod.DELETE, URI.create("/internships/" + id), status().isOk());
        List<Internship> internships = internshipRepository.findAllByDeletedFalse();
        assertEquals(internships.size(), 10);
    }


    @Test
    public void givenInternship_WhenDelete_ThenCheck_WhetherInternshipIsDeleted() throws Exception {
        UUID id = postTestInternshipData().getId();
        getResult(HttpMethod.DELETE, URI.create("/internships/" + id), status().isOk());
        Internship internship = internshipRepository.findByIdAndDeletedTrue(id)
                .orElseThrow(() -> new NotFoundException("Internship with uuid = " + id +
                        " Not Found in DB", "internship.uuid.invalid"));
        assertTrue(internship.isDeleted());
    }

    @Test
    public void given_NewInternship_Then_Deleted_AndWhen_RestoreInternship_Expect_FlagIsFalse() throws Exception {
        UUID id = postTestInternshipData().getId();
        getResult(HttpMethod.DELETE, URI.create("/internships/" + id), status().isOk());
        getResult(HttpMethod.PUT, URI.create("/internships/" + id + "/restore"), status().isOk());
        Internship internship = internshipRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Internships with uuid = " + id +
                        " Not Found in DB", "internship.uuid.invalid"));
        assertFalse(internship.isDeleted());
    }
}