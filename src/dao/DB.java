package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private String className = "com.mysql.cj.jdbc.Driver";
	private String user = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost/geslocationvoiture";
	private Connection connection;

	public Connection getConnection() {
		try {
			Class.forName(className);
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
