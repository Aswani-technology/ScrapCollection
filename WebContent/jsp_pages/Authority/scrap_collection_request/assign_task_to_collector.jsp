<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Assign</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
			
			</h1>
			<p></p>
		</div>
		<ul class="app-breadcrumb breadcrumb">
		
		</ul>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Assign Task To Collector</h3>
				<div class="tile-body">
					<form class="form-horizontal" method="post" action="${pageContext.servletContext.contextPath}/ScrapCollectionRequestController" >
						<div class="form-group row">
							<label class="control-label col-md-3">Request Id</label>
							<div class="col-md-8">
								<input class="form-control" type="text" name="request_Id" readonly value="${scraprequest.id }">
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">User Details</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									readonly name="name" value="${scraprequest.user.first_name } ${scraprequest.user.last_name }" >
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Date</label>

							<div class="col-md-8">

								<input class="form-control" id="demoDate" type="text"
									name="date" placeholder="Select Date">
							</div>
							</div>
						</div>

					

					
						<div class="form-group row">
							<label class="control-label col-md-3">Available Collectors </label>
							<div class="col-md-8">
								<select class="form-control" id="collectors"
									name="collectors">

									<c:forEach var="list" items="${Collectors}">
										<option value="${list.id }">${list.first_name }${list.last_name }</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<button class="btn btn-primary btn-lg" type="submit">Assign Task
				</button>
						
						<input type="hidden" name="command" value="assign_task">
					</form>
				</div>
			</div>



		</div>
	</div>
	</div>
	</div>
	</main>
</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>