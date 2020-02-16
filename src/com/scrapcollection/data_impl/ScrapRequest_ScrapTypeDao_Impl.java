package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scrapcollection.data.ScrapRequest_ScrapTypeDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.ScrapRequestList;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.ScrapCollectionRequestService;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;

public class ScrapRequest_ScrapTypeDao_Impl extends DatabaseDaoImpl implements ScrapRequest_ScrapTypeDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public List<ScrapRequest_ScrapType> findAll(int authority_id) {
		// TODO Auto-generated method stub
		List<ScrapRequest_ScrapType> objectList = new ArrayList<ScrapRequest_ScrapType>();

		ScrapCollector scrapCollector = new ScrapCollector();

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scraprequest_scraptype where request=?");
			statement.setInt(1, 0);
			// availability 0 -> available
			// 1 -> not available
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name = resultset.getString(3);
				String gender = resultset.getString(4);
				// Date dob = resultset.getDate(5);
				// LocalDate locadDate=DateToLocalDateConverter.convert(dob);
				String email = resultset.getString(5);
				String password = resultset.getString(6);
				String dob = resultset.getString(7);
				String addres = resultset.getString(8);
				Long contact = resultset.getLong(9);
				String city = resultset.getString(10);

				Authority authority = new Authority();
				authority.setId(authority_id);
				int status = resultset.getInt(12);
				scrapCollector = new ScrapCollector(id, first_name, last_name, gender, email, password, dob, addres,
						contact, city, authority, status);
				// objectList.add(scrapCollector);
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
	public ScrapRequest_ScrapType findById(int request_id) {
		// TODO Auto-generated method stub
		ScrapRequest_ScrapType request_type = new ScrapRequest_ScrapType();
		List<ScrapRequest_ScrapType> objectList = new ArrayList<ScrapRequest_ScrapType>();
		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM scraprequest_scraptype where request=?");
			statement.setInt(1, request_id);
			// availability 0 -> available
			// 1 -> not available
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				int scraptype = resultset.getInt(2);
				float weight = resultset.getFloat(3);

				ScrapTypeService scraptypeservice = new ScrapTypeServiceImpl();
				ScrapType scrapType = scraptypeservice.findScrapTypeById(scraptype);
				ScrapCollectionRequestService request_service = new ScrapCollectionRequestServiceImpl();
				ScrapCollectionRequest scraprequest = request_service.findScrapCollectionRequestById(request_id);
				
				System.out.println("collection request obj is "+scraprequest);
				/*
				 * request_type=new ScrapRequest_ScrapType(scraprequest,
				 * scrapType, weight);
				 */
				request_type = new ScrapRequest_ScrapType(scraprequest);
				// int status=resultset.getInt(12);
				System.out.println("***********  " + request_type);
				objectList.add(request_type);
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully");

		return request_type;
	}

	@Override
	public Map<ScrapCollectionRequest, List<ScrapType>> findAll() {
		// TODO Auto-generated method stub

		List<ScrapRequest_ScrapType> request_ScrapTypeList = new ArrayList<ScrapRequest_ScrapType>();

		Map<ScrapCollectionRequest, List<ScrapType>> map = new HashMap<>();
		ScrapCollectionRequest scraprequest = null;
		ResultSet resultset = null;
		try {
			connection = getConnection();
			/*
			 * statement = connection.
			 * prepareStatement("SELECT * FROM scraprequest_scraptype where request=10;"
			 * );
			 */
			statement = connection.prepareStatement("SELECT * FROM scraprequest_scraptype");
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				int request_id = resultset.getInt(1);
				int scraptype_id = resultset.getInt(2);
				float weight = resultset.getFloat(3);

				scraprequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request_id);

				ScrapType scraptype = new ScrapTypeServiceImpl().findScrapTypeById(scraptype_id);
				/*
				 * ScrapRequest_ScrapType request_scraptype=new
				 * ScrapRequest_ScrapType(scraprequest, scraptype, weight);
				 */
				ScrapRequest_ScrapType request_scraptype = new ScrapRequest_ScrapType(scraprequest);

				request_ScrapTypeList.add(request_scraptype);

			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		Set<ScrapRequest_ScrapType> set = new HashSet<>();

		for (ScrapRequest_ScrapType list : request_ScrapTypeList) {
			System.out.println("~~~~~~~~~~ " + list);
			set.add(list);
		}

		set.stream().forEach(s -> System.err.println("..............???????????????????????????????????? "
				+ s.getRequest().getScrapRequestList().get(0).getScraptype()));

		/*
		 * List<ScrapCollectionRequest> collectionRequestList=new
		 * ScrapCollectionRequestServiceImpl().findAllScrapCollectionRequest();
		 * 
		 * for(ScrapCollectionRequest request : collectionRequestList) {
		 * List<ScrapType> scrapTypeList=new ArrayList<>(); for(
		 * ScrapRequest_ScrapType set_obj : set) {
		 * 
		 * 
		 * 
		 * if(request_typeobj.getRequest().equals(request)) {
		 * scrapTypeList.add(request_typeobj.getScrapType());
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * map.put(request, scrapTypeList); } for
		 * (Map.Entry<ScrapCollectionRequest, List<ScrapType>> entry :
		 * map.entrySet()) {
		 * System.out.println(entry.getKey()+" ::: "+entry.getValue()); }
		 */

		closeDBResource();

		return null;
	}

	@Override
	public Set<ScrapRequest_ScrapType> findAllScrapRequest_Type() {
		// TODO Auto-generated method stub
		List<ScrapRequest_ScrapType> request_ScrapTypeList = new ArrayList<ScrapRequest_ScrapType>();

		ScrapCollectionRequest scraprequest = null;
		ResultSet resultset = null;
		try {
			connection = getConnection();

			statement = connection.prepareStatement("SELECT * FROM scraprequest_scraptype");
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				int request_id = resultset.getInt(1);
				int scraptype_id = resultset.getInt(2);

				scraprequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request_id);

				ScrapType scraptype = new ScrapTypeServiceImpl().findScrapTypeById(scraptype_id);

				ScrapRequest_ScrapType request_scraptype = new ScrapRequest_ScrapType(scraprequest);

				request_ScrapTypeList.add(request_scraptype);

			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		Set<ScrapRequest_ScrapType> request_type_set = new HashSet<>();

		for (ScrapRequest_ScrapType list : request_ScrapTypeList) {
			// System.out.println("~~~~~~~~~~ "+list);
			request_type_set.add(list);
		}

	/*	request_type_set.stream().forEach(s -> System.err.println("..............???????????????????????????????????? "
				+ s.getRequest().getScrapRequestList().get(0).getScraptype()));*/
		 
		closeDBResource();

		return request_type_set;

	}

	@Override
	public Set<ScrapRequest_ScrapType> findAllScrapRequest_TypeByScrapCollector(int scrapcollector) {
		// TODO Auto-generated method stub
		List<ScrapRequest_ScrapType> request_ScrapTypeList = new ArrayList<ScrapRequest_ScrapType>();

		ScrapCollectionRequest scraprequest = null;
		ResultSet resultset = null;
		try {
			connection = getConnection();

			statement = connection.prepareStatement("SELECT * FROM scraprequest_scraptype WHERE ");
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				int request_id = resultset.getInt(1);
				int scraptype_id = resultset.getInt(2);

				scraprequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request_id);

				ScrapRequest_ScrapType request_scraptype = new ScrapRequest_ScrapType(scraprequest);

				request_ScrapTypeList.add(request_scraptype);

			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		Set<ScrapRequest_ScrapType> request_type_set = new HashSet<>();

		for (ScrapRequest_ScrapType list : request_ScrapTypeList) {
			// System.out.println("~~~~~~~~~~ "+list);
			request_type_set.add(list);
		}

		request_type_set.stream().forEach(s -> System.err.println("..............???????????????????????????????????? "
				+ s.getRequest().getScrapRequestList().get(0).getScraptype()));
		closeDBResource();

		return request_type_set;

	}

	@Override
	public ScrapRequest_ScrapType findRequestTypeById(int id) {
		// TODO Auto-generated method stub

		List<ScrapRequest_ScrapType> request_ScrapTypeList = new ArrayList<ScrapRequest_ScrapType>();

		ScrapCollectionRequest scraprequest = new ScrapCollectionRequest();
		ResultSet resultset = null;
		 
		List<ScrapRequestList> scrapRequestList=new ArrayList<>();
		ScrapRequest_ScrapType request_scraptype = null;
		try {
			connection = getConnection();
			/*
			 * statement = connection.
			 * prepareStatement("SELECT * FROM scraprequest_scraptype where request=10;"
			 * );
			 */

			statement = connection.prepareStatement(
					"SELECT * FROM scraprequest_scraptype request_type , scrap_collection_request request where request_type.request = request.id and request.id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {

				int request_id = resultset.getInt(1);
				int scraptype_id = resultset.getInt(2);
				float weight = resultset.getFloat(3);

				int user = resultset.getInt(5);
				String date = resultset.getString(6);
				// int authority = resultset.getInt(7);
				int status = resultset.getInt(8);
				// scraprequest = new
				// ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(request_id);
				User userobj = new UserServiceImpl().findUserById(user);
				scraprequest = new ScrapCollectionRequest(request_id, userobj, date, status, null);
				
				System.out.println("currently request obj haws "+scraprequest);
				ScrapType scraptype = new ScrapTypeServiceImpl().findScrapTypeById(scraptype_id);

				request_scraptype = new ScrapRequest_ScrapType(scraprequest);
				System.out.println("request object for id 10 is " + request_scraptype);
				
				ScrapRequestList requestListObj=new ScrapRequestList(scraptype,weight);
				scrapRequestList.add(requestListObj);
				 

				request_ScrapTypeList.add(request_scraptype);

			}
			scraprequest.setScrapRequestList(scrapRequestList);
			System.out.println("now this has become //// "+scraprequest);
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}
 

		closeDBResource();

		return request_scraptype;
	}

}
