package com.ufitness.ufitness.service.client;


import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.client.ClientRepository;
import com.ufitness.ufitness.service.dto.ClientDTOService;
import com.ufitness.ufitness.service.dto.ClientRegistryDTOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    private ClientService clientService;
    @Mock
    private ClientRegistryDTOService clientRegistryDTOService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientDTOService clientDTOService;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    private ClientEntity clientEntity;
    private ClientRegistryDTO clientRegistryDTO;
    private ClientDTO clientDTO;

    @BeforeEach
    public void init() {
        clientEntity = generateClientEntity();
        clientRegistryDTO = generateClientRegistryDTO();
        clientDTO = generateClientDTO();
        clientService = new ClientService(clientRepository, clientRegistryDTOService, clientDTOService);
        clientService.setPasswordEncoder(passwordEncoder);
        Mockito.when(passwordEncoder.encode("12333"))
                .thenReturn("$2a.12.jsdkj3sdsooi2uy");
    }

    @Test
    void shouldSaveClient() {
        Mockito.when(clientRepository.save(clientEntity))
                .thenReturn(clientEntity);
        doReturn(clientEntity)
                .when(clientRegistryDTOService)
                .convertToEntity(clientRegistryDTO);
        doReturn(clientDTO)
                .when(clientDTOService)
                .convertToDTO(clientEntity);
        assertThat("My Name").isEqualTo( clientService.saveClient(clientRegistryDTO).getName());
    }


    private ClientEntity generateClientEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName("My Name");
        clientEntity.setEmail("myemail@gmail.com");
        clientEntity.setEnabled(false);
        clientEntity.setPassword("12333");

        return clientEntity;
    }

    private ClientRegistryDTO generateClientRegistryDTO() {
        ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO();
        clientRegistryDTO.setEmail("myemail@gmail.com");
        clientRegistryDTO.setName("My Name");
        clientRegistryDTO.setPassword("12333");

        return clientRegistryDTO;
    }

    private ClientDTO generateClientDTO() {
        return new ClientDTO(40L, "My Name", "myemail@gmail.com");
    }
}
