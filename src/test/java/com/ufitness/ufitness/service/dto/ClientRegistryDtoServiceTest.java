package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
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
    private ClientRegistryDTO clientRegistryDTO;
    private ClientEntity clientEntity;

    @BeforeEach
    void init() {
        clientRegistryDTOService = new ClientRegistryDTOService(modelMapper);
        clientRegistryDTO = generateClientRegistryDTO();
        clientEntity = generateClientEntity();

    }

    @Test
    void shouldConvertDTOToEntity() {
        Mockito.when(modelMapper.map(clientRegistryDTO, ClientEntity.class))
                .thenReturn(clientEntity);
        AssertionsForClassTypes.assertThat(clientEntity).isEqualTo(clientRegistryDTOService.convertToEntity(clientRegistryDTO));
    }

    @Test
    void shouldConvertEntityToDTO() {
        Mockito.when(modelMapper.map(clientEntity, ClientRegistryDTO.class))
                .thenReturn(clientRegistryDTO);
        AssertionsForClassTypes.assertThat(clientRegistryDTO).isEqualTo(clientRegistryDTOService.convertToDTO(clientEntity));
    }

    private ClientEntity generateClientEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(40L);
        clientEntity.setName("My Name");
        clientEntity.setEmail("myemail@gmail.com");
        clientEntity.setEnabled(false);
        clientEntity.setPassword("12333");

        return clientEntity;
    }

    private ClientRegistryDTO generateClientRegistryDTO() {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO();
        clientRegistryDTO.setId(40L);
        clientRegistryDTO.setEmail("myemail@gmail.com");
        clientRegistryDTO.setName("My Name");
        clientRegistryDTO.setPassword("12333");

        return clientRegistryDTO;
    }
}
