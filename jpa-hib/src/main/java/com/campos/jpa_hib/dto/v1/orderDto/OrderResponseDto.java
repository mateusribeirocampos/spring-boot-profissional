package com.campos.jpa_hib.dto.v1.orderDto;

import com.campos.jpa_hib.dto.v1.orderItemDto.OrderItemDto;
import com.campos.jpa_hib.dto.v1.paymentDto.PaymentDto;
import com.campos.jpa_hib.dto.v1.userDto.UserSummaryDto;
import com.campos.jpa_hib.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderResponseDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private OrderStatus orderStatus;
    private UserSummaryDto client;
    private List<OrderItemDto> items = new ArrayList<>();
    private PaymentDto payment;
    private Double total;

    public OrderResponseDto() {}

    public OrderResponseDto(Long id, Instant moment, OrderStatus orderStatus, UserSummaryDto client, Double total) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UserSummaryDto getClient() {
        return client;
    }

    public void setClient(UserSummaryDto client) {
        this.client = client;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDto that = (OrderResponseDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
