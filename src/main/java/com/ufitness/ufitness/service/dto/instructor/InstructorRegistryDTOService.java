package com.ufitness.ufitness.service.dto.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.repository.instructor.InstructorEntity;
import com.ufitness.ufitness.service.dto.DTOService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructorRegistryDTOService extends DTOService<InstructorRegistryDTO, InstructorEntity> {
    @Autowired
    public InstructorRegistryDTOService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public InstructorEntity convertToEntity(InstructorRegistryDTO modelDTO) {
        InstructorEntity instructorEntity = modelMapper.map(modelDTO, InstructorEntity.class);
        instructorEntity.createUserEntityFromDTOObject(modelDTO);
        return instructorEntity;
    }

    @Override
    public InstructorRegistryDTO convertToDTO(InstructorEntity entity) {
        return modelMapper.map(entity, InstructorRegistryDTO.class);
    }
}
