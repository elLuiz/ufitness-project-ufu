package com.ufitness.ufitness.service.dto.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.repository.instructor.InstructorEntity;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class InstructorRegistryDtoServiceTest {
    private ModelMapper modelMapper;
    private InstructorRegistryDTOService instructorRegistryDTOService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        instructorRegistryDTOService = new InstructorRegistryDTOService(modelMapper);
    }

    @Test
    void shouldConvertToEntity() {
        InstructorRegistryDTO instructorRegistryDTO = new InstructorRegistryDTO();
        instructorRegistryDTO.setEmail("email@email.com");
        instructorRegistryDTO.setName("My name");
        instructorRegistryDTO.setPassword("123");
        instructorRegistryDTO.setCref("23456-G/M");
        instructorRegistryDTO.setCpf("13400011100");
        AssertionsForClassTypes.assertThat(instructorRegistryDTOService.convertToEntity(instructorRegistryDTO).getCpf()).isEqualTo("13400011100");
    }

    @Test
    void shouldConvertToDTO() {
        InstructorEntity instructorEntity = new InstructorEntity("123", "23412-G/SP", null);
        AssertionsForClassTypes.assertThat(instructorRegistryDTOService.convertToDTO(instructorEntity).getCref()).isEqualTo("23412-G/SP");
    }

}
