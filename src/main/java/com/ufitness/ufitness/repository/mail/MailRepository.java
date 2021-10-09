package com.ufitness.ufitness.repository.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<MailEntity, Long> {
    Optional<MailEntity> findMailEntityByToken(String token);
}