package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOService extends DTOService<ClientDTO, ClientEntity> {
    @Autowired
    public ClientDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ClientEntity convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    @Override
    public ClientDTO convertToDTO(ClientEntity entity) {
        return modelMapper.map(entity, ClientDTO.class);
    }
}
