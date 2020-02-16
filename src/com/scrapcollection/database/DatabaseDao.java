package com.scrapcollection.database;

public interface DatabaseDao {
	
	final String DB_NAME="scrap_collection";
	final String USERNAME="root";
	final String PASSWORD="sa";
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public String getDriverName();
	public String getPassword();
	public String getURL();
	public String getUserName();

}
