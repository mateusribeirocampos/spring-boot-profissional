package application;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.List;

import db.DB;
//import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		try {
			runTests();
		} finally {
			DB.closeConnection();
			System.out.println("\n========== Connection closed ==========");
		}
	}
	
	private static void runTests() {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		// ==================== SELLER TESTS ====================
		
		System.out.println("============== TEST 1: seller findById ====================");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println("\n============== TEST 2: seller findByDepartment ====================");
		Department department = new Department(4, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n============== TEST 3: seller findAll ====================");
		List<Seller> listAll = sellerDao.findAll();
		for (Seller obj : listAll) {
			System.out.println(obj);
		}
		
		System.out.println("\n============== TEST 4: seller Insert ====================");
		/*Department department1 = new Department(1, null);
		LocalDate birth_date = LocalDate.parse("02/05/1975", dtf);
		Seller sellerInsert = new Seller(null, "Elon Musk", "elon.musk@email.com", birth_date, 25000.00, department1);
		sellerDao.insert(sellerInsert);
		System.out.println("Inserted: " + sellerInsert);*/
		
		System.out.println("\n============== TEST 5: seller update ====================");
		/*Seller seller1 = sellerDao.findById(16);
		if (seller1 != null) {
			System.out.println("Before update: " + seller1);
			Department department1 = new Department(1, null);
			LocalDate birth_date = LocalDate.parse("30/05/1971", dtf);
			Seller sellerUpdate = new Seller(16, "Elon Musk", "elon.musk@spacex.com", birth_date, 125000.00, department1);
			sellerDao.update(sellerUpdate);
			System.out.println("After update: " + sellerUpdate);
			System.out.println("User was updated successfully!");
		} else {
			throw new DbException("The user was not found in the database!");
		}*/
		
		System.out.println("\n============== TEST 6: seller delete ====================");
		/*Seller seller2 = sellerDao.findById(16);
		if (seller2 != null) {
			System.out.println("To be deleted: " + seller2);
			sellerDao.deleteById(16);
			System.out.println("User ID " + seller2.getId() + " was deleted successfully!");
		} else {
			System.out.println("User not found for deletion!");
		}*/
		
		System.out.println("\n============== TEST 7: seller findByBaseSalaryGreaterThan ====================");
		List<Seller> listSellerByBaseSalary = sellerDao.findByBaseSalaryGreaterThan(4000.00);
		System.out.println("Sellers with salary >= $4000.00:");
		for (Seller obj : listSellerByBaseSalary) {
			System.out.println(obj);
		}
		
		// ==================== DEPARTMENT TESTS ====================
		
		System.out.println("\n============== TEST 8: Department findById ====================");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
		
		System.out.println("\n============== TEST 9: Department findAll ====================");
		List<Department> depList = departmentDao.findAll();
		for (Department listDep : depList) {
			System.out.println(listDep);
		}
		
		System.out.println("\n============== TEST 10: Department insert ====================");
		/*Department dep2 = new Department(null, "Restaurant");
		departmentDao.insert(dep2);
		System.out.println("Inserted: " + dep2);*/
		
		System.out.println("\n============== TEST 11: Department update ====================");
		/*Department dep3 = departmentDao.findById(7);
		if (dep3 != null) {
			System.out.println("Before update: " + dep3);
			Department updateDep = new Department(7, "Health and Beauty");
			departmentDao.update(updateDep);
			System.out.println("After update: " + updateDep);
			System.out.println("Department was updated successfully!");
		} else {
			throw new DbException("Department not found!");
		}*/
		
		System.out.println("\n============== TEST 12: Department delete ====================");
		/*Department dep4 = departmentDao.findById(9);
		if (dep4 != null) {
			System.out.println("To be deleted: " + dep4);
			departmentDao.deleteById(9);
			System.out.println("Department ID " + dep4.getId() + " was deleted successfully!");
		} else {
			System.out.println("Department not found for deletion!");
		}*/
	}
}