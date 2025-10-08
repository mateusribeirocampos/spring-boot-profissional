package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(" ============== TEST 1: seller findById ====================");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println("\n ============== TEST 2:seller findByDepartment ======================");
		Department department = new Department(4, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n ================= TEST3: seller findAll ================== ");
		List<Seller> listAll = sellerDao.findAll();
		for (Seller obj : listAll) {
			System.out.println(obj);
		}
		
		System.out.println("\n ================= TEST4: seller Insert ================== ");
		Department department1 = new Department(1, null);
		LocalDate birth_date = LocalDate.parse("02/05/1975", dtf);
		Seller sellerInsert = new Seller(null, "Elon Musk", "elon.musk@email.com", birth_date, 25000.00, department1);
		sellerDao.insert(sellerInsert);
		System.out.println(sellerInsert);
	}

}
