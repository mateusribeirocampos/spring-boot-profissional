package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn =null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				System.out.println("PostgreSQL connection was successful");
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	public static Properties loadProperties() {
		Properties props = new Properties();
		
		try {
			try (FileInputStream openFile = new FileInputStream("db.properties")) {
				props.load(openFile);
				System.out.println("db.properties file load!");
			}
		} catch (IOException e) {
			System.out.println("db.properties not found, using only environment variables");
		}
		
		String dbName = System.getenv("DBNAME");
		String dbUser = System.getenv("DBUSER");
		String dbpswd = System.getenv("DBPSWD");
		
		if (dbName != null && !dbName.isEmpty()) {
			String dbUrl = "jdbc:postgresql://localhost:5432/" + dbName;
			props.setProperty("dburl", dbUrl);
		}
		
		if (dbUser != null && !dbUser.isEmpty()) {
			props.setProperty("user", dbUser);
		}
		
		if (dbpswd != null && !dbpswd.isEmpty()) {
			props.setProperty("password", dbpswd);
		}
		
		if (!props.containsKey("dburl") || props.containsKey("user") || props.containsKey("password")) {
			throw new DbException("Database configuration is incomplete. Check environment variables or db.properties");
		}
		
		return props;
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
