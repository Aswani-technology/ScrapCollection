package com.scrapcollection.web;

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

import com.scrapcollection.domain.Complaint;
import com.scrapcollection.service.ComplaintService;
import com.scrapcollection.service_impl.ComplaintServiceImpl;
import com.scrapcollection.util.Constants;

@Path("/complaint")
public class ComplaintWebService {
	
	ComplaintService service=new ComplaintServiceImpl();
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addComplaint(Complaint complaint) throws JSONException
	{
		
		int id=service.saveComplaint(complaint);
		 
		 
		
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
	public String findComplaint(@PathParam("id") int id) throws JSONException
	{
		Complaint complaint=service.findComplaintById(id);
		System.out.println("complaint is "+complaint);
		 
		JSONObject jsonObject = null;
		String response="0";
		jsonObject = new JSONObject();
		 if(complaint.getId() == 0)
		 {
			 jsonObject.put("complaint", new JSONObject(complaint));
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		  
		 
		  
		 jsonObject.put("complaint", new JSONObject(complaint));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}

}
