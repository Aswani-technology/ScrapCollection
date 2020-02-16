package com.scrapcollection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scrapcollection.domain.Authority;
import com.scrapcollection.service.AuthorityService;
import com.scrapcollection.service_impl.AuthorityServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String command=request.getParameter("command");
			if(command.equals("logout"))
			{
				HttpSession session=request.getSession();
				session.invalidate();
				response.sendRedirect("jsp_pages/login/login.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		
		String role=request.getParameter("role");
		System.out.println("role"+role);
		switch(role)
		{
		
		
		case "admin":
			String username = request.getParameter("username");
			String password=request.getParameter("password");
			if(username.equals("admin") && password.equals("password"))
			{
				if(!response.isCommitted())
				{
				request.getRequestDispatcher("jsp_pages/Admin/home/admin_home.jsp").forward(request, response);
			 
				}
				
			}
			else
			{
				if(!response.isCommitted())
				{
					System.out.println("towards login");
				request.getRequestDispatcher("jsp_pages/login/login.jsp").forward(request, response);
			 
				}
				
			}
			
		case "authority":
		 String username1=request.getParameter("username");
		 String password1=request.getParameter("password");
			 
			AuthorityService auth_service=new AuthorityServiceImpl();
			Authority authority=auth_service.login(username1, password1); 
			 
			  if(authority.getId() != 0)
				{
				  System.out.println("towards home");
				  request.setAttribute("authority", authority);
				  
				  HttpSession session=request.getSession();
				  session.setAttribute("authority", authority);
					if(!response.isCommitted())
					{
					request.getRequestDispatcher("jsp_pages/Authority/home/authority_home.jsp").forward(request, response);
				 
					}
				}
				
				else
				{
					if(!response.isCommitted())
					{
						System.out.println("towards login");
					request.getRequestDispatcher("jsp_pages/login/login.jsp").forward(request, response);
				 
					}
				}
			
			
			break;
			
	 
		default:
			break;
		}
	}

}
