package com.ufitness.ufitness.repository.user;

import com.ufitness.ufitness.repository.client.ClientEntity;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldFindUserByCredentials() {
        saveUserEntity();
        Optional<UserEntity> response = userRepository.loginUser("luiz@luiz.com");
        AssertionsForClassTypes.assertThat(response).isPresent();
    }

    private void saveUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("luiz@luiz.com");
        userEntity.setPassword("$2a.18928832");
        userEntity.setUserTypeEnum(UserTypeEnum.CLIENT);
        userEntity.setClientEntity(new ClientEntity(null, userEntity));
        userRepository.save(userEntity);
    }
}
