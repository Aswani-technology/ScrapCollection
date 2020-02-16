package com.scrapcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;

/**
 * Servlet implementation class AuthorityControllerServlet
 */
@WebServlet("/AuthorityControllerServlet")
public class AuthorityControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorityControllerServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		String command = request.getParameter("command");
		AuthorityService service = new AuthorityServiceImpl();
		int id=Integer.parseInt(request.getParameter("id"));

		switch (command) {
		case "findAuthority":
			Authority authority=service.findAuthorityById(id);
			request.setAttribute("authority", authority);
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/Admin/authority/authority_view.jsp")
						.forward(request, response);
			}
			
			break;
			
		case "deleteAuthority":
			service.deleteAuthority(id);
			List<Authority> authorityList =  service.findAllAuthority();
			System.out.println("authoritylist is " + authorityList);
			request.setAttribute("authority_list", authorityList);
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/Admin/authority/authority_list.jsp")
						.forward(request, response);
			}
			break;
			
		default:
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/404.jsp").forward(request, response);
			}
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
	//	doGet(request, response);

		String command = request.getParameter("command");
		System.out.println("command is "+command);
		AuthorityService service = new AuthorityServiceImpl();

		switch (command) {
		case "add_authority":
			Authority authority = readAuthority(request);
			service.saveAuthority(authority);

			 
			List<Authority> authorityList =  service.findAllAuthority();
			System.out.println("authoritylist is " + authorityList);
			request.setAttribute("authority_list", authorityList);
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/Admin/authority/authority_list.jsp")
						.forward(request, response);
			}
			break;
			
		case "update_authority":
			
			Authority authority1=readUpdateDetails(request);
			service.updateAuthority(authority1);
			List<Authority> authorityList1 =  service.findAllAuthority();
			System.out.println("authoritylist is " + authorityList1);
			request.setAttribute("authority_list", authorityList1);
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/Admin/authority/authority_list.jsp")
						.forward(request, response);
			}
			
			break;
			
		
			

		default:
			if (!response.isCommitted()) {
				request.getRequestDispatcher("jsp_pages/404.jsp").forward(request, response);
			}
			break;
		}

	}

	public Authority readAuthority(HttpServletRequest request) {
		Authority authority = new Authority();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		Long contact = Long.parseLong(request.getParameter("contact"));
		String district = request.getParameter("district_select");
		authority = new Authority(0, name, username, password, email, city, district, contact);
		return authority;
	}
	
	public Authority readUpdateDetails(HttpServletRequest request)
	{
		Authority authority=new Authority();
		authority.setId(Integer.parseInt(request.getParameter("id")));
		authority.setName(request.getParameter("name"));
		authority.setEmail(request.getParameter("email"));
		authority.setPassword(request.getParameter("password"));
		authority.setUsername(request.getParameter("username"));
		authority.setCity(request.getParameter("city"));
		authority.setContact(Long.parseLong(request.getParameter("contact")));
		authority.setDistrict(request.getParameter("district_select"));
		return authority;
	}

}
