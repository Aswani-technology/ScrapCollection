<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assigned Task List</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1>Collection Request List</h1>

	<main class="app-content">
	<div class="row">


		<c:forEach var="list" items="${assigned_task}">
			<div class="col-lg-1"></div>
			<div class="col-md-8">
				<div class="tile">
					<div class="tile-title-w-btn">
						<h3 class="title">Task ID : ${list.id }</h3>
						<div class="btn-group">
							
							
							
						</div>
					</div>
					<div class="tile-body">
					<b>Request ID : ${list.request.id}</b><br>
						
								
						<b>Assigned Date : ${list.date}</b>
						
						
					
						
						

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