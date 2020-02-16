package com.scrapcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;
 

/**
 * Servlet implementation class ScrapTypeController
 */
@WebServlet("/ScrapTypeController")
public class ScrapTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapTypeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		int id = Integer.parseInt(request.getParameter("id"));
		String command = request.getParameter("command");
		ScrapTypeService service = new ScrapTypeServiceImpl();
		
		switch (command) {
		case "findScrapType":
			ScrapType scrapType=service.findScrapTypeById(id);
			 request.setAttribute("scrap_type", scrapType);
			 System.out.println("scrao type is "+scrapType);
			 request.getRequestDispatcher("jsp_pages/Admin/scrap_type/scrap_type_edit.jsp").forward(request, response);
			 
			break;
			
		case "deleteScrapType":
			service.deleteScrapType(id);
			List<ScrapType> scrapTypeList=service.findAllScrapType();
			request.setAttribute("scrap_type_list", scrapTypeList);
			request.getRequestDispatcher("jsp_pages/Admin/scrap_type/scrap_type_list.jsp").forward(request, response);
			break;
			

		default:
			System.out.println("default command");
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		
		
		ScrapTypeService service = new ScrapTypeServiceImpl();
		System.out.println("111111111111111111111111");
		String command = request.getParameter("command");
		
		System.out.println("command is /////  "+command);

		switch (command) {
		case "add_scrap_type":
			System.out.println("prior to add scrap type");
			ScrapType scrapType = readScrapType(request);
			service.saveBookType(scrapType);
			 
			List<ScrapType> scrapTypeList=service.findAllScrapType();
			 
			request.setAttribute("scrap_type_list", scrapTypeList);
			request.getRequestDispatcher("jsp_pages/Admin/scrap_type/scrap_type_list.jsp").forward(request, response);
			break;

		case "update_scrap_type":
			int id = Integer.parseInt(request.getParameter("id"));
			ScrapType scrapType2 = readScrapType(request);
			scrapType2.setId(id);
			service.updateScrapType(scrapType2);
			List<ScrapType> scrapTypeList2=service.findAllScrapType();
			request.setAttribute("scrap_type_list", scrapTypeList2);
			request.getRequestDispatcher("jsp_pages/Admin/scrap_type/scrap_type_list.jsp").forward(request, response);
			break;

		default:
			System.out.println("default command");
			break;
			
		}
		
	 
		
		
	}
	
	public ScrapType readScrapType(HttpServletRequest request) {
		ScrapType scrapType = new ScrapType();
		scrapType.setName(request.getParameter("name"));

		return scrapType;

	}


	}


