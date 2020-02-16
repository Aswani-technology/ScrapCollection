package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scrapcollection.data.ComplaintDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Complaint;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.util.Constants;

public class ComplaintDaoImpl extends DatabaseDaoImpl implements ComplaintDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public int saveComplaint(Complaint complaint) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(complaint);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into Complaint values(?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, complaint.getId());

			statement.setInt(2, complaint.getRequest_id());
			statement.setString(3, complaint.getDescription());
			statement.setString(4,complaint.getDate());
		 
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
	public boolean updateComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update Complaint set " + getAllColumnName() + " where id=?;");

			statement.setString(1, complaint.getDescription());
			statement.setInt(2, complaint.getId());

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
	public Complaint findComplaintById(int id) {
		// TODO Auto-generated method stub
		Complaint complaint = new Complaint();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM Complaint  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				int request = resultset.getInt(2);
				ScrapCollectionRequest scrapRequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request);
				 
				String description = resultset.getString(3);
				 String date=resultset.getString(4);
				complaint = new Complaint(id, scrapRequest, description,date);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return complaint;
	}

	@Override
	public List<Complaint> findAllComplaint() {
		// TODO Auto-generated method stub
		List<Complaint> objectList = new ArrayList<Complaint>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM Complaint ");

			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id = resultset.getInt(1);
				int request = resultset.getInt(2);
				ScrapCollectionRequest scrapRequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request);
				 
				String description = resultset.getString(3);
				 String date=resultset.getString(4);
				Complaint complaint = new Complaint(id, scrapRequest, description,date);

				objectList.add(complaint);

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
	public boolean deleteComplaint(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM Complaint WHERE id = ?");
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
