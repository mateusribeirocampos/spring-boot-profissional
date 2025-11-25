package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(nativeQuery = true, value = "SELECT products.name " +
            "FROM products " +
            "JOIN providers ON products.id_providers=providers.id " +
            "WHERE products.amount BETWEEN :min AND :max " +
            "AND providers.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String beginName);
}
