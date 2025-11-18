package com.campos.onetoone.services;

import com.campos.onetoone.controllers.PersonController;
import com.campos.onetoone.dto.PersonDepartmentDto;
import com.campos.onetoone.dto.PersonDto;
import com.campos.onetoone.entities.Department;
import com.campos.onetoone.entities.Person;
import com.campos.onetoone.repositories.DepartmentRepository;
import com.campos.onetoone.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PersonRepository personRepository;

    public PersonDepartmentDto create(PersonDepartmentDto dto) {
        logger.info("Creating personDepartmentDto with name: {}", dto.getName());
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

//        Department dept = new Department();
        Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId());

//        dept.setId(dto.getDepartment().getId());

        // association
        entity.setDepartment(dept);

        entity = personRepository.save(entity);

        logger.info("PersonDepartment with name {} created successfully", dto.getName());
        return new PersonDepartmentDto(entity);
    }

    public PersonDto create(PersonDto dto) {
        logger.info("Creating personDto with name: {}", dto.getName());
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        // Department dept = new Department();
        // dept.setId(dto.getDepartmentId());
        Department dept = departmentRepository.getReferenceById(dto.getDepartmentId());

        // association
        entity.setDepartment(dept);

        entity = personRepository.save(entity);

        logger.info("Person with name {} created successfully", dto.getName());
        return new PersonDto(entity);
    }

}
