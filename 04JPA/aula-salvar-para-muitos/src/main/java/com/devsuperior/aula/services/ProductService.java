package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDto;
import com.devsuperior.aula.dto.ProductDto;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto create(ProductDto dto) {
        logger.info("Creating productDto with name: {}", dto.getName());
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDto catDto : dto.getCategories()) {
            //Category categ = new Category();
            Category categ = categoryRepository.getReferenceById(catDto.getId());
            //categ.setId(catDto.getId());

            entity.getCategories().add(categ);
        }
        entity = productRepository.save(entity);

        logger.info("ProductDto with name {} created successfully", dto.getName());
        return new ProductDto(entity);
    }
}
