package com.scrapcollection.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.Complaint;
import com.scrapcollection.domain.FeedBack;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.domain.Task;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service.ComplaintService;
import com.scrapcollection.service.FeedBackService;
import com.scrapcollection.service.ScrapCollectionRequestService;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service.ScrapRequest_ScrapTypeService;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service.TaskService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.service_impl.ComplaintServiceImpl;
import com.scrapcollection.service_impl.FeedBackServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectionRequestServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.service_impl.ScrapRequest_ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
import com.scrapcollection.service_impl.TaskServiceImpl;

/**
 * Servlet implementation class PageControllerServlet_Authority
 */
@WebServlet("/PageControllerServlet_Authority")
public class PageControllerServlet_Authority extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageControllerServlet_Authority() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String next_page = request.getParameter("page");
		System.out.println("next page is ====" + next_page);
		HttpSession session=request.getSession();
		Authority authority=(Authority) session.getAttribute("authority");
		switch (next_page) {
		
		case "profile": 
				AuthorityService authority_service=new AuthorityServiceImpl();
				Authority profile=authority_service.findAuthorityById(authority.getId());
				request.setAttribute("Authority", profile);
				if(!response.isCommitted())
				{
				request.getRequestDispatcher("jsp_pages/Authority/profile/profile.jsp").forward(request, response);
				}
			
				break;
		
		
		
		case "scraptype":

			ScrapTypeService service = new ScrapTypeServiceImpl();
			List<ScrapType> scrapTypeList = service.findAllScrapType();
			System.out.println("scrap type list is " + scrapTypeList);
			request.setAttribute("scrap_type_list", scrapTypeList);
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Authority/scrap_type/scrap_type_list.jsp").forward(request, response);
			}
			break;

		case "scrapcollector":

			ScrapCollectorService sc_service = new ScrapCollectorServiceImpl();
			
			List<ScrapCollector> scrapCollectorList = sc_service.findAllScrapCollectorByAuthority(authority.getId());
			
			System.out.println("authority is "+authority.getId());
			System.out.println("scrap collector list is//// " + scrapCollectorList);
			request.setAttribute("scrap_collector_list", scrapCollectorList);
			
			request.getRequestDispatcher("jsp_pages/Authority/scrapcollector/scrapcollector_list.jsp").forward(request, response);
			/*if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Authority/scrapcollector/scrapcollector_list.jsp").forward(request, response);
			}*/
			
			
			break;
		 
		case "scrapcollection_request":
			System.out.println("///////////"+next_page);
			HttpSession session1=request.getSession();
			Authority authority_obj=(Authority) session1.getAttribute("authority");
			ScrapCollectionRequestService request_service=new ScrapCollectionRequestServiceImpl();
			//ScrapRequest_ScrapTypeService request_scraptypeservice = new ScrapRequest_ScrapTypeServiceImpl();
			//Set<ScrapRequest_ScrapType> list = request_scraptypeservice.findAllScrapRequest_Type();
			List<ScrapCollectionRequest> list=request_service.findAllScrapCollectionRequestAllFields(authority_obj.getId());
			request.setAttribute("scraprequest_list", list);
			Iterator it=list.iterator();
			while(it.hasNext()){
		
				System.out.println("List-----"+it.next());
			
			}
			
			if(!response.isCommitted())
			{
				
			request.getRequestDispatcher("jsp_pages/Authority/scrap_collection_request/scrap_collection_request_list.jsp").forward(request, response);
			}
			
			break;
			
		case "assigned_task":
							
						List<Task> task_list=new ArrayList<Task>();
						List<Task> assigned_Task=new ArrayList();
						TaskService task_service=new TaskServiceImpl();
						task_list=task_service.findAllTaskByStatus(0);
						List<ScrapCollectionRequest> request_list=new ArrayList<ScrapCollectionRequest>();
						ScrapCollectionRequestService requestService=new ScrapCollectionRequestServiceImpl();
						request_list=requestService.findScrapCollectionRequestByAuthorityStatus(authority.getId(), 1);
						
						for(Task tasks:task_list){
							
							for(ScrapCollectionRequest requests:request_list){
								
							if(tasks.getRequest().getId()==requests.getId()){
								
								assigned_Task.add(tasks);
								
							}
							
							}
							
						}
						request.setAttribute("assigned_task", assigned_Task);
						if(!response.isCommitted())
						{
							
						request.getRequestDispatcher("jsp_pages/Authority/Task/assigned_task_list.jsp").forward(request, response);
						}
								
			
								break;
			 
		case "completed_task":
			List<Task> task_lists=new ArrayList<Task>();
			List<Task> completed_Task=new ArrayList<Task>();
			TaskService task_services=new TaskServiceImpl();
			task_lists=task_services.findAllTaskByStatus(1);
			
			
			List<ScrapCollectionRequest> request_lists=new ArrayList<ScrapCollectionRequest>();
			ScrapCollectionRequestService requestServices=new ScrapCollectionRequestServiceImpl();
			request_lists=requestServices.findScrapCollectionRequestByAuthorityStatus(authority.getId(), 1);
			
			
			
			for(Task tasks:task_lists){
				System.out.println("Task******************"+tasks);
				
				for(ScrapCollectionRequest requests:request_lists){
					System.out.println("Req******************"+requests);
					
				if(tasks.getRequest().getId()==requests.getId()){
					
					completed_Task.add(tasks);
					System.out.println("Task list"+tasks);
					
				}
				
				}
				
			}
			
			
			
			
			request.setAttribute("completed_task", completed_Task);
			if(!response.isCommitted())
			{
				
			request.getRequestDispatcher("jsp_pages/Authority/Task/completed_task_list.jsp").forward(request, response);
			}
					

					break;
			
		case "complaints":
			
				ScrapCollectionRequestService collection_request=new ScrapCollectionRequestServiceImpl();
				List<ScrapCollectionRequest> requestList=collection_request.findAllScrapCollectionRequestByAuthority(authority.getId());
				ComplaintService complaint_service= new ComplaintServiceImpl();
				List<Complaint> complaints=complaint_service.findAllComplaint();
				List<Complaint> complaint_list=new ArrayList<Complaint>();
				
				for(Complaint complaint:complaints){
					
					for(ScrapCollectionRequest requests:requestList){
						
						if(complaint.getRequest().getId()==requests.getId()){
							complaint_list.add(complaint);
						}
						
						
					}
				}
				
				request.setAttribute("complaint", complaint_list);
				
				if(!response.isCommitted())
				{
					
				request.getRequestDispatcher("jsp_pages/Authority/complaint/complaint.jsp").forward(request, response);
				}
				
			
				break;
				
				
		case "feedbacks":
							FeedBackService feedback_service=new FeedBackServiceImpl();
							List<FeedBack> feedback_list=feedback_service.findAllFeedBack();
							request.setAttribute("feedback",feedback_list);
							if(!response.isCommitted())
							{
								
							request.getRequestDispatcher("jsp_pages/Authority/Feedback/feedback.jsp").forward(request, response);
							}
							break;
							
		case "delete_feedback":
								
							FeedBackService feedback_services=new FeedBackServiceImpl();
							String id=request.getParameter("id");	
							feedback_services.deleteFeedBack(Integer.parseInt(id));
							FeedBackService feedback_servic=new FeedBackServiceImpl();
							List<FeedBack> feedback_lists=feedback_servic.findAllFeedBack();
							request.setAttribute("feedback",feedback_lists);
							if(!response.isCommitted())
							{
								
							request.getRequestDispatcher("jsp_pages/Authority/Feedback/feedback.jsp").forward(request, response);
							}
									
			
									break;
		case "delete_complaint":
							String cid=request.getParameter("id");
			ScrapCollectionRequestService collection_requests=new ScrapCollectionRequestServiceImpl();
			List<ScrapCollectionRequest> requestLists=collection_requests.findAllScrapCollectionRequestByAuthority(authority.getId());
			ComplaintService complaint_services= new ComplaintServiceImpl();
			complaint_services.deleteComplaint(Integer.parseInt(cid));
			
			List<Complaint> complaint=complaint_services.findAllComplaint();
			List<Complaint> complaint_lists=new ArrayList<Complaint>();
			
			for(Complaint complint:complaint){
				
				for(ScrapCollectionRequest requests:requestLists){
					
					if(complint.getRequest().getId()==requests.getId()){
						complaint_lists.add(complint);
					}
					
					
				}
			}
			
			request.setAttribute("complaint", complaint_lists);
			
			if(!response.isCommitted())
			{
				
			request.getRequestDispatcher("jsp_pages/Authority/complaint/complaint.jsp").forward(request, response);
			}
			
			
							
						break;
			

		default:
			System.out.println("An invalid option is chosen");
			response.sendRedirect("jsp_pages/404.jsp");
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
