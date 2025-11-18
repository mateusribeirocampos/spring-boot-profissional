package com.campos.onetoone.controllers;

import com.campos.onetoone.dto.PersonDepartmentDto;
import com.campos.onetoone.dto.PersonDto;
import com.campos.onetoone.services.PersonService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/people")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    //@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
    //        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDepartmentDto> create(@RequestBody PersonDepartmentDto dto) {
        logger.info("POST /people - Creating personDepartmentDto with name: {}", dto.getName());
        dto = personService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto dto) {
        logger.info("POST /people - Creating personDto with name: {}", dto.getName());
        dto = personService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
