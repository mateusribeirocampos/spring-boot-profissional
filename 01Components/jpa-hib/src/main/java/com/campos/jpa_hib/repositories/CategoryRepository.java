package com.campos.jpa_hib.repositories;

import com.campos.jpa_hib.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
