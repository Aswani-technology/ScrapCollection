package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.scrapcollection.data.ScrapTypeDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.util.Constants;

public class ScrapTypeDaoImpl extends DatabaseDaoImpl implements ScrapTypeDao {

	Connection connection = null;
	PreparedStatement statement = null;
	@Override
	public int saveScrapType(ScrapType scrapType) {
		// TODO Auto-generated method stub
	

	 
			// TODO Auto-generated method stub

			int key = 0;
			System.out.println(scrapType);
			try {
				connection = getConnection();
				System.out.println("======================user=======================");

				statement = connection.prepareStatement("insert into scrap_type(name) values(?);",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, scrapType.getName());

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
		builder.append("name").append("=?");
		return builder.toString();
	}   
	@Override
	public boolean updateScrapType(ScrapType scrapType) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update scrap_type set " + getAllColumnName() + " where id=?;");
 
			statement.setString(1, scrapType.getName());
			statement.setInt(2, scrapType.getId());

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
	public ScrapType findScrapTypeById(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ScrapType scrapType = new ScrapType();

				ResultSet resultset = null;
				try {
					connection = getConnection();
					statement = connection.prepareStatement("SELECT * FROM scrap_type  WHERE id=?;");
					statement.setInt(1, id);
					resultset = statement.executeQuery();
					while (resultset != null && resultset.next()) {
						// int id = resultset.getInt(1);
						String name = resultset.getString(2);

						scrapType = new ScrapType(id, name);

						Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
					}
				} catch (SQLException se) {
					Constants.Response.MSG = Constants.Response.MSG_FAILED;
					System.out.println("Error occured in data finding");
					se.printStackTrace();
				}

				closeDBResource();
				System.out.println("Find all data  Successfully");
				return scrapType;
	}

	@Override
	public List<ScrapType> findAllScrapType() {
		// TODO Auto-generated method stub
		List<ScrapType> objectList = new ArrayList<ScrapType>();

		ScrapType scrapType = new ScrapType();
		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_type;");
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id = resultset.getInt(1);
				String name = resultset.getString(2);

				scrapType = new ScrapType(id, name);

				objectList.add(scrapType);
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
	public boolean deleteScrapType(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM scrap_type WHERE id = ?");
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
	}


