package com.campos.jpa_hib.dto.v1.orderItemDto;

import com.campos.jpa_hib.entities.pk.OrderItemPk;

import java.util.Objects;

public class OrderItemDto {

    private Long ProductId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double subTotal;

    public OrderItemDto() {}

    public OrderItemDto(Long productId, String productName, Integer quantity, Double price, Double subTotal) {
        ProductId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDto that = (OrderItemDto) o;
        return Objects.equals(ProductId, that.ProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ProductId);
    }
}
