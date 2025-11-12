package com.campos.ecommerce.controllers;

import com.campos.ecommerce.dto.ProductDto;
import com.campos.ecommerce.entities.Product;
import com.campos.ecommerce.repositories.ProductRepository;
import com.campos.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}