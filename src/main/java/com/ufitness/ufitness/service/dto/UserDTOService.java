package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOService extends DTOService<ClientRegistryDTO, UserEntity> {
    @Autowired
    public UserDTOService(ModelMapper modelMapper) {
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