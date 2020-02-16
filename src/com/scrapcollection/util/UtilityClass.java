package com.scrapcollection.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

import com.scrapcollection.domain.Authority;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.service.ScrapCollectorService;
import com.scrapcollection.service_impl.ScrapCollectorServiceImpl;
import com.scrapcollection.service_impl.ScrapTypeServiceImpl;

public class UtilityClass {

	private Iterator it = null;

	public ServletFileUpload setLocationAndSize(int maxMemSize, int maxFileSize) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("../Uploads/temp/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		return upload;
	}

	public ScrapCollector getFormFieldScrapCollector(ServletFileUpload upload, HttpServletRequest request)
			throws FileUploadException {

		ScrapCollector scrapCollector = new ScrapCollector();
		List fileItems = upload.parseRequest(request);
		System.out.println("FileItems=" + fileItems);
		Iterator i = fileItems.iterator();
		it = fileItems.iterator();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			// System.out.println(fi.getFieldName());
			System.out.println("current loop value ::::" + fi.getString());
			if (fi.isFormField()) {
				String fieldName = fi.getFieldName();
				// HttpSession session = request.getSession();
				if (fieldName.equals("fname")) {

					scrapCollector.setFirst_name(fi.getString());
				}
				if (fieldName.equals("lname")) {

					scrapCollector.setLast_name(fi.getString());
				}
				if (fieldName.equals("gender")) {
					scrapCollector.setGender(fi.getString());
				}
				
				if(fieldName.equals("email"))
				{
					scrapCollector.setEmail(fi.getString());
				}
				if(fieldName.equals("password"))
				{
					scrapCollector.setPassword(fi.getString());
				}
				if (fieldName.equals("date")) {
					/*LocalDate localDate = StringToLocalDateConverter.convert(fi.getString());
					scrapCollector.setDob(localDate);*/
					
					scrapCollector.setDob(fi.getString());
				}
				if (fieldName.equals("address")) {
					scrapCollector.setAddress(fi.getString());
				}

				if (fieldName.equals("contact")) {

					scrapCollector.setContact(Long.parseLong(fi.getString()));
				}

				if (fieldName.equals("city")) {
					scrapCollector.setCity(fi.getString());
				}
				if (fieldName.equals("authority_select")) {
					Authority authority=new Authority();
					authority.setId(Integer.parseInt(fi.getString()));
					scrapCollector.setAuthority_id(authority);
				}
				 
				 

			}
		}

		return scrapCollector;
	}

	 
	public void getNonFormField(ServletFileUpload upload, HttpServletRequest request, HttpServletResponse response,
			String imageName, String UPLOAD_DIRECTORY) throws FileUploadException, Exception {
		File file;
		List fileItems = upload.parseRequest(request);
		System.out.println("nonForm FileItems=" + fileItems);
		String fileName = null;
		while (it.hasNext()) {
			FileItem fi = (FileItem) it.next();
			System.out.println(fi.getFieldName());

			if (!fi.isFormField()) {
				String fieldName = fi.getFieldName();

				fileName = imageName + ".jpg";
				String data = fi.getString();

				if (fileName.lastIndexOf("\\") >= 0) {
					file = new File(UPLOAD_DIRECTORY + fileName.substring(fileName.lastIndexOf("\\")));
				} else {
					file = new File(UPLOAD_DIRECTORY + fileName.substring(fileName.lastIndexOf("\\") + 1));
				}

				fi.write(file);

			}

		}
		System.err.println("it is here now" + fileName);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet upload</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Insertion Successful</p><a href='/HotelMC/pages/menu/category.jsp'>Add Category</a>");
		out.println("</body>");
		out.println("</html>");
	}

	public void isMultipart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println(
					"<p>Uploading Failed</p><a href='/Mobile_Attendance_System/jsp_pages/header/header_file.jsp'>Home</a>");
			out.println("</body>");
			out.println("</html>");
			return;
		}
	}

	public void InsertionFailedResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet upload</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Insertion Failed</p><a href='/HotelMC/pages/menu/category.jsp'>Add Category</a>");
		out.println("</body>");
		out.println("</html>");
		return;

	}

	public void uploadImage(HttpServletRequest request, HttpServletResponse response, int maxMemSize, int maxFileSize,
			Object object, String UPLOAD_DIRECTORY) throws IOException, ServletException {

		if (object instanceof ScrapCollector) {
			ScrapCollector scrapCollector = (ScrapCollector) object;
			isMultipart(request, response);

			ServletFileUpload upload = setLocationAndSize(maxMemSize, maxFileSize);
			HttpServletRequest request_non_form_field = request;
			ScrapCollectorService service=new ScrapCollectorServiceImpl();
			try {
				scrapCollector = getFormFieldScrapCollector(upload, request);

				int scrapCollectorid = service.saveScrapCollector(scrapCollector);
				String imageName = scrapCollectorid + "";
				getNonFormField(upload, request_non_form_field, response, imageName, UPLOAD_DIRECTORY);

			} catch (Exception e) {
				e.printStackTrace();
			}

			List<ScrapCollector> scrapCollector_list = service.findAllScrapCollector();
			request.setAttribute("scrapcollector_list", scrapCollector_list);
		//	request.getRequestDispatcher("pages/admin/employee/employee_list.jsp").forward(request, response);

		}
 

	}

}
