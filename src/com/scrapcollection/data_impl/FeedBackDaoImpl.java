package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scrapcollection.data.FeedBackDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.FeedBack;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.User;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;

public class FeedBackDaoImpl extends DatabaseDaoImpl implements FeedBackDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public int saveFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(feedBack);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into FeedBack values(?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, feedBack.getId());

			statement.setInt(2, feedBack.getUser().getId());
			statement.setString(3, feedBack.getDescription());

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {

				System.out.println("Insertion Done Successfully");

				key = generatedKeys.getInt(1);
				System.out.println("Registration id = " + key);
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
		builder.append("description").append("=?,");
		return builder.toString();
	}

	@Override
	public boolean updateFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update FeedBack set " + getAllColumnName() + " where id=?;");

			statement.setString(1, feedBack.getDescription());
			statement.setInt(2, feedBack.getId());

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
	public FeedBack findFeedBackById(int id) {
		// TODO Auto-generated method stub
		FeedBack feedBack = new FeedBack();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM FeedBack  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);

				User user = new UserServiceImpl().findUserById(resultset.getInt(2));
				 
				String description = resultset.getString(3);
				feedBack = new FeedBack(id, user, description);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return feedBack;
	}

	@Override
	public List<FeedBack> findAllFeedBack() {
		// TODO Auto-generated method stub
		List<FeedBack> objectList = new ArrayList<FeedBack>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM FeedBack ");

			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				FeedBack feedBack = new FeedBack();
				int id = resultset.getInt(1);

				User user =new UserServiceImpl().findUserById(resultset.getInt(2));
				String description = resultset.getString(3);
				feedBack = new FeedBack(id, user, description);

				objectList.add(feedBack);

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
	public boolean deleteFeedBack(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM feedback WHERE id = ?");
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
	public List<FeedBack> findAllFeedBacks(int user_id) {
		// TODO Auto-generated method stub
		List<FeedBack> objectList = new ArrayList<FeedBack>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM FeedBack WHERE user=? ");
			statement.setInt(1, user_id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				FeedBack feedBack = new FeedBack();
				int id = resultset.getInt(1);

				User user =new UserServiceImpl().findUserById(resultset.getInt(2));
				 
				String description = resultset.getString(3);
				feedBack = new FeedBack(id, user, description);

				objectList.add(feedBack);

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
}
