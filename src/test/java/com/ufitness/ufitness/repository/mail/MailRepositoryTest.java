package com.ufitness.ufitness.repository.mail;

import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserTypeEnum;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MailRepositoryTest {
    @Autowired
    private MailRepository mailRepository;

    @Test
    void shouldCreateMailEntity() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setEmailStatus(EmailStatus.SENT);
        mailEntity.setToken("43984983498439348@akxjkxj");
        mailEntity.setUserEntity(userEntity());
        AssertionsForClassTypes.assertThat(mailRepository.save(mailEntity).getId()).isNotNull();
    }

    private UserEntity userEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(40L);
        userEntity.setUserTypeEnum(UserTypeEnum.CLIENT);
        userEntity.setName("Name");

        return userEntity;
    }
}
