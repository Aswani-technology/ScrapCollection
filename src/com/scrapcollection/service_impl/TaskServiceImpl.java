package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.TaskDao;
import com.scrapcollection.data_impl.TaskDaoImpl;
import com.scrapcollection.database.DatabaseDaoImpl;
import com.scrapcollection.domain.Task;
import com.scrapcollection.service.TaskService;

public class TaskServiceImpl extends DatabaseDaoImpl implements TaskService{
	TaskDao dao=new TaskDaoImpl();

	@Override
	public int saveTask(Task task) {
		// TODO Auto-generated method stub
		return dao.saveTask(task);
	}

	@Override
	public boolean updateTask(Task Task) {
		// TODO Auto-generated method stub
		return dao.updateTask(Task);
	}

	@Override
	public Task findTaskById(int id) {
		// TODO Auto-generated method stub
		return dao.findTaskById(id);
	}

	@Override
	public List<Task> findAllTask() {
		// TODO Auto-generated method stub
		return dao.findAllTask();
	}

	@Override
	public boolean deleteTask(int id) {
		// TODO Auto-generated method stub
		return dao.deleteTask(id);
	}

	@Override
	public List<Task> findAllTaskByScrapCollector(int scrapcollector) {
		// TODO Auto-generated method stub
		return dao.findAllTaskByScrapCollector(scrapcollector);
	}

	@Override
	public List<Task> findAllTaskByStatus(int status) {
		// TODO Auto-generated method stub
		return dao.findAllTaskByStatus(status);
	}
	

}
