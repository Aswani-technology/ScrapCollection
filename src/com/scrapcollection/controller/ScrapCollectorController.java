package com.scrapcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
 

/**
 * Servlet implementation class ScrapCollectorController
 */
@WebServlet("/ScrapCollectorController")
public class ScrapCollectorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapCollectorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		

		int id = Integer.parseInt(request.getParameter("sid"));
		String command = request.getParameter("command");
		ScrapCollectorService service = new ScrapCollectorServiceImpl();
		
		switch (command) {
		case "findScrapCollector":
			ScrapCollector scrapCollector=service.findScrapCollectorById(id);
			 request.setAttribute("scrap_collector", scrapCollector);
			 System.out.println("scrap_collector "+scrapCollector);
			 
			  
			 if(!response.isCommitted())
				{
			 request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_view.jsp").forward(request, response);
				}
			break;
			
		case "deleteScrapCollector":
			service.deleteScrapCollector(id);
			System.out.println(".....................");
			List<ScrapCollector> scrapCollectorList=service.findAllScrapCollector();
			request.setAttribute("scrap_collector_list", scrapCollectorList);
			System.out.println("???????????  "+scrapCollectorList);
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_list.jsp").forward(request, response);
			}
			break;
			

		default:
			System.out.println("default command");
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/404.jsp").forward(request, response);
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String command = request.getParameter("command");
		ScrapCollectorService service = new ScrapCollectorServiceImpl();
		
		switch (command) {
		case "updateScrapCollector":
			ScrapCollector scrapCollector=readScrapCollector(request);
			boolean status=service.updateScrapCollector(scrapCollector);
			
			ScrapCollectorService sc_service = new ScrapCollectorServiceImpl();
			List<ScrapCollector> scrapCollectorList = sc_service.findAllScrapCollector();
			System.out.println("scrap collector list is " + scrapCollectorList);
			request.setAttribute("scrap_collector_list", scrapCollectorList);
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_list.jsp").forward(request, response);
			}
			break;

		default:
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/404.jsp").forward(request, response);
			}
			break;
		}
		 
		
		
	}
	
	public ScrapCollector readScrapCollector(HttpServletRequest request)
	{
		ScrapCollector scrapCollector=new ScrapCollector();
		scrapCollector.setId(Integer.parseInt(request.getParameter("id")));
		scrapCollector.setFirst_name(request.getParameter("fname"));
		scrapCollector.setLast_name(request.getParameter("lname"));
		scrapCollector.setEmail(request.getParameter("email"));
		scrapCollector.setPassword(request.getParameter("password"));
		scrapCollector.setAddress(request.getParameter("address"));
		scrapCollector.setContact(Long.parseLong(request.getParameter("contact")));
		scrapCollector.setCity(request.getParameter("city"));
		return scrapCollector;
	}

}
