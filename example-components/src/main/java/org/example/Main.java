package org.example;

import org.example.model.Employee;
import org.example.services.BrazilTaxService;
import org.example.services.PensionService;
import org.example.services.SalaryService;
import org.example.services.TaxService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Salário bruto: ");
        double grossSalary= sc.nextDouble();

        Employee employee = new Employee(name, grossSalary);

        //TaxService taxService = new TaxService(); // manually instantiated
        TaxService taxService = new BrazilTaxService(); // Upcasting
        PensionService pensionService = new PensionService();

        SalaryService salaryService = new SalaryService(taxService, pensionService);
        double netSalary = salaryService.netSalary(employee);

        System.out.println("Nome do funcionário: " + employee.getName());
        System.out.printf("Salário líquido = %.2f%n", netSalary);

    sc.close();
    }
}