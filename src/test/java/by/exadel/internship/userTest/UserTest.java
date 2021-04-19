package by.exadel.internship.userTest;

import by.exadel.internship.InternshipApplicationTests;
import by.exadel.internship.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends InternshipApplicationTests {

    @Test
    public void checkListSize() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<UserDTO> userDTOList = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(userDTOList.size(), 5);
    }

    @Test
    public void checkFirstName() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/b64b3afc-b1be-4c7a-9406-d7d14f436332"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        UserDTO userDTO = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertEquals(userDTO.getFirstName(),"Maxim");
    }
}