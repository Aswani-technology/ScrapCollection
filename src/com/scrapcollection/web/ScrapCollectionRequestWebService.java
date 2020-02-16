package com.scrapcollection.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.scrapcollection.data_impl.ScrapRequest_ScrapTypeDao_Impl;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.Task;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.ScrapCollectionRequestService;
import com.scrapcollection.service.ScrapRequest_ScrapTypeService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapRequest_ScrapTypeServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/scrapCollectionRequest")
public class ScrapCollectionRequestWebService {
	ScrapCollectionRequestService service = new ScrapCollectionRequestServiceImpl();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addCollectionRequest(ScrapCollectionRequest request) throws JSONException {
		System.out.println("^^^^^^^   " + request);
		int id = service.saveScrapCollectionRequest(request);
		/*
		 * String response = Constants.Response.FAILED; if(id != 0) { response =
		 * Constants.Response.SUCCESS; }
		 * 
		 * 
		 * 
		 * return response;
		 */

		String response = "0";

		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		if (id == 0) {
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
	public String findScrapRequestById(@PathParam("id") int id) throws JSONException {

		ScrapRequest_ScrapTypeService request_scraptypeservice = new ScrapRequest_ScrapTypeServiceImpl();
		Set<ScrapRequest_ScrapType> list = request_scraptypeservice.findAllScrapRequest_Type();
		System.out.println("id is " + id);

		for (ScrapRequest_ScrapType obj : list) {
			System.out.println("/////////\\\\\\  " + obj.getRequest().getId());

			if (obj.getRequest().getId() == id) {
				System.out.println("~~~~~!!!! " + obj);
			}
		}

		ScrapRequest_ScrapType obj = new ScrapRequest_ScrapTypeDao_Impl().findById(id);
		System.out.println("request type object is " + obj);
		String response = "0";

		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		if (obj.getRequest() == null) {
			response = "0";
			jsonObject.put("ScrapCollectionRequest", new JSONObject(obj));
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}

		JSONObject objjson = new JSONObject();

		objjson.put("ScrapCollectionRequest", new JSONObject(obj));
		response = "1";
		return Constants.Util.getResponseMessageForClient(objjson, response);

	}

	@GET
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/* public Set<ScrapRequest_ScrapType> findAll() */
	public String findAll() throws JSONException, JsonProcessingException
	{
 /*
		ScrapRequest_ScrapTypeService request_scraptypeservice = new ScrapRequest_ScrapTypeServiceImpl();
		Set<ScrapRequest_ScrapType> list = request_scraptypeservice.findAllScrapRequest_Type();
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(list));
		jsonObject.put("request_list ", array);
		String response = "1";
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(list);
		System.out.println(".........");
		System.out.println(jsonString);
		//return Constants.Util.getResponseMessageForClient(jsonObject, response);
		return jsonString.toString();*/
		String response = null;
		List<ScrapCollectionRequest> sList   =  service.findAllScrapCollectionRequest();
		List<ScrapCollectionRequest> request_list = sList.stream().filter(list ->list.getStatus()==0)
				.collect(Collectors.toList());
		JSONObject jsonObject = new JSONObject();
		JSONArray sarray = new JSONArray(new Gson().toJson(request_list));
		
		if(request_list.isEmpty()){
			response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
		else{
			response = "1";
			jsonObject.put("request_list", sarray);
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
		

	}

	/*
	 * @GET
	 * 
	 * @Path("/findAllByScrapCollector/{scrapcollector}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Set<ScrapRequest_ScrapType>
	 * findAllByScrapCollector(@PathParam("scrapcollector") int scrapcollector)
	 * 
	 * {
	 * 
	 * ScrapRequest_ScrapTypeService request_scraptypeservice = new
	 * ScrapRequest_ScrapTypeServiceImpl(); return
	 * request_scraptypeservice.findAllScrapRequest_TypeByScrapCollector(
	 * scrapcollector);
	 * 
	 * }
	 */

	@GET
	@Path("/findAllByUser/{user}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllByScrapCollector(@PathParam("user") int user) throws JSONException, JsonProcessingException

	{

	
		
		
		/*ScrapRequest_ScrapTypeService request_scraptypeservice = new ScrapRequest_ScrapTypeServiceImpl();
		Set<ScrapRequest_ScrapType> list = request_scraptypeservice.findAllScrapRequest_Type();

		List<ScrapRequest_ScrapType> newlist = new ArrayList<>();
		for (ScrapRequest_ScrapType obj : list) {
			if (obj.getRequest().getUser().getId() == user) {
				newlist.add(obj);
			}
		}

		JSONObject jsonObject = new JSONObject();

		// JSONArray array = new JSONArray(new Gson().toJson(list));
		JSONArray array = new JSONArray(new Gson().toJson(newlist));
		System.out.println("array" + array);

		// ////////////////////put into object
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(list);
		System.out.println(".........");
		System.out.println(jsonString);

		if (newlist.isEmpty()) {
			String response = "0";
			jsonObject.put("request_list ", array);

			return Constants.Util.getResponseMessageForClient(jsonObject, response);

		}
		String response = "1";
		jsonObject.put("request_list ", array);
		//return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 
*/		
		List<ScrapCollectionRequest> sList   =  service.findAllScrapCollectionRequest();
		List<ScrapCollectionRequest> newlist = new ArrayList<ScrapCollectionRequest>();
		for (ScrapCollectionRequest obj : sList) {
			if (obj.getUser().getId() == user) {
				newlist.add(obj);
			}
		}
		JSONObject jsonObject = new JSONObject();
		JSONArray sarray = new JSONArray(new Gson().toJson(newlist));
		//jsonObject.put("request_list", sarray);
		//ObjectMapper mapper = new ObjectMapper();
		//String jsonString = mapper.writeValueAsString(newlist);
		
		//return "{\"request_list\":"+jsonObject+"}";
		
		//return  "{\"request_list\":"+jsonString+"}";
		String response = "1";
		jsonObject.put("request_list", sarray);
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
		
		
	}

}
