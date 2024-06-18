package resource.samuelandrian.auth_service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import resource.samuelandrian.auth_service.AuthServiceApplication;
import resource.samuelandrian.auth_service.model.CredentialUserRequest;
import resource.samuelandrian.auth_service.model.WebResponse;
import resource.samuelandrian.auth_service.repository.CredentialUsersRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CredentialUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CredentialUsersRepository credentialUsersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        credentialUsersRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        CredentialUserRequest request = new CredentialUserRequest();
        request.setUsername("samuel");
        request.setPassword("ganteng");

        mockMvc.perform(
                post("/credential/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response =objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertEquals("OK", response.getData());
        });

    }

    @Test
    void testRegisterBadRequest() throws Exception {
        CredentialUserRequest request = new CredentialUserRequest();
        request.setUsername("");
        request.setPassword("");

        mockMvc.perform(
                post("/credential/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

}