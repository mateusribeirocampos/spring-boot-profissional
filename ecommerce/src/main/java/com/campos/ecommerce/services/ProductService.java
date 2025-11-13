package com.campos.ecommerce.services;

import com.campos.ecommerce.dto.ProductDto;
import com.campos.ecommerce.entities.Product;
import com.campos.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        /*
        * Product product = repository.findById(id).get();
        * return new ProductDto(product);
        * */
        Optional<Product> result = productRepository.findById(id);
        System.out.println("result: " + result);
        Product product = result.get();
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

}
