package com.campos.jpa_hib.config;

import com.campos.jpa_hib.entities.Category;
import com.campos.jpa_hib.entities.Order;
import com.campos.jpa_hib.entities.User;
import com.campos.jpa_hib.entities.enums.OrderStatus;
import com.campos.jpa_hib.repositories.CategoryRepository;
import com.campos.jpa_hib.repositories.OrderRepository;
import com.campos.jpa_hib.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class DbSeedConfig implements CommandLineRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria.brown@gmail.com", LocalDate.parse("26/04/1986", dtf), "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex.green@gmail.com", LocalDate.parse("04/07/1979", dtf),"966666666", "123456");
        User u3 = new User(null, "Kevin Blue", "kevin.blue@gmail.com", LocalDate.parse("26/11/1981", dtf),"944444556", "123456");

        Order o1 = new Order(null, Instant.parse("2025-10-13T17:57:03Z"), OrderStatus.PAID, u1 );
        Order o2 = new Order(null, Instant.parse("2025-10-13T17:58:11Z"), OrderStatus.WAITING_PAYMENT, u1 );
        Order o3 = new Order(null, Instant.parse("2025-10-13T18:03:26Z"), OrderStatus.DELIVERED, u1 );
        Order o4 = new Order(null, Instant.parse("2025-10-13T17:57:03Z"), OrderStatus.CANCELED, u2 );
        Order o5 = new Order(null, Instant.parse("2025-10-13T17:58:11Z"), OrderStatus.WAITING_PAYMENT, u2 );
        Order o6 = new Order(null, Instant.parse("2025-10-13T18:03:26Z"), OrderStatus.SHIPPED, u3 );

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    }
}
