package com.ufitness.ufitness.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("SELECT user FROM UserEntity user WHERE user.email = ?1")
    Optional<UserEntity> loginUser(String email);
}