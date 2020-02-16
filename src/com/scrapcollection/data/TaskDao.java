package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.Task;

public interface TaskDao {
	
	public int saveTask(Task Task);
	public boolean updateTask(Task Task);
	public Task findTaskById(int id);
	public List<Task> findAllTask();
	public boolean deleteTask(int id);
	
	public List<Task> findAllTaskByScrapCollector(int scrapcollector);

	public List<Task> findAllTaskByStatus(int status);
	
	

}
