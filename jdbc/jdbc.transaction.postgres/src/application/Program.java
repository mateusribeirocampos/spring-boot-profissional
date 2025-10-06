package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET base_salary = 2900 WHERE department_id = 1");
			
			//int x = 1;
			//if (x < 2) {
			//	throw new SQLException("FAKE ERROR");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET base_salary = 3090 WHERE department_id = 2");
			
			conn.commit();
			
			System.out.println("rows 1 = " + rows1);
			System.out.println("rows 2 = " + rows2);
			
		}
			catch (SQLException e) {
				try {
					conn.rollback();
					throw new DbException("Transaction rolled baack! Caused by: " + e.getMessage());
				} catch (SQLException e1) {
					throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
				}
			}
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
			}
	}
}
