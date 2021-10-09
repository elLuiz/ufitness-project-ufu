package com.ufitness.ufitness.repository;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.client.ClientRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldSaveClientWithUniqueId() {
        UserEntity userEntity = new UserEntity();
        ClientEntity clientEntity = new ClientEntity();
        userEntity.setName("Test spring");
        userEntity.setEmail("test@spring.boot");
        userEntity.setPassword("1232432");
        userEntity.setClientEntity(clientEntity);
        AssertionsForClassTypes.assertThat(clientRepository.save(clientEntity).getId()).isNotNull();
    }
}
