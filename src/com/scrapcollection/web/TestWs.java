package com.scrapcollection.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrapcollection.data_impl.ScrapCollectionRequestDaoImpl;
/*import com.dev.mobile_attendance.domain.Attendance;
import com.dev.mobile_attendance.domain.ClassRoom;
import com.dev.mobile_attendance.domain.Hours;
import com.dev.mobile_attendance.domain.Subject;
import com.dev.mobile_attendance.service.dto.ServiceFactory;
import com.dev.mobile_attendance.util.Constants;
import com.dev.mobile_attendance.util.DateUtils;
import com.google.gson.Gson;*/
import com.scrapcollection.domain.Login;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequestList;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service.UserService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/test")
public class TestWs {

 
	
	@GET
	@Path("/list")
	public String get() throws JSONException
	{
		
		ScrapTypeService service=new ScrapTypeServiceImpl();
		UserService serv=new UserServiceImpl();
		
		List<User>userlist=serv.findAllUser();
		List<ScrapType> list=service.findAllScrapType();
		JSONObject jsonObject = new JSONObject();
		
		 
		
		
		JSONArray array = new JSONArray(
				new Gson().toJson(userlist));
	 
		// ////////////////////put into object
		jsonObject.put("user list ", array);
		String response="1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
 
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	 public String login(User user) throws JSONException, JsonParseException, JsonMappingException, IOException
	{
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
	
 
	 
	 

@GET
@Path("/testrequest")
public String findAllUniqueRequests() throws JSONException
{
	List<ScrapCollectionRequest> list=new ScrapCollectionRequestDaoImpl().getUniqueScrapRequests();
	JSONObject jsonObject = new JSONObject();
	
	 
	
	
	JSONArray array = new JSONArray(
			new Gson().toJson(list));
 
	// ////////////////////put into object
	jsonObject.put("requestlist ", array);
	String response="1";
	return Constants.Util.getResponseMessageForClient(jsonObject, response);
}
 
/*@GET
@Path("/testrequestmap")
public String findAllReqestAsMap() throws JSONException
{
	Map<ScrapCollectionRequest, List<ScrapRequestList>> map=new ScrapCollectionRequestDaoImpl().findAllAsMap();
	List<ScrapCollectionRequest> newlist=new ArrayList<>();
	for (Map.Entry<ScrapCollectionRequest, List<ScrapRequestList>> entry : map.entrySet()) {
		ScrapCollectionRequest key = entry.getKey();
		List<ScrapRequestList> value = entry.getValue();
	   // System.out.println("key, " + key + " value " + value);
		
		
		key.setScrapRequestList(value);
		newlist.add(key);
	}
	
	
	
	 
	 JSONObject jsonObject = new JSONObject();
	
	JSONArray array = new JSONArray(
			new Gson().toJson(newlist));
 
	// ////////////////////put into object
	jsonObject.put("requestlist ", array);
	String response="1"; 
	return Constants.Util.getResponseMessageForClient(jsonObject, response);
 
}*/

@POST
@Path("/posttest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String test(Object obj)
{
	System.out.println("/////  "+obj);
	 
	
	//list.forEach(s->System.out.println(s));
	return "";
	
}

@POST
@Path("/posttest2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String test2(ScrapCollectionRequest request)
{
 System.out.println(request);	
	
	return "";
	

}

@POST
@Path("/listtest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String test3(List<ScrapCollectionRequest> requestlist)
{
	requestlist.stream().forEach(s->System.out.println("00000   "+s));
	
	return "";
	

}
 
}