package com.ufitness.ufitness.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.client.ClientRepository;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

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
    void name() throws Exception {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO(null, "Luiz", "luiz@luiz.com", "123222");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRegistryDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        Optional<ClientEntity> clientEntity = clientRepository.findById(1L);
        AssertionsForClassTypes.assertThat(clientEntity).isPresent();
    }
}