<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Scrap Collectors</title>
</head>
<body>

<main class="app-content">

      <div class="row">
      <h3>Scrap Collectors</h3>
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                  <tr>
                    <th>First Name</th>
                      <th>Last Name</th>
                     
                     <th>E-mail</th>
                      <th>Address</th>
                      <th>Contact</th>
                     
                     <th>City</th>
                      
                     
                    <th> Availability</th>
                  
                
                     
                  </tr>
                </thead>
                 <c:forEach var="list" items="${scrap_collector_list}">
							<tr>
								<td>${list.first_name}</td>
								<td>${list.last_name }</td>
								 <td>${list.email }</td>
								 <td>${list.address }</td>
								 <td>${list.contact }</td>
								   <td>${list.city }</td>
								  <td>${list.availability_status }</td>				
								 
								
								<%-- <td><a
									href="AuthorityControllerServlet?command=findAuthority&id=${list.id }"><button
											type="button" class="btn btn-outline-primary">Edit
											Authority</button></a></td>
								<td><a
									href="AuthorityControllerServlet?command=deleteAuthority&id=${list.id }">
										<button name="delete" class="btn btn-outline-danger">Delete</button>
								</a></td> --%>
								
								<%-- <td><a
									href="ScrapCollectorController?command=findScrapCollector&id=${list.id }"><button
											type="button" class="btn btn-outline-info">Edit
											</button></a></td>
								 --%>
								
								
							</tr>
						</c:forEach>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>