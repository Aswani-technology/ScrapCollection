 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ConfirmDelete()
{
  var x = confirm("Are you sure you want to delete?");
  if (x)
      return true;
  else
    return false;
}
</script>

</head>
<body>

 <main class="app-content">
 <div class="row" style="margin-bottom:30px;">
 <div class="col-lg-10"> </div>
 <div class="col-lg-2">
 <Form >
       <a class="btn btn-primary icon-btn" href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=add_scrapcollector"><i class="fa fa-plus"></i>Add Collector	</a>
       </div>
       </div>
       
    
       
      
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" >
                <thead>
                  <tr>
                    <th>First Name</th>
                     <th>Second Name</th>
                     <th>DOB</th>
                     <th>E-mail</th>
                     <th>Address</th>
                     <th>Contact</th>
                     <th>City</th>
                    <th>  </th>
                    <th> </th>
                     
                  </tr>
                </thead>
                 <c:forEach var="list" items="${scrap_collector_list}">
							<tr>
								<td>${list.first_name}</td>
								 <td>${list.last_name }</td>
								 <td>${list.dob }</td>
								 <td>${list.email }</td>
								 <td>${list.address }</td>
								 <td>${list.contact }</td>
								 <td>${list.city }</td>
								<td><a
									href="${pageContext.servletContext.contextPath}/ScrapCollectorController?command=findScrapCollector&sid=${list.id}"><button
											type="button" class="btn btn-outline-primary">Edit
											Scrap Collector</button></a></td>
								<td><a
									href="${pageContext.servletContext.contextPath}/ScrapCollectorController?command=deleteScrapCollector&sid=${list.id}" onclick="return ConfirmDelete();">
										<button type="button" name="delete" class="btn btn-outline-danger">Delete</button>
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