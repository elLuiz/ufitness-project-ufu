package com.ufitness.ufitness.service.dto.client;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.dto.client.ClientRegistryDTO;
import com.ufitness.ufitness.service.dto.client.ClientRegistryDTOService;
import com.ufitness.ufitness.service.dto.client.UserClientDTOService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ClientRegistryDtoServiceTest {
    private ClientRegistryDTOService clientRegistryDTOService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserClientDTOService userClientDTOService;
    private ClientRegistryDTO clientRegistryDTO;
    private ClientEntity clientEntity;

    @BeforeEach
    void init() {
        clientRegistryDTOService = new ClientRegistryDTOService(modelMapper);
        clientRegistryDTOService.setUserDTOService(userClientDTOService);
        clientRegistryDTO = generateClientRegistryDTO();
        clientEntity = new ClientEntity();
    }

    @Test
    void shouldConvertDTOToEntity() {
        Mockito.when(userClientDTOService.convertToEntity(clientRegistryDTO))
                .thenReturn(generateUserEntity());
        AssertionsForClassTypes.assertThat(clientRegistryDTOService.convertToEntity(clientRegistryDTO)).isNotNull();
    }

    @Test
    void shouldConvertEntityToDTO() {
        Mockito.when(modelMapper.map(clientEntity, ClientRegistryDTO.class))
                .thenReturn(clientRegistryDTO);
        AssertionsForClassTypes.assertThat(clientRegistryDTO).isEqualTo(clientRegistryDTOService.convertToDTO(clientEntity));
    }


    private ClientRegistryDTO generateClientRegistryDTO() {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO();
        clientRegistryDTO.setEmail("myemail@gmail.com");
        clientRegistryDTO.setName("My Name");
        clientRegistryDTO.setPassword("12333");

        return clientRegistryDTO;
    }

    private UserEntity generateUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("My Name");
        userEntity.setEmail("myemail@gmail.com");
        userEntity.setEnabled(false);

        return userEntity;
    }
}
