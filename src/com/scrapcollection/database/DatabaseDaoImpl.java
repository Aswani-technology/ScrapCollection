package com.scrapcollection.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

public class DatabaseDaoImpl implements DatabaseDao {
	
	
private Connection connection = null;
	
	static DatabaseDao baseInfo;
	public PreparedStatement statement;
 

	@Override
	public String getDriverName() {

				return DRIVER_NAME;
	}

	@Override
	public String getPassword() {

		String password =PASSWORD;
		return password;
	}

	@Override
	public String getURL() {

		return new StringBuilder().append("jdbc:mysql://").append("localhost")
				.append(":").append("3306").append("/")
				.append(DB_NAME).append("?user=").append(USERNAME)
				.append("&password=").append(PASSWORD).toString();
	}

	@Override
	public String getUserName() {

		String userName = USERNAME;

		return userName;
	}

	public Connection getConnection() {
					try {
		
			Class.forName(DRIVER_NAME);
			System.out.println("Connector loaded successfully");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(getURL());
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return connection;
	}

	public void closeDBResource() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
