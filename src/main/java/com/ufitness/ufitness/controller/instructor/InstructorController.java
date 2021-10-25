package com.ufitness.ufitness.controller.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ufitness/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveInstructor(@Valid @RequestBody InstructorRegistryDTO instructorRegistryDTO) {
        instructorService.saveInstructor(instructorRegistryDTO);
        return new ResponseEntity<>(Collections.singletonMap("message", "SUCCESS"), HttpStatus.CREATED);
    }
}
