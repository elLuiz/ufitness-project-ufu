package com.ufitness.ufitness.service.mail;

import com.ufitness.ufitness.exception.InvalidTokenException;
import com.ufitness.ufitness.repository.mail.EmailStatus;
import com.ufitness.ufitness.repository.mail.MailEntity;
import com.ufitness.ufitness.repository.mail.MailRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    @Mock
    private MailRepository mailRepository;
    @Mock
    private JavaMailSender javaMailSender;
    private MailService mailService;

    @BeforeEach
    void init() {
        mailService = new MailService(mailRepository, javaMailSender);
    }

    @Test
    void shouldValidateToken() {
        MailEntity mailEntity = mailEntity();
        Mockito.when(mailRepository.findMailEntityByToken("abc-3d33-fdlkz-asa"))
                .thenReturn(Optional.of(mailEntity));
        mailService.validateMailToken("abc-3d33-fdlkz-asa");
        AssertionsForClassTypes.assertThat(mailEntity.getEmailStatus()).isEqualTo(EmailStatus.ANSWERED);
    }

    @Test
    void shouldThrowExceptionWhenTokenIsInvalid() {
        Mockito.when(mailRepository.findMailEntityByToken("ass-aadc-asaa"))
                .thenReturn(Optional.empty());
        try {
            mailService.validateMailToken("ass-aadc-asaa");
        } catch (InvalidTokenException invalidTokenException) {
            AssertionsForClassTypes.assertThat(invalidTokenException.getMessage()).isEqualTo("INVALID_TOKEN_EXCEPTION");
        }
    }
    private MailEntity mailEntity() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setExpiresAt(LocalDateTime.now().plusDays(1));
        mailEntity.setEmailStatus(EmailStatus.SENT);
        mailEntity.setToken("abc-3d33-fdlkz-asa");
        mailEntity.setUserEntity(new UserEntity());

        return mailEntity;
    }
}
