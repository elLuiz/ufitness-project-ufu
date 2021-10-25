package com.ufitness.ufitness.service.dto.client;

import com.ufitness.ufitness.repository.client.ClientEntity;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.dto.client.ClientRegistryDTO;
import com.ufitness.ufitness.service.dto.DTOService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRegistryDTOService extends DTOService<ClientRegistryDTO, ClientEntity> {
    private UserClientDTOService userClientDTOService;
    @Autowired
    public ClientRegistryDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ClientEntity convertToEntity(ClientRegistryDTO clientRegistryDTO) {
        UserEntity userEntity = userClientDTOService.convertToEntity(clientRegistryDTO);
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
    public void setUserDTOService(UserClientDTOService userClientDTOService) {
        this.userClientDTOService = userClientDTOService;
    }
}