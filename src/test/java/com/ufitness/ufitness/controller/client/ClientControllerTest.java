package com.ufitness.ufitness.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufitness.ufitness.repository.client.ClientRepository;
import com.ufitness.ufitness.dto.client.ClientRegistryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


// reference: https://github.com/amigoscode/spring-boot-fullstack-professional/blob/13-testing/src/test/java/com/example/demo/integration/StudentIT.java
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldNotSaveClientDueToUnavailableEmailServer() throws Throwable {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO("Luiz Test", "abc@abc.com", "123233322");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRegistryDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
