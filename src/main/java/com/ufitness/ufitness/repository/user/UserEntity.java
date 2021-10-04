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
    @Column(name = "client_id")
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
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public void insertClientDetails(ClientEntity clientEntity) {
        this.setClientEntity(clientEntity);
        this.setUserTypeEnum(UserTypeEnum.CLIENT);
    }
}