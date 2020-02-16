package com.scrapcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.util.Constants;
import com.scrapcollection.util.UtilityClass;

 
/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String image = request.getParameter("image");

		int maxFileSize = 8000 * 1024;
		int maxMemSize = 8000 * 1024;

		if (image.equals("scrapcollector_image")) {
			String UPLOAD_DIRECTORY = Constants.SCRAPCOLLECTOR_PROFILE_FILE_PATH;

			ScrapCollector scrapCollector=new ScrapCollector();

			new UtilityClass().uploadImage(request, response, maxMemSize, maxFileSize, scrapCollector, UPLOAD_DIRECTORY);
			
			ScrapCollectorService sc_service = new ScrapCollectorServiceImpl();
			List<ScrapCollector> scrapCollectorList = sc_service.findAllScrapCollector();
			System.out.println("scrap collector list is " + scrapCollectorList);
			request.setAttribute("scrap_collector_list", scrapCollectorList);
			
			if (!response.isCommitted()) {
			request.getRequestDispatcher("jsp_pages/Admin/scrap_collector/scrap_collector_list.jsp").forward(request, response);
			return;
			}

		}

		 

	}

}
