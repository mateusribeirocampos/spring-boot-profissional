package org.example.services;

import org.example.model.Employee;

public class SalaryService {

    // dependencies - controller inversion
    private TaxService taxService;
    private PensionService pensionService;

    // dependency injection
    public SalaryService(TaxService taxService, PensionService pensionService) {
        this.taxService = taxService; // this reference the  attribute was class
        this.pensionService = pensionService;
    }

    public double netSalary(Employee employee) {
        System.out.println("TAX: " + taxService.tax(employee.getGrossSalary()) + " AND PENSION: " + pensionService.discount(employee.getGrossSalary()));
        double sum = taxService.tax(employee.getGrossSalary()) + pensionService.discount(employee.getGrossSalary());
        System.out.println("SALARY DISCOUNT SUM : " + sum );
        return employee.getGrossSalary()  - taxService.tax(employee.getGrossSalary()) - pensionService.discount(employee.getGrossSalary());
    }
}
