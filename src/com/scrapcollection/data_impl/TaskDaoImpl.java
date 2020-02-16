package com.scrapcollection.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scrapcollection.data.TaskDao;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.Task;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.util.Constants;

public class TaskDaoImpl  extends DatabaseDaoImpl implements TaskDao{

	

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveTask(Task task) {
		// TODO Auto-generated method stub

		int key = 0;
		System.out.println(task);
		try {
			connection = getConnection();
			System.out.println("======================user=======================");

			statement = connection.prepareStatement("insert into Task values(?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, task.getId());
			statement.setInt(2, task.getRequest().getId());
			statement.setInt(3, task.getScrapCollector().getId());
			statement.setString(4, task.getDate());
			statement.setString(5, task.getTask_completed_date());
			statement.setInt(6, task.getStatus());
			
			 

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
		builder.append("status").append("=?,").append("task_completed_time").append("=?") ;
		return builder.toString();
	}   
	
	@Override
	public boolean updateTask(Task task) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("update Task set "+ getAllColumnName() + " where id=?;");
 
			 
			statement.setInt(1, 1);
			statement.setString(2, task.getTask_completed_date());
			statement.setInt(3, task.getId());
			 
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
			System.out.println("Error occured in updation");
			e.printStackTrace();
		}

		closeDBResource();
		return is_update;
	}

	@Override
	public Task findTaskById(int id) {
		// TODO Auto-generated method stub
		Task task = null;

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM Task  WHERE id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				// int id = resultset.getInt(1);
				 int request=resultset.getInt(2);
				 ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestByIdAll(request);
				  
				 
				 /*ScrapCollector scrapCollector=new ScrapCollectorServiceImpl().findScrapCollectorById(resultset.getInt(3));
				 
				 String task_assigned_time=resultset.getString(4);
				 String task_completed_time=resultset.getString(5);
				 int status=resultset.getInt(6);*/
				 
				//Task=new Task(id, scrapCollectionRequest, scrapCollector, task_assigned_time, task_completed_time, status);
				  task=new Task(id,scrapCollectionRequest);

				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		} catch (SQLException se) {
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			se.printStackTrace();
		}

		closeDBResource();
		System.out.println("Find all data  Successfully"+task);
		return task;
	}

	@Override
	public List<Task> findAllTask() {
		// TODO Auto-generated method stub
		List<Task> objectList = new ArrayList<Task>();

	

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM Task ");
			 
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
				Task task=new Task();
				int id=resultset.getInt(1);
				 int request=resultset.getInt(2);
				 ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequest();
				 scrapCollectionRequest.setId(request);
				 
				 ScrapCollector scrapCollector=new ScrapCollector();
				 scrapCollector.setId(resultset.getInt(3));
				 String task_assigned_time=resultset.getString(4);
				 String task_completed_time=resultset.getString(5);
				 int status=resultset.getInt(6);
				 
				task=new Task(id, scrapCollectionRequest, scrapCollector, task_assigned_time, task_completed_time, status);
				objectList.add(task);

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
	public boolean deleteTask(int id) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM task WHERE id = ?");
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
	public List<Task> findAllTaskByScrapCollector(int scrapcollector) {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		List<Task> objectList = new ArrayList<Task>();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(" select * from task where scrap_collector=?;");
			statement.setInt(1, scrapcollector);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
				Task task=new Task();
				int id=resultset.getInt(1);
				 int request=resultset.getInt(2);
				 ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequestServiceImpl().findScrapCollectionRequestById(resultset.getInt(2));
				 
				 
				 ScrapCollector scrapCollector=new ScrapCollectorServiceImpl().findScrapCollectorById(resultset.getInt(3));
				 
				  
				 
				 String task_assigned_time=resultset.getString(4);
				 String task_completed_time=resultset.getString(5);
				 int status=resultset.getInt(6);
				 
				User user=scrapCollectionRequest.getUser();
				 
				//task=new Task(id, scrapCollectionRequest, scrapCollector, task_assigned_time, task_completed_time, status);
				task=new Task(id,user,task_assigned_time,status);
				 objectList.add(task);

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
	public List<Task> findAllTaskByStatus(int state) {
		// TODO Auto-generated method stub
		List<Task> objectList = new ArrayList<Task>();

		

		ResultSet resultset = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM Task where status=?");
			 statement.setInt(1,state);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
				Task task=new Task();
				int id=resultset.getInt(1);
				 int request=resultset.getInt(2);
				 ScrapCollectionRequest scrapCollectionRequest = new ScrapCollectionRequest();
				 scrapCollectionRequest.setId(request);
				 
				 ScrapCollector scrapCollector=new ScrapCollector();
				 
				 scrapCollector.setId(resultset.getInt(3));
				 String task_assigned_time=resultset.getString(4);
				 String task_completed_time=resultset.getString(5);
				 int status=resultset.getInt(6);
				 
				task=new Task(id, scrapCollectionRequest, scrapCollector, task_assigned_time, task_completed_time, status);
				objectList.add(task);

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
