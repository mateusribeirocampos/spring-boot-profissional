package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Long id;
    
    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    
    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    
    @Positive(message = "O preço deve ser positivo")
    private Double price;
    
    private String imgUrl;

    @NotEmpty(message = "Deve haver pelo menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();
    
    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        for  (Category cat : entity.getCategories()) {
            System.out.println("****** for CategoryDTO ******");
            System.out.println("ProductDTO cat.getName: " + cat.getName());
            System.out.println("ProductDTO Category tamanho: " + categories.size());
            categories.add(new CategoryDTO(cat));
            System.out.println("ProductDTO Category tamanho depois do new CategoryDTO: " +categories.size());
            System.out.println("ProductDTO cat.getName depois do new CategoryDTO: " +categories.get(0).getName());
        }
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
