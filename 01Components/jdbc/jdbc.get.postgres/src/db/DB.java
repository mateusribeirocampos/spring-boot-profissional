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
	
	private static Connection conn = null;
	
	// Connection method
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				// database connection - instantiate the object Connection
				conn = DriverManager.getConnection(url, props);
				System.out.println("PostgreSQL connection was successful");
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	// method to close the connection.
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// aux method to load properties from environment variables or file
	private static Properties loadProperties() {
		Properties props = new Properties();
		
		try {
			// Tenta carregar o arquivo db.properties (se existir)
			try (FileInputStream openFile = new FileInputStream("db.properties")) {
				props.load(openFile);
				System.out.println("db.properties file loaded!");
			}
		} catch (IOException e) {
			System.out.println("db.properties not found, using only environment variables");
		}
		
		// Sobrescreve com variáveis de ambiente (prioridade maior)
		String dbName = System.getenv("DATABASENAME");
		String dbUser = System.getenv("USERDATABASE");
		String dbPassword = System.getenv("DATABASEPASSWORD");
		
		// Monta a URL do banco
		if (dbName != null && !dbName.isEmpty()) {
			String dbUrl = "jdbc:postgresql://localhost:5432/" + dbName;
			props.setProperty("dburl", dbUrl);
		}
		
		if (dbUser != null && !dbUser.isEmpty()) {
			props.setProperty("user", dbUser);
		}
		
		if (dbPassword != null && !dbPassword.isEmpty()) {
			props.setProperty("password", dbPassword);
		}
		
		// Validação: garante que as propriedades essenciais estão presentes
		if (!props.containsKey("dburl") || !props.containsKey("user") || !props.containsKey("password")) {
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