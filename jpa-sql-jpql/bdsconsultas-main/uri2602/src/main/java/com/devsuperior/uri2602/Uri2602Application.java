package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dto.CustomerMinDto;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        List<CustomerMinProjection> list = customerRepository.search1("rs");
        List<CustomerMinDto> result1 = list.stream()
                .map(x -> new CustomerMinDto(x))
                .collect(Collectors.toList());

        System.out.println("\n**** RESULTADO SQL RAIZ");
        for (CustomerMinDto obj : result1) {
            System.out.println(obj);
        }

        System.out.println("\n\n");
        List<CustomerMinDto> result2 = customerRepository.search2("rs");

        System.out.println("\n**** RESULTADO JPQL");
        for (CustomerMinDto obj : result2) {
            System.out.println(obj);
        }

    }
}
