package com.ufitness.ufitness.service.dto.client;

import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.dto.client.ClientRegistryDTO;
import com.ufitness.ufitness.service.dto.DTOService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserClientDTOService extends DTOService<ClientRegistryDTO, UserEntity> {
    @Autowired
    public UserClientDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserEntity convertToEntity(ClientRegistryDTO modelDTO) {
        return modelMapper.map(modelDTO, UserEntity.class);
    }

    @Override
    public ClientRegistryDTO convertToDTO(UserEntity entity) {
        return modelMapper.map(entity, ClientRegistryDTO.class);
    }
}