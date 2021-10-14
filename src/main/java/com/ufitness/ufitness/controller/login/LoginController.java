package com.ufitness.ufitness.controller.login;

import com.ufitness.ufitness.dto.LoginDTO;
import com.ufitness.ufitness.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        userService.loginUser(loginDTO);
        return ResponseEntity.ok(Collections.singletonMap("result", "success"));
    }
}
