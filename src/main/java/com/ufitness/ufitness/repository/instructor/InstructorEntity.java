package com.ufitness.ufitness.repository.instructor;

import com.ufitness.ufitness.dto.instructor.InstructorRegistryDTO;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(length = 11)
    private String cpf;
    @Column(length = 13)
    private String cref;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public InstructorEntity(String cpf, String cref, UserEntity userEntity) {
        this.cpf = cpf;
        this.cref = cref;
        this.userEntity = userEntity;
    }

    public void createUserEntityFromDTOObject(InstructorRegistryDTO instructorRegistryDTO) {
        userEntity = new UserEntity();
        userEntity.setName(instructorRegistryDTO.getName());
        userEntity.setPassword(instructorRegistryDTO.getPassword());
        userEntity.setEmail(instructorRegistryDTO.getEmail());
        userEntity.setUserTypeEnum(UserTypeEnum.INSTRUCTOR);
        userEntity.setInstructorEntity(this);
    }
}