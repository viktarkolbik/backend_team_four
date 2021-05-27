package by.exadel.internship;

import by.exadel.internship.config.jwt.JwtService;
import by.exadel.internship.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.annotation.PostConstruct;
import java.net.URI;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ContextConfiguration(initializers = {InternshipApplicationTests.Initializer.class})
@SpringBootTest
public class InternshipApplicationTests {

    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
            .withPassword("root")
            .withUsername("postgres");

    static {
        container.start();
    }

    public static String token = "Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWV2c2t5IiwiaWF0IjoxNjIyMTEyMjU1LCJleHAiOjE3MjIxMTIyNTV9.AhyPLPN_qaQa9NgYe5y_yEko03w1it22o9uchXkZh3tbQFuoF-63wPKeKUgdTxoBVYrrCzaxt9jjOOkyHp7pSQ";

    @MockBean
    protected FileService fileService;

    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @PostConstruct
    public void setTimeModuleUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    protected MvcResult getResult(HttpMethod method, URI uri, ResultMatcher status) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .request(method, uri).header("Authorization", token))
                .andExpect(status)
                .andReturn();

    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=jdbc:postgresql://"
                            + container.getContainerIpAddress()
                            + ":" + container.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT)
                            + "/postgres",
                    "spring.datasource.password =" + container.getPassword(),
                    "spring.datasource.username =" + container.getUsername()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}