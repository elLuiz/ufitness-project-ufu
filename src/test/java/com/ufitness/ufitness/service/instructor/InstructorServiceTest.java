package com.ufitness.ufitness.service.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.exception.InstructorCREFNotFoundException;
import com.ufitness.ufitness.repository.instructor.InstructorEntity;
import com.ufitness.ufitness.repository.instructor.InstructorRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.service.confef.ConfefService;
import com.ufitness.ufitness.service.dto.instructor.InstructorRegistryDTOService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private InstructorRegistryDTOService instructorRegistryDTOService;
    @Mock
    private ConfefService confefService;
    private InstructorService instructorService;

    @BeforeEach
    void setUp() {
        instructorService = new InstructorService(passwordEncoder, instructorRepository, instructorRegistryDTOService, confefService);
    }

    @Test
    void shouldSaveInstructor() {
        Mockito.when(confefService.verifyIfInstructorCREFIsRegistered("23456-G/SP"))
                .thenReturn(true);
        Mockito.when(passwordEncoder.encode("123"))
                .thenReturn("$2a12$abcdefghx.kjskjs\\salklsak");
        InstructorEntity instructorEntity = instructorEntity();
        InstructorRegistryDTO modelDTO = instructorRegistryDTOService();
        Mockito.when(instructorRegistryDTOService.convertToEntity(modelDTO))
                .thenReturn(instructorEntity);
        Mockito.when(instructorRepository.save(instructorEntity))
                .thenReturn(instructorEntity);
        instructorService.saveInstructor(modelDTO);
        AssertionsForClassTypes.assertThat(instructorEntity.getUserEntity().getPassword()).isEqualTo("$2a12$abcdefghx.kjskjs\\salklsak");
    }
    
    private InstructorRegistryDTO instructorRegistryDTOService() {
        InstructorRegistryDTO instructorRegistryDTO = new InstructorRegistryDTO();
        instructorRegistryDTO.setCpf("123456789101");
        instructorRegistryDTO.setPassword("123");
        instructorRegistryDTO.setCref("23456-G/SP");
        instructorRegistryDTO.setName("Name");
        instructorRegistryDTO.setEmail("email@email.com");
        return instructorRegistryDTO;
    }

    private InstructorEntity instructorEntity() {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.createUserEntityFromDTOObject(instructorRegistryDTOService());
        return instructorEntity;
    }

    @Test
    void shouldThrowInstructorCREFNotFoundExceptionWhenCREFDoesNotExist() {
        InstructorRegistryDTO instructorRegistryDTO = new InstructorRegistryDTO();
        instructorRegistryDTO.setCref("231221-G/MF");
        try {
            instructorService.saveInstructor(instructorRegistryDTO);
        } catch (InstructorCREFNotFoundException instructorCREFNotFoundException) {
            AssertionsForClassTypes.assertThat(instructorCREFNotFoundException.getMessage()).isEqualTo("INSTRUCTOR_CREF_NOT_FOUND");
        }
    }

}