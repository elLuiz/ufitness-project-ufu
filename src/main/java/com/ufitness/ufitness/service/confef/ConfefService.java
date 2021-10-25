package com.ufitness.ufitness.service.confef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfefService {
    private static final Logger LOG = LoggerFactory.getLogger("Confef");
    @Value("${confef.url}")
    private String confefURL;
    private final RestTemplate restTemplate;

    @Autowired
    public ConfefService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyIfInstructorCREFIsRegistered(String cref) {
        try {
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(confefURL + cref, Object.class);
            return responseEntity.getStatusCode().equals(HttpStatus.OK);
        } catch (HttpClientErrorException httpClientErrorException) {
            LOG.error(httpClientErrorException.getMessage());
            return false;
        }
    }

    public void setConfefURL(String confefURL) {
        this.confefURL = confefURL;
    }
}