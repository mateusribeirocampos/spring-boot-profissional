package com.campos.jpa_hibernate.resources;

import com.campos.jpa_hibernate.entities.User;
import com.campos.jpa_hibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class UserResource {

    /*@Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable  Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }*/
}
