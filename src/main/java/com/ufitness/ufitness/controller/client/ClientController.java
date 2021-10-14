package com.ufitness.ufitness.controller.client;

import com.ufitness.ufitness.dto.ClientDTO;
import com.ufitness.ufitness.dto.ClientRegistryDTO;
import com.ufitness.ufitness.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ufitness/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientRegistryDTO clientRegistryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientRegistryDTO));
    }
}