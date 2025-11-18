package com.campos.jpa_hib.services;

import com.campos.jpa_hib.dto.v1.orderDto.OrderResponseDto;
import com.campos.jpa_hib.entities.Order;
import com.campos.jpa_hib.mapper.OrderMapper;
import com.campos.jpa_hib.repositories.OrderRepository;
import com.campos.jpa_hib.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderMapper orderMapper;

//  public List<Order> findAll() {
//    return orderRepository.findAll();
//  }

    public List<OrderResponseDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResponseDtoList(orders);
    }

//  public Order findById(Long id) {
//    Optional<Order> obj = orderRepository.findById(id);
//    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
//  }

    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return orderMapper.toResponseDto(order);
    }
}
