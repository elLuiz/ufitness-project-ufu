package com.ufitness.ufitness.controller.login;

import com.ufitness.ufitness.exception.UserNotFoundException;
import com.ufitness.ufitness.service.user.LoginDTO;
import com.ufitness.ufitness.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ufitness/login")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            userService.loginUser(loginDTO);
            return ResponseEntity.ok(Collections.singletonMap("result", "success"));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", userNotFoundException.getMessage()));
        }
    }
}
