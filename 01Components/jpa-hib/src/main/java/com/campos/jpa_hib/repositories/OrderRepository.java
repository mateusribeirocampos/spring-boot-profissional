package com.campos.jpa_hib.repositories;

import com.campos.jpa_hib.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
