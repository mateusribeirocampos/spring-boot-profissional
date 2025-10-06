package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import db.DB;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT \r\n"
					+ "    s.id, "
					+ "    s.name, "
					+ "    s.email, "
					+ "    s.birth_date, "
					+ "    s.base_salary, "
					+ "    s.department_id, "
					+ "    d.name AS department_name "
					+ "FROM seller s "
					+ "JOIN department d "
					+ "    ON s.department_id = d.id "
					+ "ORDER BY s.id;");
			
			System.out.println();

			while (rs.next()) {
				System.out.println(rs.getInt("id") 
						+ ", " + rs.getString("name")
						+ ", " + rs.getString("email")
						+ ", " + rs.getDate("birth_date")
						+ ", " + rs.getDouble("base_salary")
						+ ", " + rs.getInt("department_id"));

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
	}
	
}
