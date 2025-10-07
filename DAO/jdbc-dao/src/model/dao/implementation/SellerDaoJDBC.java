package model.dao.implementation;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
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

}
