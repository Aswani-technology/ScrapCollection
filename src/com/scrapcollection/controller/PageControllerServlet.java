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
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service.ScrapTypeService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;

/**
 * Servlet implementation class PageControllerServlet
 */
@WebServlet("/PageControllerServlet")
public class PageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		

		String next_page = request.getParameter("page");
		System.out.println("next page is ====  " + next_page);

		switch (next_page) {
		case "scraptype":

			ScrapTypeService service = new ScrapTypeServiceImpl();
			List<ScrapType> scrapTypeList = service.findAllScrapType();
			System.out.println("scrap type list is " + scrapTypeList);
			request.setAttribute("scrap_type_list", scrapTypeList);
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Admin/scrap_type/scrap_type_list.jsp").forward(request, response);
			}
			break;

		case "scrapcollector":

			ScrapCollectorService sc_service = new ScrapCollectorServiceImpl();
			List<ScrapCollector> scrapCollectorList = sc_service.findAllScrapCollector();
			System.out.println("scrap collector list is " + scrapCollectorList);
			request.setAttribute("scrap_collector_list", scrapCollectorList);
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_list.jsp").forward(request, response);
			}
			break;
		case "add":
			if(!response.isCommitted())
			{
			request.getRequestDispatcher("pages/admin/book_type/add.jsp").forward(request, response);
			}
			break;
		case "add_scrapcollector":
			
			AuthorityService auth_service=new AuthorityServiceImpl();
			List<Authority> authorityList=auth_service.findAllAuthority();
			request.setAttribute("authority_list", authorityList);
			System.out.println("/////////   "+authorityList);
			
			if(!response.isCommitted())
			{
				request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_add.jsp").forward(request, response);
		 
			}
			break;
			
		case "authority":
			AuthorityService aut_service=new AuthorityServiceImpl();
			List<Authority> authorityList1=aut_service.findAllAuthority();
			request.setAttribute("authority_list", authorityList1);
			if(!response.isCommitted())
			{
				request.getRequestDispatcher("jsp_pages/Admin/authority/authority_list.jsp").forward(request, response);
		 
			}
			break;
			
		case "logout":
			HttpSession session=request.getSession();
			session.invalidate();
			if(!response.isCommitted())
			{
				request.getRequestDispatcher("jsp_pages/login/login.jsp").forward(request, response);
				
		 
			}
			break;
			
			
			

		default:
			System.out.println("An ivalid option is chosen");
			response.sendRedirect("jsp_pages/404.jsp");
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
