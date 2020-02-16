<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Scrap Type</title>

<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

 <main class="app-content">
 <div class="row" style="margin-bottom:30px;">
 <div class="col-lg-10"> </div>
 <div class="col-lg-2">
       <a class="btn btn-primary icon-btn" href="${pageContext.servletContext.contextPath}/jsp_pages/Admin/scrap_type/scrap_type_add.jsp"><i class="fa fa-plus"></i>Add Scrap Type	</a>
       </div>
       </div>
       
    
       
      
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                  <tr>
                    <th>Name</th>
                     
                    <th>  </th>
                    <th> </th>
                     
                  </tr>
                </thead>
                 <c:forEach var="list" items="${scrap_type_list}">
							<tr>
								<td>${list.name}</td>
								 
								<td><a
									href="ScrapTypeController?command=findScrapType&id=${list.id }"><button
											type="button" class="btn btn-info btn-lg">Edit
											Scrap Type</button></a></td>
								<td><a
									href="ScrapTypeController?command=deleteScrapType&id=${list.id }">
										<button name="delete" class=" btn btn-lg btn-danger">Delete</button>
								</a></td>
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