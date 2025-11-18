package com.campos.aula1.services;

import com.campos.aula1.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    // dependencies - controller inversion
    @Autowired
    private TaxService taxService;

    @Autowired
    private PensionService pensionService;

    // dependency injection
    /*public SalaryService(TaxService taxService, PensionService pensionService) {
        this.taxService = taxService; // this reference the  attribute was class
        this.pensionService = pensionService;
    }*/

    public double netSalary(Employee employee) {
        System.out.println("TAX: " + taxService.tax(employee.getGrossSalary()) + " AND PENSION: " + pensionService.discount(employee.getGrossSalary()));
        double sum = taxService.tax(employee.getGrossSalary()) + pensionService.discount(employee.getGrossSalary());
        System.out.println("SALARY DISCOUNT SUM : " + sum );
        System.out.println("Name: " + employee.getName());
        return employee.getGrossSalary()  - taxService.tax(employee.getGrossSalary()) - pensionService.discount(employee.getGrossSalary());
    }
}
