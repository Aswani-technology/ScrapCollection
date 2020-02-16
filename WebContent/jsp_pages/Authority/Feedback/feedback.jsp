<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complaints</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1></h1>

	<main class="app-content">
	<div class="row">

<c:forEach var="list" items="${feedback}">
		
			<div class="col-lg-1"></div>
			<div class="col-md-8">
				<div class="tile">
					<div class="tile-title-w-btn">
						<h3 class="title">FeedBack ID : ${list.id}</h3>
						<div class="btn-group">
							
							
							<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=delete_feedback&id=${list.id }"><i class="fa fa-lg fa-trash"></i></a>
						</div>
					</div>
					<div class="tile-body">
					
					
					<b>User: ${list.user.first_name}${list.user.last_name}</b><br>
						
								
						<b>FeedBack</b> ${list.description }<br>
						
						
					
						
						

					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</c:forEach>
	</div>


	</main>
</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>