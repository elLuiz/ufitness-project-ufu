package com.ufitness.ufitness.service.confef;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class ConfefServiceTest {
    @Mock
    private RestTemplate restTemplate;
    private ConfefService confefService;

    @BeforeEach
    void setUp() {
        confefService = new ConfefService(restTemplate);
    }

    @Test
    void shouldReturn200OKWhenInstructorIsRegistered() {
        String cref = "22431-G/MT";
        String confefURL = "http://fake-confef-url/confef/search?=";
        confefService.setConfefURL(confefURL);
        Mockito.when(restTemplate.getForEntity(confefURL + cref, Object.class))
            .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        AssertionsForClassTypes.assertThat(confefService.verifyIfInstructorCREFIsRegistered(cref)).isTrue();
    }

    @Test
    void shouldReturn404WhenInstructorIsNotRegistered() {
        String cref = "22431-G/MT";
        String confefURL = "http://fake-confef-url/confef/search?=";
        confefService.setConfefURL(confefURL);
        Mockito.when(restTemplate.getForEntity(confefURL + cref, Object.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "NOT_FOUND"));
        AssertionsForClassTypes.assertThat(confefService.verifyIfInstructorCREFIsRegistered(cref)).isFalse();
    }
}
