package com.campos.jpa_hib.services;

import com.campos.jpa_hib.entities.Category;
import com.campos.jpa_hib.repositories.CategoryRepository;
import com.campos.jpa_hib.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;

    public List<Category> findAll() {
        return CategoryRepository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = CategoryRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
