package com.campos.jpa_hib.repositories;

import com.campos.jpa_hib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
