package com.ufitness.ufitness.service.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.exception.InstructorCREFNotFoundException;
import com.ufitness.ufitness.repository.instructor.InstructorEntity;
import com.ufitness.ufitness.repository.instructor.InstructorRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.service.confef.ConfefService;
import com.ufitness.ufitness.service.dto.instructor.InstructorRegistryDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService {
    private final PasswordEncoder passwordEncoder;
    private final InstructorRepository instructorRepository;
    private final InstructorRegistryDTOService instructorRegistryDTOService;
    private final ConfefService confefService;

    @Autowired
    public InstructorService(PasswordEncoder passwordEncoder, InstructorRepository instructorRepository, InstructorRegistryDTOService instructorRegistryDTOService, ConfefService confefService) {
        this.passwordEncoder = passwordEncoder;
        this.instructorRepository = instructorRepository;
        this.instructorRegistryDTOService = instructorRegistryDTOService;
        this.confefService = confefService;
    }

    @Transactional
    public void saveInstructor(InstructorRegistryDTO instructorRegistryDTO) {
        if (confefService.verifyIfInstructorCREFIsRegistered(instructorRegistryDTO.getCref())) {
            InstructorEntity instructorEntity = instructorRegistryDTOService.convertToEntity(instructorRegistryDTO);
            UserEntity userEntity = instructorEntity.getUserEntity();
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            instructorRepository.save(instructorEntity);
        } else {
            throw new InstructorCREFNotFoundException("INSTRUCTOR_CREF_NOT_FOUND");
        }
    }
}