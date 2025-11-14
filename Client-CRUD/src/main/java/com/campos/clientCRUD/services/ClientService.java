package com.campos.clientCRUD.services;

import com.campos.clientCRUD.dto.ClientDto;
import com.campos.clientCRUD.entities.Client;
import com.campos.clientCRUD.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        List<Client> entityList = clientRepository.findAll();
        return entityList.stream().map(ClientDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Client entity = clientRepository.findById(id).get();
        return new ClientDto(entity);
    }

    


}
