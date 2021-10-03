package com.ufitness.ufitness.service.client;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.client.ClientRepository;
import com.ufitness.ufitness.service.dto.ClientDTOService;
import com.ufitness.ufitness.service.dto.ClientRegistryDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientRegistryDTOService clientRegistryDTOService;
    private final ClientDTOService clientDTOService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientRegistryDTOService clientRegistryDTOService, ClientDTOService clientDTOService) {
        this.clientRepository = clientRepository;
        this.clientRegistryDTOService = clientRegistryDTOService;
        this.clientDTOService = clientDTOService;
    }

    @Transactional
    public ClientDTO saveClient(ClientRegistryDTO clientRegistryDTO) {
        ClientEntity clientEntity = clientRegistryDTOService.convertToEntity(clientRegistryDTO);
        hashPassword(clientEntity);
        ClientEntity savedClient = clientRepository.save(clientEntity);
        return clientDTOService.convertToDTO(savedClient);
    }

    private void hashPassword(ClientEntity clientEntity) {
        if (clientEntity.getUserEntity() != null)
            clientEntity.getUserEntity().setPassword(passwordEncoder.encode(clientEntity.getUserEntity().getPassword()));
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}