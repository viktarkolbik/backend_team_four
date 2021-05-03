package by.exadel.internship;

import by.exadel.internship.config.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String jwtToken;

    @PostConstruct
    public void setTimeModuleUp() {
        objectMapper.registerModule(new JavaTimeModule());
        jwtToken = generateJWTToken();
    }


    private String generateJWTToken() {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        "maevsky",
                        "1"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateJwtToken(authentication);
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    protected MvcResult getResult(HttpMethod method, URI uri, ResultMatcher status) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .request(method, uri).header("Authorization", "Bearer " + jwtToken))
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