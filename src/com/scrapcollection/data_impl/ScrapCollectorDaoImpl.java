package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 

import com.scrapcollection.data.ScrapCollectorDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.util.Constants;
import com.scrapcollection.util.DateToLocalDateConverter;
import com.scrapcollection.util.LocalDateToSqlDateConverter;

public class ScrapCollectorDaoImpl extends DatabaseDaoImpl  implements ScrapCollectorDao {

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveScrapCollector(ScrapCollector scrapCollector) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(scrapCollector);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into scrap_collector values(?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, scrapCollector.getId());
			statement.setString(2, scrapCollector.getFirst_name());
			statement.setString(3, scrapCollector.getLast_name());
			statement.setString(4, scrapCollector.getGender());
			
			/*java.sql.Date sqlDate = LocalDateToSqlDateConverter.convert(scrapCollector.getDob());
			statement.setDate(5, sqlDate);*/
			statement.setString(5, scrapCollector.getEmail());
			statement.setString(6, scrapCollector.getPassword());
			statement.setString(7, scrapCollector.getDob());
			statement.setString(8, scrapCollector.getAddress());
			statement.setLong(9, scrapCollector.getContact());
			statement.setString(10, scrapCollector.getCity());
			statement.setInt(11, scrapCollector.getAuthority_id().getId());
			statement.setInt(12, scrapCollector.getAvailability_status());

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
		builder.append("first_name").append("=?,").append("last_name").append("=?,")
		.append("address").append("=?,") 
		.append("email").append("=?,")
		.append("password").append("=?,")
		.append("contact").append("=?,").append("city").append("=?");
		return builder.toString();
	}   
	
	@Override
	public boolean updateScrapCollector(ScrapCollector scrapCollector) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update scrap_collector set " + getAllColumnName() + " where id=?;");
 
			statement.setString(1, scrapCollector.getFirst_name());
			statement.setString(2, scrapCollector.getLast_name());
			statement.setString(3, scrapCollector.getAddress());
			statement.setString(4, scrapCollector.getEmail());
			statement.setString(5, scrapCollector.getPassword());
			statement.setLong(6, scrapCollector.getContact());
			statement.setString(7, scrapCollector.getCity());
			statement.setInt(8, scrapCollector.getId());
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
	public ScrapCollector findScrapCollectorById(int id) {
		// TODO Auto-generated method stub
		ScrapCollector scrapCollector = new ScrapCollector();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name=resultset.getString(3);
				String gender=resultset.getString(4);
				//Date dob = resultset.getDate(5);
				//LocalDate locadDate=DateToLocalDateConverter.convert(dob);
				String email=resultset.getString(5);
				String password=resultset.getString(6);
				String dob=resultset.getString(7);
				String addres=resultset.getString(8);
				Long contact=resultset.getLong(9);
				String city=resultset.getString(10);
				int authority_id=resultset.getInt(11);
				AuthorityService auth_service=new AuthorityServiceImpl();
				Authority authority=auth_service.findAuthorityById(authority_id);
				 
				int status=resultset.getInt(12);
				scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority, status);
				

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");
		return scrapCollector;
	}

	@Override
	public List<ScrapCollector> findAllScrapCollector() {
		// TODO Auto-generated method stub
		List<ScrapCollector> objectList = new ArrayList<ScrapCollector>();

		ScrapCollector scrapCollector = new ScrapCollector();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector");
			 
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				  int id = resultset.getInt(1);
				  String first_name = resultset.getString(2);
					String last_name=resultset.getString(3);
					String gender=resultset.getString(4);
					//Date dob = resultset.getDate(5);
					//LocalDate locadDate=DateToLocalDateConverter.convert(dob);
					String email=resultset.getString(5);
					String password=resultset.getString(6);
					String dob=resultset.getString(7);
					String addres=resultset.getString(8);
					Long contact=resultset.getLong(9);
					String city=resultset.getString(10);
					int authority_id=resultset.getInt(11);
					Authority authority=new Authority();
					authority.setId(authority_id);
					int status=resultset.getInt(12);
					scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority, status);
					objectList.add(scrapCollector);
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
	public boolean deleteScrapCollector(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM scrap_collector WHERE id = ?");
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
	public boolean updateTaskCompletionStatus(int status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScrapCollector login(String email, String password) {
		// TODO Auto-generated method stub
		ScrapCollector scrapCollector = new ScrapCollector();
		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector WHERE email=? and password=?;");
			statement.setString(1, email);
			statement.setString(2, password);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id=resultset.getInt(1);
				 String first_name = resultset.getString(2);
					String last_name=resultset.getString(3);
					String gender=resultset.getString(4);
					//Date dob = resultset.getDate(5);
					//LocalDate locadDate=DateToLocalDateConverter.convert(dob);


					String dob=resultset.getString(7);
					String addres=resultset.getString(8);
					Long contact=resultset.getLong(9);
					String city=resultset.getString(10);
					int authority_id=resultset.getInt(11);
					Authority authority=new Authority();
					authority.setId(authority_id);
					int status=resultset.getInt(12);
					scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority, status);
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully  :: "+scrapCollector);
		return scrapCollector;
	}

	@Override
	public List<ScrapCollector> findAllScrapCollector(int status) {
		// TODO Auto-generated method stub
		List<ScrapCollector> objectList = new ArrayList<ScrapCollector>();

		ScrapCollector scrapCollector = new ScrapCollector();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector where availability=?");
			statement.setInt(1, 0);
			// availability 0 -> available
			// 1 -> not available
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				  int id = resultset.getInt(1);
				  String first_name = resultset.getString(2);
					String last_name=resultset.getString(3);
					String gender=resultset.getString(4);
					//Date dob = resultset.getDate(5);
					//LocalDate locadDate=DateToLocalDateConverter.convert(dob);
					String email=resultset.getString(5);
					String password=resultset.getString(6);
					String dob=resultset.getString(7);
					String addres=resultset.getString(8);
					Long contact=resultset.getLong(9);
					String city=resultset.getString(10);
					int authority_id=resultset.getInt(11);
					Authority authority=new Authority();
					authority.setId(authority_id);
					//int status=resultset.getInt(12);
					scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority, status);
					objectList.add(scrapCollector);
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
	public List<ScrapCollector> findAllScrapCollectorByAuthority(int authority) {
		// TODO Auto-generated method stub
		List<ScrapCollector> objectList = new ArrayList<ScrapCollector>();

	

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector where authority_id=?");
			statement.setInt(1, authority);
			// availability 0 -> available
			// 1 -> not available
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				ScrapCollector scrapCollector = new ScrapCollector();
				  int id = resultset.getInt(1);
				  String first_name = resultset.getString(2);
					String last_name=resultset.getString(3);
					String gender=resultset.getString(4);
					//Date dob = resultset.getDate(5);
					//LocalDate locadDate=DateToLocalDateConverter.convert(dob);
					String email=resultset.getString(5);
					String password=resultset.getString(6);
					String dob=resultset.getString(7);
					String addres=resultset.getString(8);
					Long contact=resultset.getLong(9);
					String city=resultset.getString(10);
					int authority_id=resultset.getInt(11);
					 Authority authority_obj=new Authority();
					 authority_obj.setId(authority_id);
					int status=resultset.getInt(12);
					scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority_obj, status);
					objectList.add(scrapCollector);
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
	public List<ScrapCollector> findAllScrapCollectorByAuthorityandStatus(int authority) {
		// TODO Auto-generated method stub
		List<ScrapCollector> objectList = new ArrayList<ScrapCollector>();

		

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collector where authority_id=? and availability=0");
			statement.setInt(1, authority);
			// availability 0 -> available
			// 1 -> not available
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				ScrapCollector scrapCollector = new ScrapCollector();
				  int id = resultset.getInt(1);
				  String first_name = resultset.getString(2);
					String last_name=resultset.getString(3);
					String gender=resultset.getString(4);
					//Date dob = resultset.getDate(5);
					//LocalDate locadDate=DateToLocalDateConverter.convert(dob);
					String email=resultset.getString(5);
					String password=resultset.getString(6);
					String dob=resultset.getString(7);
					String addres=resultset.getString(8);
					Long contact=resultset.getLong(9);
					String city=resultset.getString(10);
					int authority_id=resultset.getInt(11);
					 Authority authority_obj=new Authority();
					 authority_obj.setId(authority_id);
					int status=resultset.getInt(12);
					scrapCollector=new ScrapCollector(id, first_name, last_name, gender,email, password,dob, addres, contact,city, authority_obj, status);
					objectList.add(scrapCollector);
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
	public boolean updateScrapCollectorStatus(int id) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		connection = getConnection();
		try {
			statement = connection
					.prepareStatement("update scrap_collector set availability=1 where id=?;");
			statement.setInt(1, id);
			
			int check_id = statement.executeUpdate();

			is_update = (check_id > 0);
			if (is_update) {

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			} else {
				Constants.Response.MSG = Constants.Response.MSG_FAILED;
				System.out.println("Updation Failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return is_update;
	}
}
