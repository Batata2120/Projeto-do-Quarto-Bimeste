package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {
	public Connection getConnection() {
		try {
			return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cidadedb?useSSL=false", "mateus", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}