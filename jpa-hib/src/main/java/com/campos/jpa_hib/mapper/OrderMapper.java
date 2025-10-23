package com.campos.jpa_hib.mapper;

import com.campos.jpa_hib.dto.v1.orderDto.OrderResponseDto;
import com.campos.jpa_hib.dto.v1.orderItemDto.OrderItemDto;
import com.campos.jpa_hib.dto.v1.paymentDto.PaymentDto;
import com.campos.jpa_hib.dto.v1.userDto.UserSummaryDto;
import com.campos.jpa_hib.entities.Order;
import com.campos.jpa_hib.entities.OrderItem;
import com.campos.jpa_hib.entities.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    
    /**
     * convert entity Order to OrderResponseDto
     * **/
    public OrderResponseDto toResponseDto(Order entity) {
        if (entity == null) return null;
        
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(entity.getId());
        dto.setMoment(entity.getMoment());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setTotal(entity.getTotal());
        
        if (entity.getClient() != null) {
            UserSummaryDto clientDto = new UserSummaryDto(
                    entity.getClient().getId(),
                    entity.getClient().getName()
            );
            dto.setClient(clientDto);
        }

        List<OrderItemDto> itemsDto = entity.getItems().stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
        dto.setItems(itemsDto);
        
        if (entity.getPayment() != null) {
            dto.setPayment(toPaymentDto(entity.getPayment()));
        }

        return dto;
    }

    /**
     * Convert Payment para PaymentDto
     */
    private PaymentDto toPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getMoment()
        );
    }

    private OrderItemDto toOrderItemDto(OrderItem item) {
        return new OrderItemDto(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getSubTotal()
        );
    }
}
