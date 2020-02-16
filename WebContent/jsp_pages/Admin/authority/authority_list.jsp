<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 


 
</head>
<body>

<main class="app-content">
 <div class="row" style="margin-bottom:30px;">
 <div class="col-lg-10"> </div>
 <div class="col-lg-2">
 <Form >
       <a class="btn btn-primary icon-btn" href="${pageContext.servletContext.contextPath}/jsp_pages/Admin/authority/authority_addnew.jsp"><i class="fa fa-plus"></i>Add Authority	</a>
       </div>
       </div>
       
    
       
      
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                  <tr>
                    <th>Authority Name</th>
                      
                     
                     <th>E-mail</th>
                      
                     
                     <th>City</th>
                     <th>District</th>
                     <th>Contact</th>
                    <th>  </th>
                    <th> </th>
                     
                  </tr>
                </thead>
                 <c:forEach var="list" items="${authority_list}">
							<tr>
								<td>${list.name}</td>
								 <td>${list.email }</td>
								   <td>${list.city }</td>
								 <td>${list.district }</td>					
								 <td>${list.contact }</td>
								
								<td><a
									href="${pageContext.servletContext.contextPath}/AuthorityControllerServlet?command=findAuthority&id=${list.id }"><button
											type="button" class="btn btn-outline-primary">Edit
											Authority</button></a></td>
								<td><a
									href="${pageContext.servletContext.contextPath}/AuthorityControllerServlet?command=deleteAuthority&id=${list.id }">
										<button ntype="button"  name="delete" class="btn btn-outline-danger">Delete</button>
								</a></td>
							</tr>
						</c:forEach>
              </table>
            </div>
          </div>
        </div>
      </div>
      </form>
    </main>
</body>

<jsp:include page="../header/header.jsp"></jsp:include>
</html>