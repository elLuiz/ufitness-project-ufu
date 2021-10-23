package com.ufitness.ufitness.repository.instructor;

import com.ufitness.ufitness.repository.user.UserEntity;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class InstructorRepositoryTest {
    @Autowired
    private InstructorRepository instructorRepository;

    @AfterEach
    void tearDown() {
        instructorRepository.deleteAll();
    }

    @Test
    void shouldInsertInstructorIntoUserEntityTable() {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setCpf("14211122211");
        instructorEntity.setCref("23451-G/MG");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("email@email.com");
        userEntity.setPassword("1234");
        userEntity.setName("Test");
        instructorEntity.setUserEntity(userEntity);
        instructorEntity = instructorRepository.save(instructorEntity);
        AssertionsForClassTypes.assertThat(instructorEntity.getId()).isPositive();
    }
}