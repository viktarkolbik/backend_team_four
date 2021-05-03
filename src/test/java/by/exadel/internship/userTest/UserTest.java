package by.exadel.internship.userTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends InternshipApplicationTests {

    @Autowired
    private UserRepository userRepository;

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
        UserDTO userDTO = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(userDTO.getFirstName(), "Maxim");
        assertEquals(userDTO.getLastName(), "Maevsky");
        assertEquals(userDTO.getEmail(), "maevsky@exadel.com");
        assertEquals(userDTO.getLogin(), "maevsky");
        assertEquals(userDTO.getPassword(), "$2y$12$l4YL7qegaAE4cxlfODXy8ePxT7pBsfArGyfGbhH.Qje/GiYx3dysm");
        assertEquals(userDTO.getUserRole(), UserRole.SUPER_ADMIN);
    }

    @Test
    public void givenUserList_WhenDeleteUser_ThenCheckListSizeWithFlagTrue() throws Exception {
        MvcResult result = getResult(HttpMethod.DELETE, URI.create("/users/740f8b07-3d75-4312-8ba9-1b8531f3940b"), status().isOk());
        List<User> users = userRepository.findAllByDeletedTrue();
        assertEquals(users.size(), 1);
    }

    @Test
    public void givenForm_WhenDelete_ThenCheck_WhetherFormIsDeleted() throws Exception {
        MvcResult result = getResult(HttpMethod.DELETE, URI.create("/users/7ae22ab0-93f9-4be5-9adb-4dbd5bf76c5c"), status().isOk());
        UUID uuid = UUID.fromString("7ae22ab0-93f9-4be5-9adb-4dbd5bf76c5c");
        User user = userRepository.findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + uuid +
                        " Not Found in DB", "form.uuid.invalid"));
        assertTrue(user.isDeleted());
    }

    //To DO restore test
}