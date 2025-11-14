package com.campos.clientCRUD.controllers;

import com.campos.clientCRUD.dto.ClientDto;
import com.campos.clientCRUD.entities.Client;
import com.campos.clientCRUD.repositories.ClientRepository;
import com.campos.clientCRUD.services.ClientService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> dto = clientService.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto dto = clientService.findById(id);
        return ResponseEntity.ok(dto);
    }

}
