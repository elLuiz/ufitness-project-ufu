package com.ufitness.ufitness.controller.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserRepository;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
import com.ufitness.ufitness.dto.LoginDTO;
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
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldLogin() throws Exception {
        saveUser("luiz@luiz.com");
        LoginDTO loginDTO = new LoginDTO("luiz@luiz.com", "abc2223232323232");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/login/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotLoginWhenUserDoesNotExist() throws Exception {
        saveUser("luiz@123.spring");
        LoginDTO loginDTO = new LoginDTO("luiz12@luiz.com", "33223232323232");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/ufitness/login/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)));
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private void saveUser(String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword("$2a$12$slyk9iAOhqhWIpAeWR7m1OChyIDmp18BXPY3Jb8NruFYL8IpFPH4q");
        userEntity.setUserTypeEnum(UserTypeEnum.CLIENT);
        userEntity.setEnabled(true);
        userEntity.setClientEntity(new ClientEntity(null, userEntity));
        userRepository.save(userEntity);
    }

}
