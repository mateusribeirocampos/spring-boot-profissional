package com.campos.ecommerce.dto;

import com.campos.ecommerce.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class ProductDto {

    private Long id;

    @Size(min = 3, max = 80, message = "Character number should be between 3 and 80")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 3, message = "Character number should be between 3 and 80")
    @NotBlank(message = "Name cannot be empty")
    private String description;

    @Positive(message = "The price must be positive")
    private Double price;

    private String imgUrl;

    public ProductDto() {}

    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDto(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
