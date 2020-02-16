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
	<h1>Collection Request List</h1>

	<main class="app-content">
	<div class="row">


		<c:forEach var="list" items="${scraprequest_list}">
			<div class="col-lg-1"></div>
			<div class="col-md-8">
				<div class="tile">
					<div class="tile-title-w-btn">
						<h3 class="title">Request ID : ${list.id }</h3>
						<div class="btn-group">
							
							<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/ScrapCollectionRequestController?command=task&id=${list.id}"><i class="fa fa-lg fa-edit"></i>Assign Task</a>
						
						</div>
					</div>
					<div class="tile-body">
						<b>User Details </b><br> ${list.user.first_name  }  ${list.user.last_name} <br>
						
						<b>${list.date}</b>
						
						<br>
						<b>Scrap Type List</b>
						<c:forEach var="lists" items="${list.scrapRequestList}">
						<b>${lists.scraptype.name}</b>
						<b>${lists.weight}</b>
						<br>
						
						</c:forEach>

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