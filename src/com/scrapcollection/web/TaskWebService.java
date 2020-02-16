package com.scrapcollection.web;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.Task;
import com.scrapcollection.service.TaskService;
import com.scrapcollection.service_impl.TaskServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/task")
public class TaskWebService {

	TaskService service = new TaskServiceImpl();

	/*@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addTask(Task task) {
		System.out.println("^^^^^^^   " + task);
		int id = service.saveTask(task);
		String response = Constants.Response.FAILED;
		if (id != 0) {
			response = Constants.Response.SUCCESS;
		}

		return response;
	}*/

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateTask(Task task) throws JSONException {

		boolean id = service.updateTask(task);
		JSONObject jsonObject = null;
		String response = "0";
		jsonObject = new JSONObject();
		if (!id) {
			jsonObject.put("status", "0");
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}

		response = "1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}

	@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String find(@PathParam("id") int id) throws JSONException {

		Task obj = service.findTaskById(id);
		JSONObject jsonObject = new JSONObject();
		String response = "0";
	
		if (obj.getId() == 0) {
			response = "0";
			jsonObject.put("task", new JSONObject(obj));
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}

		jsonObject.put("task", new JSONObject(obj));
		response = "1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);

	}

	@GET
	@Path("/findAllByScrapCollector/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findByScrapCollector(@PathParam("id") int id) throws JSONException {

		List<Task> taskList = service.findAllTaskByScrapCollector(id);
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(taskList));
		if (taskList.isEmpty()) {
			jsonObject.put("task_list", array);
			String response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}
		jsonObject.put("task_list", array);
		String response = "1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);

	}

	@GET
	@Path("/findAllAssigned/{scrapcollector}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllAssignedTaskByScrapCollector(@PathParam("scrapcollector") int id) throws JSONException {

		List<Task> taskList = service.findAllTaskByScrapCollector(id);
		List<Task> tasklist_assigned = taskList.stream().filter(list ->list.getStatus()==0)
				.collect(Collectors.toList());
		

		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(tasklist_assigned));
		if (taskList.isEmpty()) {
			jsonObject.put("task_list", array);
			String response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}
		jsonObject.put("task_list", array);
		String response = "1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);

	}

}
