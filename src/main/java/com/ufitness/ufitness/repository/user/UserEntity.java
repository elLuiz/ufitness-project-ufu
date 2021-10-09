package com.ufitness.ufitness.repository.user;

import com.ufitness.ufitness.repository.client.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column(columnDefinition = "bool default false")
    private boolean enabled;
    @Column
    private UserTypeEnum userTypeEnum;
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private ClientEntity clientEntity;

    public void insertClientDetails(ClientEntity clientEntity) {
        this.setClientEntity(clientEntity);
        this.setUserTypeEnum(UserTypeEnum.CLIENT);
    }
}