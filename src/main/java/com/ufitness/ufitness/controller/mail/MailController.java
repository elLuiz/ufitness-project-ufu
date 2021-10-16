package com.ufitness.ufitness.controller.mail;

import com.ufitness.ufitness.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("")
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "/api/v1/ufitness/mail/confirm", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET, RequestMethod.PATCH})
    public ResponseEntity<Map<String, String>> validateMailToken(@RequestParam("token") String token) {
        mailService.validateMailToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("action", "SUCCESS"));
    }
}