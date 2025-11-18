package com.campos.clientCRUD.services;

import com.campos.clientCRUD.dto.ClientDto;
import com.campos.clientCRUD.entities.Client;
import com.campos.clientCRUD.repositories.ClientRepository;
import com.campos.clientCRUD.services.exceptions.DatabaseException;
import com.campos.clientCRUD.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        logger.info("Finding all clients");
        Page<Client> entityList = clientRepository.findAll(pageable);
        logger.info("All clients were found successfully");
        return entityList.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        logger.info("Finding one client with id: {}", id);
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        logger.info("Client with id: {} was found successfully", id);
        return new ClientDto(entity);

    }

    @Transactional
    public ClientDto create(ClientDto dto) {
        logger.info("Creating a client with name: {}", dto.getName());
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = clientRepository.save(entity);
        logger.info("The client with id: {} was created successfully", dto.getName());
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        logger.info("Updating client with id: {} and name: {}", id, dto.getName());
        try {
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clientRepository.save(entity);
            logger.info("The client with id: {} and name {} was updated successfully", id, dto.getName());
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseException("Entity not found with id: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        logger.info("Deleting client with id: {} ", id);
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found for id: " + id);
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
        logger.info("Client deleted successfully!");
    }

    private void copyDtoToEntity(ClientDto dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }


}
