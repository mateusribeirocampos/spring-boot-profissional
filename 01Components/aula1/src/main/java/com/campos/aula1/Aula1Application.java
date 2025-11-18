package com.campos.aula1;

import com.campos.aula1.entities.Employee;
import com.campos.aula1.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula1Application implements CommandLineRunner {

    @Autowired
    private SalaryService salaryService;

    /*public Aula1Application(SalaryService salaryService) {
        this.salaryService = salaryService;
    }*/

    public static void main(String[] args) {
		SpringApplication.run(Aula1Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee("Maria", 4000.00);
        System.out.println(salaryService.netSalary(employee));
    }
}
