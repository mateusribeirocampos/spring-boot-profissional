package com.campos.jpa_hib.repositories;

import com.campos.jpa_hib.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
