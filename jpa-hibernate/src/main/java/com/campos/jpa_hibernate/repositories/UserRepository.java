package com.campos.jpa_hibernate.repositories;

import com.campos.jpa_hibernate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
