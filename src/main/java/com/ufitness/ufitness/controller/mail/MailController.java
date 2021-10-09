package com.ufitness.ufitness.controller.mail;

import com.ufitness.ufitness.exception.InvalidTokenException;
import com.ufitness.ufitness.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ufitness/mail")
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> validateMailToken(@RequestParam("token") String token) {
        try {
            mailService.validateMailToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("action", "SUCCESS"));
        } catch (InvalidTokenException invalidTokenException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", invalidTokenException.getMessage()));
        }
    }
}