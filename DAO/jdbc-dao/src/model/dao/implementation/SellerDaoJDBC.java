package model.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn = null;
	
	// dependency
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO seller (name, email, birth_date, base_salary, department_id) \r\n"
					+ "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, Date.valueOf(obj.getBirth_date()));
			st.setDouble(4, obj.getBase_salary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					obj.setId(id);
					obj.setName(name);
				} else {
					throw new DbException("Unexpected error! No rows affected!");
				}
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
		
	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE seller "
					+ "SET name = ?, email = ?, birth_date = ?, base_salary = ?, department_id = ? "
					+ "WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, Date.valueOf(obj.getBirth_date()));
			st.setDouble(4, obj.getBase_salary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM seller "
					+ "WHERE id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n"
					+ "ON seller.department_id = department_id\r\n"
					+ "WHERE seller.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setBirth_date(rs.getDate("birth_date").toLocalDate());
		obj.setBase_salary(rs.getDouble("base_salary"));
		obj.setDepartment(dep);
		
		return obj;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("department_id"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		
		st = conn.prepareStatement(
				"SELECT seller.*, department.name as DepName\r\n"
				+ "FROM seller INNER JOIN department\r\n"
				+ "ON seller.department_id = department.id\r\n"
				+ "ORDER BY seller.base_salary DESC"
				);
		
		rs = st.executeQuery();
		
		List<Seller> list = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<>(); // map created
	
		while (rs.next()) {
			
			Department dep = map.get(rs.getInt("department_id")); // will save any department in this dep
			
			if (dep == null) {
				dep = instantiateDepartment(rs);
				map.put(rs.getInt("department_id"), dep);
			}
			
			Seller obj = instantiateSeller(rs, dep);
			list.add(obj);
		}
		return list;
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*, "
					+ "department.name AS DepName "
					+ "FROM seller "
					+ "INNER JOIN department "
					+ "ON seller.department_id = department.id "
					+ "WHERE seller.department_id = ? "
					+ "ORDER BY seller.name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>(); // map created
		
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("department_id")); // will save any department in this dep
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("department_id"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public List<Seller> findByBaseSalaryGreatThan4000(double base_salary) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT s.*, d.name AS depName\r\n"
					+ "FROM seller s INNER JOIN department d\r\n"
					+ "ON s.department_id = d.id\r\n"
					+ "WHERE s.base_salary >= ?\r\n"
					+ "ORDER BY s.base_salary DESC");
			
			st.setDouble(1, base_salary);
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("department_id"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("department_id"), dep);
				}
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
