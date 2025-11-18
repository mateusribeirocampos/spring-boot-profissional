package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM seller "
					+ "WHERE "
					+ "id = ?",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, 12);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					System.out.println("Done! id = " + id + ", name = " + name);
				}
			}
		}
			catch (SQLException e) {
				throw new DbIntegrityException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
			}
	}
}
