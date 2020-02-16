package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scrapcollection.data.UserDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.User;
import com.scrapcollection.util.Constants;

public class UserDaoImpl extends DatabaseDaoImpl implements UserDao {
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(user);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getFirst_name());
			statement.setString(3, user.getLast_name());
			statement.setString(4, user.getGender());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getAddress());
			statement.setLong(8, user.getContact());
			statement.setString(9, user.getPlace());
			statement.setString(10, user.getHouse_no());
			statement.setInt(11, user.getWard_no());
			statement.setString(12, user.getPanchayath());
			statement.setString(13, user.getDistrict());

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {

				System.out.println("Insertion Done Successfully");
				
				key = generatedKeys.getInt(1);
				System.out.println("Registration id = "+key);
			} else {

				System.out.println("Registration id = not found ");
			}

		} catch (SQLException e) {

			System.out.println("Error occured in insertion");
			e.printStackTrace();
		}
		closeDBResource();
		return key;
	}

	private String getAllColumnName() {
		StringBuilder builder = new StringBuilder();
		// .append(COLUMN_id).append("=?,").
		builder.append("email").append("=?,").append("password").append("=?,").append("address").append("=?,").append("contact").append("=?,")
		.append("place").append("=?,").append("house_no").append("=?,").append("ward_no").append("=?,")
		
		.append("panchayath").append("=?,").append("district").append("=?");
		return builder.toString();
	}   
	
	@Override
	public boolean updateUser(User user) {
		
		System.out.println("user recieved is "+user);
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update user set " + getAllColumnName() + " where id=?;");
 
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			
			statement.setString(3, user.getAddress());
			statement.setLong(4, user.getContact());
			statement.setString(5, user.getPlace());
			statement.setString(6, user.getHouse_no());
			statement.setInt(7, user.getWard_no());
			statement.setString(8, user.getPanchayath());
			statement.setString(9, user.getDistrict());
			statement.setInt(10, user.getId());
			int check_id = statement.executeUpdate();

			is_update = (check_id > 0);
			
			
			if (is_update) {

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			} else {
				Constants.Response.MSG = Constants.Response.MSG_FAILED;
				System.out.println("Updation Failed");
			}

		} catch (SQLException e) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in insertion");
			e.printStackTrace();
		}

		closeDBResource();
		return is_update;
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		User user = new User();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM user  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name=resultset.getString(3);
				String gender=resultset.getString(4);
				String email=resultset.getString(5);
				String password=resultset.getString(6);
				String address=resultset.getString(7);
				Long contact=resultset.getLong(8);
				String place=resultset.getString(9);
				String house_no=resultset.getString(10);
				int ward_no=resultset.getInt(11);
				String panchayath=resultset.getString(12);
				String district=resultset.getString(13);
				user=new User(id, first_name, last_name, gender, email, password, address, contact, place, house_no, ward_no, panchayath, district);
				

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return user;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> objectList = new ArrayList<User>();

	

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM user ;");
			 
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				User user = new User();
				  int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name=resultset.getString(3);
				String gender=resultset.getString(4);
				String email=resultset.getString(5);
				String password=resultset.getString(6);
				String address=resultset.getString(7);
				Long contact=resultset.getLong(8);
				String place=resultset.getString(9);
				String house_no=resultset.getString(10);
				int ward_no=resultset.getInt(11);
				String panchayath=resultset.getString(12);
				String district=resultset.getString(13);
				user=new User(id, first_name, last_name, gender, email, password, address, contact, place, house_no, ward_no, panchayath, district);
				

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
				objectList.add(user);
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}
		closeDBResource();
		System.out.println("Find all data  Successfully");

		return objectList;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
			statement.setInt(1, id);
			int check_id = statement.executeUpdate();
			isDelete = check_id > 0;
			if (isDelete) {
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			} else {
				Constants.Response.MSG = Constants.Response.MSG_FAILED;
			}
			System.out.println("Deletion completed Successfully");
		} catch (SQLException e) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in deletion");
			e.printStackTrace();

		}

		closeDBResource();
		return isDelete;
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		User user = new User();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM user  WHERE email=? AND password=?;");
			statement.setString(1, email);
			statement.setString(2, password);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				  int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name=resultset.getString(3);
				String gender=resultset.getString(4);
				 
				String address=resultset.getString(7);
				Long contact=resultset.getLong(8);
				String place=resultset.getString(9);
				String house_no=resultset.getString(10);
				int ward_no=resultset.getInt(11);
				String panchayath=resultset.getString(12);
				String district=resultset.getString(13);
				user=new User(id, first_name, last_name, gender, email, password, address, contact, place, house_no, ward_no, panchayath, district);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return user;
				
	}

}
