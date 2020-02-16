<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
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
	
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Profile</h3>
				<div class="tile-body">
					
						<div class="form-group row">
							<label class="control-label col-md-3">Authority Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									read only name="name" value="${Authority.name}">
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">City</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									 name="city" value="${Authority.city}">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">District</label>
								<div class="col-md-8">
								<input class="form-control" type="text"
									 name="district" readonly value="${Authority.district }">
							</div>
							
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">Contact</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									 name="contact" readonly value="${Authority.contact}">
							</div>
						</div>
						
						
				</div>
			</div>



		</div>

	</main>
</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>