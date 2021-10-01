package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRegistryDTOService extends DTOService<ClientRegistryDTO, ClientEntity> {
    @Autowired
    public ClientRegistryDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ClientEntity convertToEntity(ClientRegistryDTO clientRegistryDTO) {
        return modelMapper.map(clientRegistryDTO, ClientEntity.class);
    }

    @Override
    public ClientRegistryDTO convertToDTO(ClientEntity entity) {
        return modelMapper.map(entity, ClientRegistryDTO.class);
    }
}