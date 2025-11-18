package com.campos.jpa_hib.resources;

import com.campos.jpa_hib.entities.Category;
import com.campos.jpa_hib.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories/v1")
public class CategoryResource {

  @Autowired
  private CategoryService CategoryService;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> listCategory = CategoryService.findAll();
    return ResponseEntity.ok().body(listCategory);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id) {
    Category obj = CategoryService.findById(id);
    return ResponseEntity.ok().body(obj);
  }
}
