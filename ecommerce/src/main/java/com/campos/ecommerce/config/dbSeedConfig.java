package com.campos.ecommerce.config;

import com.campos.ecommerce.entities.Order;
import com.campos.ecommerce.entities.User;
import com.campos.ecommerce.entities.enums.OrderStatus;
import com.campos.ecommerce.repositories.OrderRepository;
import com.campos.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile({"test"})
public class dbSeedConfig implements CommandLineRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping seed....");
            return;
        }

        System.out.println("Seeding database...");

        User u1 = new User(null, "Alex Brown", "alex.brown@email.com", "35998632564", LocalDate.parse("25/06/1983", dtf), "123456", new String[]{"user", "Admin"});
        User u2 = new User(null, "Maria Gray", "maria.gray@email2.com","35996258741", LocalDate.parse("05/12/1984", dtf), "123456", new String[]{"user", "Admin"});


        Order o1 = new Order(null, Instant.now(), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.now(), OrderStatus.SHIPPED,u2);

        userRepository.saveAll(Arrays.asList(u1,u2));
        for (User u : Arrays.asList(u1, u2)) {
            System.out.println("✓ User seeded: " + u.getName());
        }

        orderRepository.saveAll(Arrays.asList(o1, o2));
        for (Order o : Arrays.asList(o1, o2)) {
            System.out.println("✓ Order seeded: " + o.getOrderStatus());
        }
    }
}
