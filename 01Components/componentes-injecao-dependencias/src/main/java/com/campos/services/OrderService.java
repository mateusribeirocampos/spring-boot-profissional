package com.campos.services;


import com.campos.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ShippingService shippingService;

    public double total(Order order) {
        double discount =  order.getBasic() * order.getDiscount() / 100;
        double shipment = shippingService.shipment(order);
        return order.getBasic() - discount  + shipment;
    }
}
