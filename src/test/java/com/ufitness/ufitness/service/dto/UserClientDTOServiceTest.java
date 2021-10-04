package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class UserClientDTOServiceTest {
    @Spy
    private ModelMapper modelMapper;
    private UserClientDTOService userClientDTOService;

    @BeforeEach
    void init() {
        userClientDTOService = new UserClientDTOService(modelMapper);
    }

    @Test
    void shouldConvertToEntity() {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO(null, "test", "test", "1234");
        AssertionsForClassTypes.assertThat(userClientDTOService.convertToEntity(clientRegistryDTO).getName()).isEqualTo("test");
    }

    @Test
    void shouldConvertToDTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Name");
        userEntity.setPassword("123");
        userEntity.setUserTypeEnum(UserTypeEnum.CLIENT);
        AssertionsForClassTypes.assertThat(userClientDTOService.convertToDTO(userEntity).getName()).isEqualTo("Name");
    }
}
