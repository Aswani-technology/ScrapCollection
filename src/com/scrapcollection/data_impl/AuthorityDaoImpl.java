package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 
import com.scrapcollection.data.AuthorityDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Authority;
import com.scrapcollection.util.Constants;

public class AuthorityDaoImpl extends DatabaseDaoImpl implements AuthorityDao{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveAuthority(Authority authority) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(authority);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into authority values(?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, authority.getId());
			statement.setString(2, authority.getName());
			statement.setString(3, authority.getUsername());
			statement.setString(4, authority.getPassword());
			statement.setString(5, authority.getEmail());
			statement.setString(6, authority.getCity());
			statement.setString(7, authority.getDistrict());
			statement.setLong(8, authority.getContact());
			 

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
		builder.append("name").append("=?,").append("username").append("=?,").append("password").append("=?,").append("email").append("=?,")
		.append("city").append("=?,") 
		
		.append("district").append("=?,").append("contact").append("=?");
		return builder.toString();
	}   
	
	@Override
	public boolean updateAuthority(Authority Authority) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update authority set " + getAllColumnName() + " where id=?;");
 
			statement.setString(1, Authority.getName()); 
			statement.setString(2, Authority.getUsername());
			statement.setString(3, Authority.getPassword());
			statement.setString(4, Authority.getEmail());
			statement.setString(5, Authority.getCity());
			statement.setString(6, Authority.getDistrict());
			statement.setLong(7, Authority.getContact());
			statement.setInt(8, Authority.getId());
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
	public Authority findAuthorityById(int id) {
		// TODO Auto-generated method stub
		Authority Authority = new Authority();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM authority  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				String name=resultset.getString(2);
				String username = resultset.getString(3);
				String password=resultset.getString(4);
				String email=resultset.getString(5);
				String city=resultset.getString(6);
				String district=resultset.getString(7);
				Long contact=resultset.getLong(8);
				 
				Authority=new Authority(id,name, username, password, email, city, district, contact);
				

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return Authority;
	}

	@Override
	public List<Authority> findAllAuthority() {
		// TODO Auto-generated method stub
		List<Authority> objectList = new ArrayList<Authority>();

	

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM authority ");
			 
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
				Authority authority=new Authority();
				  int id = resultset.getInt(1);
				  String name=resultset.getString(2);
					String username = resultset.getString(3);
					String password=resultset.getString(4);
					String email=resultset.getString(5);
					String city=resultset.getString(6);
					String district=resultset.getString(7);
					Long contact=resultset.getLong(8);
					 
					authority=new Authority(id,name, username, password, email, city, district, contact);
				objectList.add(authority);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
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
	public boolean deleteAuthority(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM authority WHERE id = ?");
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
	public Authority Login(String username, String password) {
		// TODO Auto-generated method stub
		Authority authority = new Authority();
		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM authority WHERE username=? and password=?;");
			statement.setString(1, username);
			statement.setString(2, password);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id=resultset.getInt(1);
			 String name=resultset.getString(2);
			   String email = resultset.getString(5);
				String city = resultset.getString(6);
				String district = resultset.getString(7);

				Long contact = resultset.getLong(8);
		 
				authority = new Authority(id, name, username, password, email, city, district, contact);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully  :: "+authority);
		return authority;
	}

	@Override
	public Authority findAuthorityByDistrict(String district) {
		// TODO Auto-generated method stub
		Authority authority = new Authority();
		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM authority WHERE district=?;");
			statement.setString(1, district);
		 
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id=resultset.getInt(1);
			 String name=resultset.getString(2);
			   String email = resultset.getString(5);
				String city = resultset.getString(6);
				//String district = resultset.getString(7);
				
				String username=resultset.getString(3);
				String password=resultset.getString(4);

				Long contact = resultset.getLong(8);
		 
				authority = new Authority(id, name, username, password, email, city, district, contact);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully  :: "+authority);
		return authority;
	}
}
