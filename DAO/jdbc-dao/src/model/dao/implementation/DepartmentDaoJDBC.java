package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn  = conn;
	}

	@Override
	public void insert(Department obj) {
	
		try {
			st = conn.prepareStatement("INSERT INTO department (name) VALUES (?)", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
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
	public void update(Department obj) {

		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET name = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate();
				
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void deleteById(Integer id) {

		try {
			st = conn.prepareStatement("DELETE FROM department "
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
	public Department findById(Integer id) {
		
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE id = ?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
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

	@Override
	public List<Department> findAll() {
		
		try {
			st = conn.prepareStatement("SELECT * FROM department", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			rs = st.executeQuery();
		
			List<Department> list = new ArrayList<Department>();
			
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));
		return dep;
	}
}
