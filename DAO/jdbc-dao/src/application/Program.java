package application;

//import java.time.format.DateTimeFormatter;
import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
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
		/*Department department1 = new Department(1, null);
		LocalDate birth_date = LocalDate.parse("02/05/1975", dtf);
		Seller sellerInsert = new Seller(null, "Elon Musk", "elon.musk@email.com", birth_date, 25000.00, department1);
		sellerDao.insert(sellerInsert);
		System.out.println(sellerInsert);*/
		
		System.out.println("\n ================= TEST5: seller update ================== ");
		/*Seller seller1 = sellerDao.findById(15);
		if (seller1 != null) {
			System.out.println(seller1);
			Department department1 = new Department(1, null);
			LocalDate birth_date = LocalDate.parse("30/05/1971", dtf);
			Seller sellerInsert = new Seller(15, "Elon Musk", "elon.musk@email.com", birth_date, 125000.00, department1);
			sellerDao.update(sellerInsert);
			System.out.println(sellerInsert);
			System.out.println("User was updated!");
		} else {
			throw new DbException("The user was not found in the database!");
		}*/
		
		System.out.println("\n ================= TEST6: seller delete ================== ");
		/*Seller seller2 = sellerDao.findById(15);
		if (seller2 != null) {
			System.out.println(seller2);
			sellerDao.deleteById(15);
			System.out.println("The user was deleted!");
		} else {
			throw new DbException("The user was not found in the database!");
		}*/
		
		System.out.println("\n ============== TEST 7:seller findByBaseSalaryGreatThan4000 ======================");
		List<Seller> listSellerBybaseSalary = sellerDao.findByBaseSalaryGreatThan4000(4000.00);
		for (Seller obj : listSellerBybaseSalary) {
			System.out.println(obj);
		}
		
		System.out.println("\n ============== TEST 1: Department findById ====================");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
		
		System.out.println("\n ============== TEST 2: Department findAll ====================");
		List<Department> dep1 = departmentDao.findAll();
		for (Department listDep : dep1) {
			System.out.println(listDep);
		}
		
		System.out.println("\n ============== TEST 3: Department insert ====================");
		/*Department dep2 = new Department(null, "Restaurant");
		departmentDao.insert(dep2);
		System.out.println(dep2);*/
		
		System.out.println("\n ============== TEST 4: Department update ====================");
		/*Department dep3 = departmentDao.findById(7);
		if (dep3 != null) {
			System.out.println(dep3);
			Department updateDep = new Department(7, "Health and Beaty");
			departmentDao.update(updateDep);
			System.out.println(updateDep + " was update!");
		} else {
			throw new DbException("Department not found");
		}*/
		
		System.out.println("\n ============== TEST 4: Department delete ====================");
		Department dep4 = departmentDao.findById(9);
		if (dep4 != null) {
			System.out.println(dep4);
			departmentDao.deleteById(9);
			System.out.println(dep4 + " was deleted!");
		} else {
			throw new DbException("Department not found");
		}
		
	}

}
