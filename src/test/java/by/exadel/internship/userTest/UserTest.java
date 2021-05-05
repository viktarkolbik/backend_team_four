package by.exadel.internship.userTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.UserRole;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserTest extends InternshipApplicationTests {

    @Test
    public void checkListSize() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/users"), status().isOk());

        String content = result.getResponse().getContentAsString();
        List<UserDTO> userDTOList = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(userDTOList.size(), 5);
    }

    @Test
    public void checkTestData() throws Exception {

        MvcResult result = getResult(HttpMethod.GET, URI.create("/users/b64b3afc-b1be-4c7a-9406-d7d14f436332"), status().isOk());

        String content = result.getResponse().getContentAsString();
        UserDTO userDTO = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(userDTO.getFirstName(), "Maxim");
        assertEquals(userDTO.getLastName(), "Maevsky");
        assertEquals(userDTO.getEmail(), "maevsky@exadel.com");
        assertEquals(userDTO.getUserRole(), UserRole.SUPER_ADMIN);
    }
}