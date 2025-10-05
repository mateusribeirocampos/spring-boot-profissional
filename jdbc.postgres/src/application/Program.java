package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		// connect database
		Connection conn = null;
		// prepare a sql consult 
		Statement st = null;
		// results of consult will saved in the rs
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			// method receive a string that is a sql
			rs = st.executeQuery("select * from department");
			
			System.out.println();
			// return false while is finish
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		DB.closeConnection();
	}

}
