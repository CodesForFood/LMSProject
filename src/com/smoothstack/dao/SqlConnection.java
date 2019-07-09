package com.smoothstack.dao;

import java.sql.*;

public class SqlConnection {
	
	
	private static SqlConnection sqlConnection;
	private Connection connection;
	
	
	private SqlConnection() {
		getConnection();
	}
	
	public static SqlConnection getInstance() {
		return sqlConnection == null ? sqlConnection = new SqlConnection() : sqlConnection; 
	}	
	
	public Connection getConnection() {
		try {
			connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}						

}
