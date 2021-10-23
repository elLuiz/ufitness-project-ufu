package com.ufitness.ufitness.controller.instructor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class InstructorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveInstructorWithValidCREF() throws Exception {
        InstructorRegistryDTO instructorRegistryDTO = new InstructorRegistryDTO("Name", "email@email.com", "12345678", "12012391200", "56241-G/MG");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/instructor")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(instructorRegistryDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotSaveInstructorCREFDoesNotExist() throws Exception {
        InstructorRegistryDTO instructorRegistryDTO = new InstructorRegistryDTO("Name", "email@email.com", "12345678", "12012391200", "16241-G/MG");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/instructor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instructorRegistryDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}