package by.exadel.internship.userTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserTest extends InternshipApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserTimeSlotTest() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2021,05,20,11,00,00);
        LocalDateTime endTime = LocalDateTime.of(2021,05,20,12,00,00);

        UserTimeSlotDTO userTimeSlotDTO = new UserTimeSlotDTO();
        userTimeSlotDTO.setStartDate(startTime);
        userTimeSlotDTO.setEndDate(endTime);

        List<UserTimeSlotDTO> userTimeSlotDTOList = new ArrayList<>();

        userTimeSlotDTOList.add(userTimeSlotDTO);

        URI uri = UriComponentsBuilder.fromPath("/users/e4ec8eef-c659-46f5-bde8-63551a980553/time-slot")
                .build().toUri();

        MvcResult mvcResult = mockMvc.perform(post(uri)
                .content(objectMapper.writeValueAsString(userTimeSlotDTOList))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<UserTimeSlotDTO> result = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertEquals(5, result.size());

    }

    @Test
    public void checkListSize() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/users")
                .queryParam("internshipId", "68a051d7-6d82-4879-b0a3-1340e14db54d")
                .queryParam("role", UserRole.SUPER_ADMIN)
                .build().toUri();
        MvcResult result = getResult(HttpMethod.GET, uri, status().isOk());
        String content = result.getResponse().getContentAsString();
        List<UserDTO> userDTOList = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(userDTOList.size(), 1);
    }


    @Test
    public void checkTestData() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/users/b64b3afc-b1be-4c7a-9406-d7d14f436332"), status().isOk());

        String content = result.getResponse().getContentAsString();
        UserDTO userDTO = objectMapper.readValue(content, UserDTO.class);
        assertEquals(userDTO.getFirstName(), "Maxim");
        assertEquals(userDTO.getLastName(), "Maevsky");
        assertEquals(userDTO.getEmail(), "maevsky@exadel.com");
        assertEquals(userDTO.getUserRole(), UserRole.SUPER_ADMIN);
    }


    @Test
    @SqlGroup({
            @Sql(scripts = "/insert-u-sql/insert-u-1.sql"),
            @Sql(scripts = "/delete-u-sql/delete-u-1.sql", executionPhase = AFTER_TEST_METHOD)
    })
    public void   givenInternship_WhenDelete_ThenCheck_WhetherInternshipIsDeleted() throws Exception {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-789d2237f933");

        getResult(HttpMethod.DELETE, URI.create("/users/" + userId), status().isOk());
        User user = userRepository.findByIdAndDeletedTrue(userId)
                .orElseThrow(() -> new NotFoundException("User with uuid = " + userId +
                        " Not Found in DB", "user.uuid.invalid"));
        assertTrue(user.isDeleted());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/insert-u-sql/insert-u-2.sql"),
            @Sql(scripts = "/delete-u-sql/delete-u-2.sql", executionPhase = AFTER_TEST_METHOD)
    })
    public void given_NewUser_Then_Deleted_AndWhen_RestoreUser_Expect_FlagIsFalse() throws Exception {
        UUID userId = UUID.fromString("22222222-1111-1111-1111-789d2237f933");
        getResult(HttpMethod.DELETE, URI.create("/users/" + userId), status().isOk());
        getResult(HttpMethod.PUT, URI.create("/users/" + userId + "/restore"), status().isOk());
        User user = userRepository.findUserByIdWithCurrentTimeSlots(userId)
                .orElseThrow(() -> new NotFoundException("User with uuid = " + userId +
                        " Not Found in DB", "user.uuid.invalid"));
        assertFalse(user.isDeleted());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/insert-u-sql/insert-u-4.sql"),
            @Sql(scripts = "/delete-u-sql/delete-u-4.sql", executionPhase = AFTER_TEST_METHOD)
    })
    public void checkTestDataNewUser() throws Exception {
        MvcResult result = getResult(HttpMethod.GET, URI.create("/users/44444444-1111-1111-1111-789d2237f933"), status().isOk());
        String content = result.getResponse().getContentAsString();
        UserDTO userDTO = objectMapper.readValue(content, UserDTO.class);
        assertEquals(userDTO.getFirstName(), "Vlad");
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/insert-u-sql/insert-u-3.sql"),
            @Sql(scripts = "/delete-u-sql/delete-u-3.sql", executionPhase = AFTER_TEST_METHOD)
    })
    public void checkListSizeHistoricalUsers() throws Exception {
        UUID userId = UUID.fromString("33333333-1111-1111-1111-789d2237f933");
        getResult(HttpMethod.DELETE, URI.create("/users/" + userId), status().isOk());
        MvcResult result = getResult(HttpMethod.GET, URI.create("/users/historical"), status().isOk());
        String content = result.getResponse().getContentAsString();
        List<UserDTO> usersHistorical = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(usersHistorical.size(), 1);
    }

    @Test
    public void givenListOfSkills_checkUsersBySkills() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/users/skills")
                .queryParam("skills", "JAVA,GO").build().toUri();
        MvcResult result = getResult(HttpMethod.GET, uri, status().isOk());

        String content = result.getResponse().getContentAsString();
        Set<UserDTO> userDTOSet = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(userDTOSet.size(), 4);
    }

    @Test
    public void checkWrongData_expect_BadRequestStatus() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/users/skills")
                .queryParam("skills", "JAVA,G").build().toUri();
        getResult(HttpMethod.GET, uri, status().isBadRequest());

    }
}