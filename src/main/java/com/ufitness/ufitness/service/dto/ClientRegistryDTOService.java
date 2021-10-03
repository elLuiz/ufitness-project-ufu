package com.ufitness.ufitness.service.dto;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.service.client.ClientRegistryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRegistryDTOService extends DTOService<ClientRegistryDTO, ClientEntity> {
    private UserDTOService userDTOService;
    @Autowired
    public ClientRegistryDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ClientEntity convertToEntity(ClientRegistryDTO clientRegistryDTO) {
        UserEntity userEntity = userDTOService.convertToEntity(clientRegistryDTO);
        ClientEntity clientEntity = new ClientEntity();
        userEntity.insertClientDetails(clientEntity);
        clientEntity.setUserEntity(userEntity);
        return clientEntity;
    }

    @Override
    public ClientRegistryDTO convertToDTO(ClientEntity entity) {
        return modelMapper.map(entity, ClientRegistryDTO.class);
    }

    @Autowired
    public void setUserDTOService(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }
}