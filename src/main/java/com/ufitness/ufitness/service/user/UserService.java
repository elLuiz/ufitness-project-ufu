package com.ufitness.ufitness.service.user;

import com.ufitness.ufitness.exception.UserNotFoundException;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public void loginUser(LoginDTO loginDTO) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.loginUser(loginDTO.getEmail());
        if (userEntity.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), userEntity.get().getPassword()))
            throw new UserNotFoundException("USER_NOT_FOUND");
    }
}