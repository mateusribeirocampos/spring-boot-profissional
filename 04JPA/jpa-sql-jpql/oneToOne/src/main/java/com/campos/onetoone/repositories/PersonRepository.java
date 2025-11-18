package com.campos.onetoone.repositories;

import com.campos.onetoone.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
