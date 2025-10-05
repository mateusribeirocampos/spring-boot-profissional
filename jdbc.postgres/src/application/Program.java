package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import db.DB;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			/*
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(name, email, birth_date, base_salary, department_id) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Carl Purple");
			st.setString(2, "carl.purple@email.com");
			
			// Parse da data usando LocalDate
			st.setDate(3, Date.valueOf(LocalDate.parse("22/04/1985", formatter)));
			
			// st.setDate(3, new Date(sdf.parse("22/04/1985").getTime()));
			
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			*/
			
			st = conn.prepareStatement(
					"INSERT INTO department (name) values ('Toys'), ('Sports')", 
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					System.out.println("Done! id = " + id + ", name = " + name);
				}
					
			} else {
				System.out.println("No row affected");
			}
			
			//System.out.println("Done! Rows affected: " + rowsAffected);
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
