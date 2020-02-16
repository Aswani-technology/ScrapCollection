package com.scrapcollection.web;

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
 
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/scrapCollector")
public class ScrapCollectorWebService {
	
	ScrapCollectorService service=new ScrapCollectorServiceImpl();
	/*@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findScrapCollector(@PathParam("id") int id) throws JSONException
	{
		ScrapCollector obj=service.findScrapCollectorById(id);
		 if(scrapCollector.getId() == 0) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for "+scrapCollector).build();
		    }
		 return Response.ok(scrapCollector, MediaType.APPLICATION_JSON).build();
		
		String response="0";
		 
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		 if(obj.getId() == 0)
		 {
			 jsonObject.put("Scrap Collector", new JSONObject(obj));
			 
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject scapcollectorjson = new JSONObject();
		 
		 
		 scapcollectorjson.put("Scrap Collector", new JSONObject(obj));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(scapcollectorjson, response);
			
	}
	
	
	
	 
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String  updateUser(ScrapCollector scrapCollector) throws JSONException
 
	
	{
		 boolean result= service.updateScrapCollector(scrapCollector);
		  
		 if(!status) {
		        return Response.status(Response.Status.EXPECTATION_FAILED).entity("updation failed!").build();
		    }
		 return Response.ok(status, MediaType.APPLICATION_JSON).build();
		 
		 JSONObject jsonObject = null;
			jsonObject = new JSONObject();
			String response="0";
			 
			 
			if(!result) {
				jsonObject.put("status", "0");
				 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		    }
			 response="1";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 
	} */
	
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(ScrapCollector scrapCollector) throws JSONException
	{
		 
		
		ScrapCollector obj=service.login(scrapCollector.getEmail(), scrapCollector.getPassword());

		 /*if(obj.getId() == 0) {
		        return Response.status(Response.Status.EXPECTATION_FAILED).entity("login failed").build();
		    }
		 return Response.ok(obj, MediaType.APPLICATION_JSON).build();*/
		
		String response="0";
		 
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		 if(obj.getId() == 0)
		 {
			 response="0";
			 jsonObject.put("ScrapCollectorId", obj.getId());
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 
		 
		 JSONObject objjson = new JSONObject();
		 
		 objjson.put("ScrapCollector",obj.getId());
		 response="1";
		 return Constants.Util.getResponseMessageForClient(objjson, response);
	}
	

	
	@GET
	@Path("/findById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findScrapCollectorById(@PathParam("id") int id)
	{
		ScrapCollector scrapCollector=service.findScrapCollectorById(id);
		String response = Constants.Response.FAILED;
		 if(scrapCollector.getId() == 0) {
				response = Constants.Response.FAILED;
		    }
		 else {

				response = Constants.Response.SUCCESS;
			}
		
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonObject_scrapcollector = new JSONObject(scrapCollector);
			 
			jsonObject.put("ScrapCollector", jsonObject_scrapcollector);
		//	jsonObject.put("class_room_list", classroom_array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Constants.Util.getResponseMessageForClient(jsonObject, response);

	}
	
	

}
