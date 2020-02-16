package com.scrapcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.Task;
import com.scrapcollection.service.ScrapCollectionRequestService;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service.TaskService;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.service_impl.TaskServiceImpl;

/**
 * Servlet implementation class ScrapCollectionRequestController
 */
@WebServlet("/ScrapCollectionRequestController")
public class ScrapCollectionRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapCollectionRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command=request.getParameter("command");
		
		switch(command){
		
		case "task":
			//String id=request.getParameter("id");
			int request_id=Integer.parseInt(request.getParameter("id"));
			HttpSession session1=request.getSession();
			Authority authority_obj=(Authority) session1.getAttribute("authority");
			ScrapCollectionRequestService request_service=new ScrapCollectionRequestServiceImpl();
			ScrapCollectorService service=new ScrapCollectorServiceImpl();
			ScrapCollectionRequest list=request_service.findScrapCollectionRequestById(request_id);
			List<ScrapCollector> collectors_list=service.findAllScrapCollectorByAuthorityandStatus(authority_obj.getId());
			request.setAttribute("scraprequest", list);
			request.setAttribute("Collectors",collectors_list);
			
			if(!response.isCommitted())
			{
				request.getRequestDispatcher("jsp_pages/Authority/scrap_collection_request/assign_task_to_collector.jsp").forward(request, response);	
			}
			
			
			
			break;
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TaskService service=new TaskServiceImpl();
		ScrapCollectionRequest collection_request=new ScrapCollectionRequest();
		ScrapCollectionRequestService request_service=new ScrapCollectionRequestServiceImpl();
		ScrapCollectorService collector_service=new ScrapCollectorServiceImpl(); 
		HttpSession session1=request.getSession();
		Authority authority_obj=(Authority) session1.getAttribute("authority");
		ScrapCollector scrapCollector=new ScrapCollector();
		String command=request.getParameter("command");
		switch(command){
		
		case "assign_task":
							int req_id=Integer.parseInt(request.getParameter("request_Id"));
							collection_request.setId(req_id);
							int collector_id=Integer.parseInt(request.getParameter("collectors"));
							scrapCollector.setId(collector_id);
							String date=request.getParameter("date");
							Task task=new Task();
							task.setRequest(collection_request);
							task.setScrapCollector(scrapCollector);
							task.setDate(date);
							service.saveTask(task);
							request_service.updateScrapCollectionRequestStatus(req_id);
							collector_service.updateScrapCollectorStatus(collector_id);
							List<ScrapCollectionRequest> list=request_service.findAllScrapCollectionRequestAllFields(authority_obj.getId());
							
							ScrapCollectorService services=new ScrapCollectorServiceImpl();
							List<ScrapCollector> collectors_list=services.findAllScrapCollectorByAuthorityandStatus(authority_obj.getId());
							request.setAttribute("Collectors",collectors_list);
							
							
							
							
							request.setAttribute("scraprequest_list", list);
							
							if(!response.isCommitted())
							{
								
							request.getRequestDispatcher("jsp_pages/Authority/scrap_collection_request/scrap_collection_request_list.jsp").forward(request, response);
							}
							break;
		
		}
		
	}

}
