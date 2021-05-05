package by.exadel.internship.internshipTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.internship.GuestFullInternshipDTO;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.dto.location.LocationDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InternshipTest extends InternshipApplicationTests {


    @Test
    public void checkInternshipsTestData() throws Exception {


        MvcResult result = getResult(HttpMethod.GET, URI.create("/internships/68a051d7-6d82-4879-b0a3-1340e14db54d"), status().isOk());

        String content = result.getResponse().getContentAsString();

        GuestFullInternshipDTO guestFullInternshipDTO = objectMapper
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
        List<GuestShortInternshipDTO> dtos = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(dtos.size(), 10);
    }

}