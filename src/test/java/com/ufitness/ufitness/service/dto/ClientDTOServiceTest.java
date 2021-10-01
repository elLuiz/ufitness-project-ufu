package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.service.client.ClientDTO;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ClientDTOServiceTest {
    @Mock
    private ModelMapper modelMapper;
    private ClientDTOService clientDTOService;
    private ClientEntity clientEntity;
    private ClientDTO clientDTO;

    @BeforeEach
    void init() {
        clientDTOService = new ClientDTOService(modelMapper);
        clientDTO = generateClientDTO();
        clientEntity = generateClientEntity();
    }

    @Test
    void shouldConvertClientToDTO() {
        Mockito.when(clientDTOService.convertToDTO(clientEntity))
                .thenReturn(clientDTO);
        AssertionsForClassTypes.assertThat(clientDTO).isEqualTo(clientDTOService.convertToDTO(clientEntity));
    }

    @Test
    void shouldConvertDTOToClient() {
        Mockito.when(clientDTOService.convertToEntity(clientDTO))
                .thenReturn(clientEntity);
        AssertionsForClassTypes.assertThat(clientEntity).isEqualTo(clientDTOService.convertToEntity(clientDTO));
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

    private ClientDTO generateClientDTO() {
        return new ClientDTO(40L, "My Name", "myemail@gmail.com");
    }
}
