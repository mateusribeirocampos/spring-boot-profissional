package com.campos.ecommerce.services;

import com.campos.ecommerce.dto.ProductDto;
import com.campos.ecommerce.entities.Product;
import com.campos.ecommerce.repositories.ProductRepository;
import com.campos.ecommerce.services.exceptions.DatabaseException;
import com.campos.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        /*
        * Product product = productRepository.findById(id).get();
        * return new ProductDto(product);
        * */
        Optional<Product> result = productRepository.findById(id);
        System.out.println("result: " + result);
        Product product = result.orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));;
        System.out.println("product: " + product.getName());
        //ProductDto dto = new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
        ProductDto dto = new ProductDto(product);
        System.out.println("dto: " + dto.getName());
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.map(x -> new ProductDto(x));
    }

    @Transactional
    public ProductDto create(ProductDto dto) {
        Product entity = new Product();

        copyDtoToEntity(dto, entity);

        entity = productRepository.save(entity);
        System.out.println("entity created: " + entity.getName());
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            Product entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            System.out.println("entity updated: " + entity.getName());
            return new ProductDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found for id: " + id);
        }
    }

    private void copyDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found for id: " + id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("referential integrity failure");
        }
    }

}
