package com.campos.jpa_hib.resources;

import com.campos.jpa_hib.entities.Order;
import com.campos.jpa_hib.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders/v1")
public class OrderResource {

  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<List<Order>> findAll() {
    List<Order> listOrder = orderService.findAll();
    return ResponseEntity.ok().body(listOrder);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Order> findById(@PathVariable Long id) {
    Order obj = orderService.findById(id);
    return ResponseEntity.ok().body(obj);
  }
}
