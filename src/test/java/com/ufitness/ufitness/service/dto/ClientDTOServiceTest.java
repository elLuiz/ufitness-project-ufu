package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
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
        clientEntity = new ClientEntity();
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

    private ClientDTO generateClientDTO() {
        return new ClientDTO(40L, "My Name", "myemail@gmail.com");
    }
}
