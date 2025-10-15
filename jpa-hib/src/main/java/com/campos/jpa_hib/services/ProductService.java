package com.campos.jpa_hib.services;

import com.campos.jpa_hib.entities.Product;
import com.campos.jpa_hib.repositories.ProductRepository;
import com.campos.jpa_hib.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository ProductRepository;

    public List<Product> findAll() {
        return ProductRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = ProductRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
