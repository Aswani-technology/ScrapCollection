package com.scrapcollection.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.scrapcollection.domain.FeedBack;
import com.scrapcollection.domain.Task;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.FeedBackService;
import com.scrapcollection.service_impl.FeedBackServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/feedback")
public class FeedBackWebService {
	
	FeedBackService service=new FeedBackServiceImpl();
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addFeedback(FeedBack feedback) throws JSONException
	{
		
		int id=service.saveFeedBack(feedback);
		 
		
		
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
	
	@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findFeedback(@PathParam("id") int id) throws JSONException
	{
		FeedBack obj=service.findFeedBackById(id);
		System.out.println("feedback  is "+obj);
		 
		
		
		String response="0";
		 
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		 if(obj.getId() == 0)
		 {
			 jsonObject.put("feedback", new JSONObject(obj));
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		  
		 
		  
		 jsonObject.put("feedback", new JSONObject(obj));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	
	@GET
	@Path("/findAllByUser/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllByScrapCollector(@PathParam("id") int id) {

		List<FeedBack> feedBackList=service.findAllFeedBackByUser(id);
		
		
		

		if (feedBackList.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("List is empty").build();
		}

		return Response.ok(feedBackList, MediaType.APPLICATION_JSON).build();

	}
	
	

}
