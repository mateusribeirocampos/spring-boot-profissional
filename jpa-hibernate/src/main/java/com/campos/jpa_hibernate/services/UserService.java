package com.campos.jpa_hibernate.services;

import com.campos.jpa_hibernate.entities.User;
import com.campos.jpa_hibernate.repositories.UserRepository;
import com.campos.jpa_hibernate.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
