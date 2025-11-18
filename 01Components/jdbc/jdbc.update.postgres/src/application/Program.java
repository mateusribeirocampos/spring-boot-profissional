package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import db.DB;	

public class Program {

	public static void main(String[] args) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET base_salary = base_salary + ? "
					+ "WHERE "
					+ "(Department_id = ?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setDouble(1, 200.0);
			st.setInt(2, 4);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Double base_salary = rs.getDouble(5);
					
					System.out.println("Done! id = " + id + ", name = " + name + ", base salary = " + base_salary);
				}
		
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
			}
	}
}
