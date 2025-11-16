package com.campos.clientCRUD.controllers;

import com.campos.clientCRUD.dto.ClientDto;
import com.campos.clientCRUD.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> findAll() {
        logger.info("GET /clients - Finding all clients");
        List<ClientDto> dto = clientService.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        logger.info("GET /clients/{} - Finding one client", id);
        ClientDto dto = clientService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto dto) {
        logger.info("POST /clients - Creating a client with name: {} ", dto.getName());
        dto = clientService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto dto) {
        logger.info("PUT /clients/{} - Updating client with name: {}", id, dto.getName());
        dto = clientService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("DELETE /clients/{} - Deleting client", id);
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
