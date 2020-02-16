package com.scrapcollection.web;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.scrapcollection.domain.Login;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.UserService;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;

import lombok.Getter;

@Path("/user")
public class UserWebService {
	UserService service=new UserServiceImpl();
	@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findUser(@PathParam("id") int id) throws JSONException
	{
		 
	 
	 
	 UserService service=new UserServiceImpl();
		String response="0";
		User obj=service.findUserById(id);
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		 if(obj.getId() == 0)
		 {
			 jsonObject.put("user", new JSONObject(obj));
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		 JSONObject user1 = new JSONObject();
		 user1.put("FirstName", "John");
		 user1.put("LastName", "Reese");
		// main.put("User", user1);
		 userjson.put("User", new JSONObject(obj));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
			
	 
	}
	
	@POST
	@Path("/registration")
	public String registration(User user) throws JSONException
	{
		int id=service.saveUser(user);
		 
		 
		 String response="0";
			 
			JSONObject jsonObject = null;
			jsonObject = new JSONObject();
			 if(id == 0)
			 {
				 jsonObject.put("status", "0");
				 return Constants.Util.getResponseMessageForClient(jsonObject, response);
			 }
			 			 		 
			 		 		 
			 response="1";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		
	}
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(User user) throws JSONException
	{
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		String response="0";
		 
		boolean result=service.updateUser(user);
		System.out.println("result "+result);
		if(!result) {
			jsonObject.put("status", "0");
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	    }
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	 public String login(User user) throws JSONException, JsonParseException, JsonMappingException, IOException
		{
		System.out.println("inside login "+user);
			UserService service=new UserServiceImpl();
			String response="0";
			User obj=service.login(user.getEmail(), user.getPassword());
			JSONObject jsonObject = null;
			jsonObject = new JSONObject();
			 if(obj.getId() == 0)
			 {
				 jsonObject.put("user", new JSONObject(obj));
				 return Constants.Util.getResponseMessageForClient(jsonObject, response);
			 }
			 				 		 
			 JSONObject main = new JSONObject();
			 
			 JSONObject user1 = new JSONObject();
			 user1.put("FirstName", "John");
			 user1.put("LastName", "Reese");
			// main.put("User", user1);
			 main.put("User", new JSONObject(obj));
			 response="1";
			 return Constants.Util.getResponseMessageForClient(main, response);
				
				 
				 
		} 
	 
	
	
	

}
