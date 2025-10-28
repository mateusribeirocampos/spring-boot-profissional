package com.campos.ecommerce.config;

import com.campos.ecommerce.entities.User;
import com.campos.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@Profile({"test"})
public class dbSeedConfig implements CommandLineRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping seed....");
            return;
        }

        User u1 = new User(null, "First name", "email@email.com","35996258741", LocalDate.parse("25/06/1983", dtf), "123456", new String[]{"user", "Admin"});

        userRepository.save(u1);
        System.out.println("✓ User seeded: " + u1.getName());
    }
}
