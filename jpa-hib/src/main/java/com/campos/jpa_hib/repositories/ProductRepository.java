package com.campos.jpa_hib.repositories;

import com.campos.jpa_hib.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
