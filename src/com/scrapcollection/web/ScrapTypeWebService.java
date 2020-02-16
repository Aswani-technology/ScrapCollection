package com.scrapcollection.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.scrapcollection.domain.ScrapRequestList;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.domain.User;
import com.scrapcollection.domain.scrapRequest;
import com.scrapcollection.service.ScrapCollectionRequestService;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service.UserService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.UserServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/scrapType")
public class ScrapTypeWebService {

	ScrapTypeService service=new ScrapTypeServiceImpl();
	@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findScrapType(@PathParam("id") int id) throws JSONException
	{
		ScrapType obj=service.findScrapTypeById(id);
		
		/* if(scrapType.getId() == 0) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for "+scrapType).build();
		    }
		 return Response.ok(scrapType, MediaType.APPLICATION_JSON).build();*/
		 
		 
		 
		 String response="0";
		 
			JSONObject jsonObject = null;
			jsonObject = new JSONObject();
			 if(obj.getId() == 0)
			 {
				 jsonObject.put("ScrapType", new JSONObject(obj));
				 return Constants.Util.getResponseMessageForClient(jsonObject, response);
			 }
			 				 		 
			 JSONObject objjson = new JSONObject();
			 
		 
			 
			 objjson.put("ScrapType", new JSONObject(obj));
			 response="1";
			 return Constants.Util.getResponseMessageForClient(objjson, response);
				
	}
	
	@GET
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllScrapType() throws JSONException
	{
		/*List<ScrapType> scrapTypeList=service.findAllScrapType();
		
		 if(scrapTypeList.isEmpty()) {
		        return Response.status(Response.Status.NOT_FOUND).entity("List is empty").build();
		    }
		 return Response.ok(scrapTypeList, MediaType.APPLICATION_JSON).build();*/
		
		ScrapTypeService service=new ScrapTypeServiceImpl();
		 
		 
		List<ScrapType> list=service.findAllScrapType();
		JSONObject jsonObject = new JSONObject();
		
		 
		
		
		JSONArray array = new JSONArray(
				new Gson().toJson(list));
	 
		// ////////////////////put into object
		jsonObject.put("scraptype list ", array);
		String response="1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	
	@GET
	@Path("/findScrapTypeByRequest/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findScrapTypeByRequest(@PathParam("id") int id) throws JSONException
	{
		ScrapCollectionRequestService service=new ScrapCollectionRequestServiceImpl();
		ScrapCollectionRequest request=service.findScrapCollectionRequestByIdAll(id);
		
		/* if(scrapType.getId() == 0) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for "+scrapType).build();
		    }
		 return Response.ok(scrapType, MediaType.APPLICATION_JSON).build();*/
		 List<scrapRequest> lists=new ArrayList<scrapRequest>();
		 List<ScrapRequestList> scrapType_list=request.getScrapRequestList();
		 
		 for(ScrapRequestList list: scrapType_list){
			 ScrapRequestList item=new ScrapRequestList();
			 scrapRequest  requests=new scrapRequest();
			 requests.setName(list.getScraptype().getName());
			 requests.setWeight(list.getWeight());
			 lists.add(requests);
		 }
		 
		 String response="0";
		 
			JSONObject jsonObject = null;
			jsonObject = new JSONObject();
			 if(lists.isEmpty())
			 {
				 response="0";
				
				 return Constants.Util.getResponseMessageForClient(jsonObject, response);
			 }
			 				 		 
			 JSONObject objjson = new JSONObject();
			 JSONArray array = new JSONArray(new Gson().toJson(lists));
		 
			 
			 objjson.put("ScrapRequest",array);
			 response="1";
			 return Constants.Util.getResponseMessageForClient(objjson, response);
				
	}
	
	
	
}
