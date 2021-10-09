package com.ufitness.ufitness.controller.mail;


import com.ufitness.ufitness.repository.mail.EmailStatus;
import com.ufitness.ufitness.repository.mail.MailEntity;
import com.ufitness.ufitness.repository.mail.MailRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserRepository;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
import com.ufitness.ufitness.util.SecureTokenGenerator;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class MailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private UserRepository userRepository;
    private String token;

    @BeforeEach
    void init() {
        token = SecureTokenGenerator.generateSecureToken();
        String randomEmail = SecureTokenGenerator.generateSecureToken() + "@email.com";
        UserEntity userEntity = new UserEntity(null, "ABC", randomEmail, "123", false, UserTypeEnum.CLIENT, null);
        userEntity = userRepository.save(userEntity);
        MailEntity mailEntity = new MailEntity(null, EmailStatus.SENT, "Messa", userEntity, token, LocalDateTime.now(), LocalDateTime.now().plusHours(1L));
        mailRepository.save(mailEntity);
    }

    @Test
    void shouldValidateToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/ufitness/mail?token=" + token)
                    .contentType(MediaType.APPLICATION_JSON));
        AssertionsForClassTypes.assertThat(mailRepository.findMailEntityByToken(token).get().getEmailStatus()).isEqualTo(EmailStatus.ANSWERED);
    }

    @Test
    void shouldNotValidateTokenWhenItIsNull() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/ufitness/mail?token=" + "abcadkejkj")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}