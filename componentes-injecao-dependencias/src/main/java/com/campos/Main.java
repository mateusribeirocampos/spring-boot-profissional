package com.campos;

import com.campos.entities.Order;
import com.campos.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order(1034, 150.00, 20.0);
        Order order2 = new Order(2282, 800.00, 10.0);
        Order order3 = new Order(1039, 95.90, 0.0);

        System.out.println("Código do pedido: " + order1.getCode());
        System.out.printf("Valor total: R$ %.2f", orderService.total(order1));
        System.out.println("\n");
        System.out.println("Código do pedido: " + order2.getCode());
        System.out.printf("Valor total: R$ %.2f", orderService.total(order2));
        System.out.println("\n");
        System.out.println("Código do pedido: " + order3.getCode());
        System.out.printf("Valor total: R$ %.2f", orderService.total(order3));
    }
}
