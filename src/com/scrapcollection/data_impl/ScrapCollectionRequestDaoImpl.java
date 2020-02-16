package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.scrapcollection.data.AuthorityDao;
import com.scrapcollection.data.ScrapCollectionRequestDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequestList;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.domain.User;

import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;
import com.scrapcollection.util.DateToLocalDateConverter;
import com.scrapcollection.util.LocalDateToSqlDateConverter;

public class ScrapCollectionRequestDaoImpl extends DatabaseDaoImpl implements ScrapCollectionRequestDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public int saveScrapCollectionRequest(ScrapCollectionRequest scrapCollectionRequest) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(scrapCollectionRequest);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into scrap_collection_request values(?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, scrapCollectionRequest.getId());
			statement.setInt(2, scrapCollectionRequest.getUser().getId());

			statement.setString(3, scrapCollectionRequest.getDate());

			//

			AuthorityDao dao = new AuthorityDaoImpl();
			Authority authority = dao.findAuthorityByDistrict(scrapCollectionRequest.getUser().getDistrict());

			statement.setInt(4, authority.getId());
			statement.setInt(5, scrapCollectionRequest.getStatus());

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {

				System.out.println("Insertion Done Successfully");

				key = generatedKeys.getInt(1);
				System.out.println("Registration id = " + key);
			} else {

				System.out.println("Registration id = not found ");
			}

			List<ScrapRequestList> sc_req_list = scrapCollectionRequest.getScrapRequestList();

			for (ScrapRequestList obj : sc_req_list) {
				statement = connection.prepareStatement("insert into scraprequest_scraptype values(?,?,?);");
				// statement.setInt(1, scrapCollectionRequest.getId());
				statement.setInt(1, key);
				statement.setInt(2, obj.getScraptype().getId());
				statement.setFloat(3, obj.getWeight());

				statement.executeUpdate();

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
		builder.append("status").append("=?");
		return builder.toString();
	}

	@Override
	public boolean updateScrapCollectionRequest(ScrapCollectionRequest ScrapCollectionRequest) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection
					.prepareStatement("update scrap_collection_request set " + getAllColumnName() + " where id=?;");

			statement.setInt(1, ScrapCollectionRequest.getStatus());
			statement.setInt(2, ScrapCollectionRequest.getId());
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
	public ScrapCollectionRequest findScrapCollectionRequestById(int id) {
		// TODO Auto-generated method stub
		ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequest();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collection_request  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				int user_id = resultset.getInt(2);
				User user = new UserServiceImpl().findUserById(user_id);

				String date = resultset.getString(3);

				// int authority_id=resultset.getInt(4);
				// Authority authority=new
				// AuthorityServiceImpl().findAuthorityById(authority_id);

				int status = resultset.getInt(5);

				scrapCollectionRequest = new ScrapCollectionRequest(id, user, date, status, null);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;

			//	System.out.println("scrap request is " + scrapCollectionRequest);
				connection = getConnection();

				 

					 

				}

				 

			 
		} catch (Exception se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();

		System.out.println("Find all data  Successfully");
		return scrapCollectionRequest;
	}

	/*
	 * @Override public ScrapCollectionRequest
	 * findScrapCollectionRequestById(int id) { // TODO Auto-generated method
	 * stub ScrapCollectionRequest scrapCollectionRequest = new
	 * ScrapCollectionRequest();
	 * 
	 * ResultSet resultset = null; try { connection = getConnection(); statement
	 * = connection.
	 * prepareStatement(" SELECT * FROM scrap_collection_request request, scraprequest_scraptype request_type WHERE request.id=request_type.request and request.id=?;"
	 * );
	 * 
	 * statement.setInt(1, id); resultset = statement.executeQuery(); while
	 * (resultset != null && resultset.next()) { // int id =
	 * resultset.getInt(1); int user_id = resultset.getInt(2); User user=new
	 * User(); user.setId(user_id); String date = resultset.getString(3);
	 * 
	 * 
	 * int authority_id=resultset.getInt(4); Authority authority=new
	 * Authority(); authority.setId(authority_id); int
	 * status=resultset.getInt(5); int request=resultset.getInt(6); int
	 * scraptype=resultset.getInt(7); float weight=resultset.getFloat(8);
	 * 
	 * List<ScrapRequestList> scrapRequestList=new ArrayList<>();
	 * ScrapCollectionRequest collectionrequest=new
	 * ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(
	 * request); ScrapType scraptypeobject=new
	 * ScrapTypeServiceImpl().findScrapTypeById(scraptype); ScrapRequestList
	 * object=new ScrapRequestList(scraptypeobject, weight);
	 * 
	 * scrapCollectionRequest=new ScrapCollectionRequest(id, user, date,
	 * status,null);
	 * 
	 * 
	 * Constants.Response.MSG = Constants.Response.MSG_SUCCESS; } } catch
	 * (SQLException se) { Constants.Response.MSG =
	 * Constants.Response.MSG_FAILED;
	 * System.out.println("Error occured in data finding");
	 * se.printStackTrace(); }
	 * 
	 * closeDBResource(); System.out.println("Find all data  Successfully");
	 * return scrapCollectionRequest; }
	 */

	

	@Override
	public boolean deleteScrapCollectionRequest(int id) {
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
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthority(int authority_id) {
		// TODO Auto-generated method stub
		List<ScrapCollectionRequest> objectList = new ArrayList<ScrapCollectionRequest>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collection_request WHERE authority=?");
			statement.setInt(1, authority_id);
			
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				ScrapCollectionRequest ScrapCollectionRequest = new ScrapCollectionRequest();
				int id = resultset.getInt(1);
				int user_id = resultset.getInt(2);
				User user = new UserServiceImpl().findUserById(user_id);

				String date = resultset.getString(3);

				// int authority_id=resultset.getInt(4);
				Authority authority = new AuthorityServiceImpl().findAuthorityById(authority_id);

				int status = resultset.getInt(5);

				ScrapCollectionRequest = new ScrapCollectionRequest(id, user, date, status, null);

				objectList.add(ScrapCollectionRequest);
				// ScrapCollectionRequest=new ScrapCollectionRequest(id,
				// first_name, last_name, gender,locadDate, addres,
				// contact,city, authority, status);
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
	public List<ScrapCollectionRequest> getUniqueScrapRequests() {
		// TODO Auto-generated method stub
		 List<ScrapCollectionRequest> requestList=new ArrayList<>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("select distinct request from scraprequest_scraptype;");

			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int requestid = resultset.getInt(1);

				 ScrapCollectionRequest requestobj=new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(requestid);

				// System.out.println("request obj is ... "+requestobj);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;

				requestList.add(requestobj);
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		return requestList;

	}
	
	@Override
	public List<ScrapCollectionRequest> findAllScrapCollectionRequest() {
		// TODO Auto-generated method stub
		List<ScrapCollectionRequest> objectList = new ArrayList<ScrapCollectionRequest>();

		ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequest();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collection_request");

			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id = resultset.getInt(1);
				int user_id = resultset.getInt(2);
				String date = resultset.getString(3);
				User user = new UserServiceImpl().findUserById(user_id);
				int authority_id = resultset.getInt(4);
				Authority authority = new Authority();
				authority.setId(authority_id);
				int status = resultset.getInt(5);
				scrapCollectionRequest = new ScrapCollectionRequest(id, user, date, status, null);
				objectList.add(scrapCollectionRequest);
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
	
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestAllFields(int aid){
		
		

		ScrapCollectionRequest scrapCollectionRequest=null;
		ScrapRequestList requestlistobj=null;
		List<ScrapCollectionRequest> request_list=findScrapCollectionRequestByAuthorityStatus(aid,0);
		List<ScrapCollectionRequest> request_lists=new ArrayList<ScrapCollectionRequest>();
		List<ScrapRequestList> scraprequestarrylist=null;
		ResultSet resultset = null;
		for(ScrapCollectionRequest request:request_list){
			
		scraprequestarrylist=new ArrayList<ScrapRequestList>();
		
				int requestid=request.getId();
				connection = getConnection();
				try {
					statement = connection.prepareStatement("select * from scraprequest_scraptype where request="+requestid+";");
					
					resultset=statement.executeQuery();
					while (resultset != null && resultset.next()) {
						
					
						int scrapid=resultset.getInt(2);
						ScrapType scraptype=new ScrapTypeServiceImpl().findScrapTypeById(scrapid);
						float weight=resultset.getFloat(3);
						requestlistobj=new ScrapRequestList(scraptype, weight);
						System.out.println(">>>   "+requestlistobj);
						scraprequestarrylist.add(requestlistobj);
					
				
			}
					scrapCollectionRequest=new ScrapCollectionRequest(request.getId(), request.getUser(), request.getDate(), request.getStatus(), scraprequestarrylist);
					request_lists.add(scrapCollectionRequest);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				
			
		
	}
		
	
		return request_lists;
	}

	@Override
	public boolean updateScrapCollectionRequestStatus(int id) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		connection = getConnection();
		try {
			statement = connection
					.prepareStatement("update scrap_collection_request set status=1 where id=?;");
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

	@Override
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthorityStatus(int authority_id, int state) {
		// TODO Auto-generated method stub
		List<ScrapCollectionRequest> objectList = new ArrayList<ScrapCollectionRequest>();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scrap_collection_request WHERE authority=? and status=?");
			statement.setInt(1, authority_id);
			statement.setInt(2, state);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				ScrapCollectionRequest ScrapCollectionRequest = new ScrapCollectionRequest();
				int id = resultset.getInt(1);
				int user_id = resultset.getInt(2);
				User user = new UserServiceImpl().findUserById(user_id);

				String date = resultset.getString(3);

				// int authority_id=resultset.getInt(4);
				Authority authority = new AuthorityServiceImpl().findAuthorityById(authority_id);

				int status = resultset.getInt(5);

				ScrapCollectionRequest = new ScrapCollectionRequest(id, user, date, status, null);

				objectList.add(ScrapCollectionRequest);
				// ScrapCollectionRequest=new ScrapCollectionRequest(id,
				// first_name, last_name, gender,locadDate, addres,
				// contact,city, authority, status);
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
	public ScrapCollectionRequest findScrapCollectionRequestByIdAll(int id) {
		// TODO Auto-generated method stub
		ScrapCollectionRequest scrapCollectionRequest=null;
		ScrapRequestList requestlistobj=null;
		ScrapCollectionRequest request_list=findScrapCollectionRequestById(id);
		List<ScrapRequestList> scraprequestarrylist=new ArrayList<ScrapRequestList>();
		ResultSet resultset = null;
		
			
		
					try {
						connection = getConnection();
						statement = connection.prepareStatement("select * from scraprequest_scraptype where request="+id+";");
						resultset=statement.executeQuery();
						while (resultset != null && resultset.next()) {
							
						
							int scrapid=resultset.getInt(2);
							ScrapType scraptype=new ScrapTypeServiceImpl().findScrapTypeById(scrapid);
							float weight=resultset.getFloat(3);
							requestlistobj=new ScrapRequestList(scraptype, weight);
							System.out.println(">>>   "+requestlistobj);
							scraprequestarrylist.add(requestlistobj);
						
					
				}
						scrapCollectionRequest=new ScrapCollectionRequest(request_list.getId(), request_list.getUser(), scraprequestarrylist);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
							
					
				
		
				
			
					closeDBResource();
					System.out.println("Find all data  Successfully");
					
					return scrapCollectionRequest;
	}

}
	
	
	
	
	
	

