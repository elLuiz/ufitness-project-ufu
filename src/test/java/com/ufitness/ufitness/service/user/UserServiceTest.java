package com.ufitness.ufitness.service.user;

import com.ufitness.ufitness.dto.LoginDTO;
import com.ufitness.ufitness.exception.LoginException;
import com.ufitness.ufitness.exception.UserNotFoundException;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userServiceSpy;
    private LoginDTO loginDTO;
    private UserEntity userEntity;

    @BeforeEach
    void init() {
        loginDTO = new LoginDTO("luiz@luiz.luiz", "123");
        UserService userService = new UserService(userRepository, passwordEncoder);
        userServiceSpy = Mockito.spy(userService);
        userEntity = new UserEntity();
        userEntity.setId(10L);
        userEntity.setEnabled(true);
        userEntity.setPassword("$2$12789834784372389");
    }

    @Test
    void shouldLoginUser() {
        Mockito.when(passwordEncoder.matches("123", userEntity.getPassword()))
                .thenReturn(true);
        Mockito.doReturn(Optional.of(userEntity))
                .when(userRepository)
                .loginUser(loginDTO.getEmail());
        userServiceSpy.loginUser(loginDTO);
        Mockito.verify(userServiceSpy).loginUser(loginDTO);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenCredentialsDoNotMatch() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .loginUser("whater@whater");
        LoginDTO invalidLogin = new LoginDTO("whater@whater", "123");
        try {
            userServiceSpy.loginUser(invalidLogin);
        } catch (UserNotFoundException userNotFoundException) {
            AssertionsForClassTypes.assertThat(userNotFoundException.getMessage()).isNotEmpty();
        }
    }

    @Test
    void shouldThrowLoginExceptionWhenUserIsNotEnabled() {
        userEntity.setEnabled(false);
        Mockito.doReturn(Optional.of(userEntity))
                .when(userRepository).loginUser(loginDTO.getEmail());
        Mockito.when(passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword()))
                .thenReturn(true);
        try {
            userServiceSpy.loginUser(loginDTO);
        } catch (LoginException loginException) {
            AssertionsForClassTypes.assertThat(loginException.getMessage()).isNotEmpty();
        }
    }
}
