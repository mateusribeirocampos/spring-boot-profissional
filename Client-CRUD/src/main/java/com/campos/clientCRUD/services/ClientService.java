package com.campos.clientCRUD.services;

import com.campos.clientCRUD.dto.ClientDto;
import com.campos.clientCRUD.entities.Client;
import com.campos.clientCRUD.repositories.ClientRepository;
import com.campos.clientCRUD.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        logger.info("Finding all clients");
        List<Client> entityList = clientRepository.findAll();
        return entityList.stream().map(ClientDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        logger.info("GET /clients/{} - Finding a client", id);
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto create(ClientDto dto) {
        logger.info("Creating a client with name: {}", dto.getName());
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

        entity = clientRepository.save(entity);
        return new ClientDto(entity);
    }




}
