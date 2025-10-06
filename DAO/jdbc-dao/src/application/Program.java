package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Department dep = new Department(1, "Books");
		
		System.out.println(dep.toString());
		
		LocalDate birth_date = LocalDate.parse("22/04/1985", dtf);
		
		Seller sel = new Seller(1, "Maria Brown", "maria.brown@email.com", birth_date, 3000.00);
		System.out.println(sel.toString());

	}

}
