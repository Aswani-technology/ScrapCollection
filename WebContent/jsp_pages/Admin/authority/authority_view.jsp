<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
</head>
<body>
<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
				<i class="fa fa-edit"></i> Form Samples
			</h1>
			<p>Sample forms</p>
		</div>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item">Forms</li>
			<li class="breadcrumb-item"><a href="#">Sample Forms</a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-9">
			<div class="tile">
				<h3 class="tile-title">Add Authority  </h3>
				<div class="tile-body">
					<form class="form-horizontal" method="post"
						action="${pageContext.servletContext.contextPath}/AuthorityControllerServlet">
						 

						<div class="form-group row">
							<label class="control-label col-md-3">Authority Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="name" value="${authority.name }">
							</div>
						</div>
						 

						<div class="form-group row">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="email" value="${authority.email }">
							</div>
						</div>
						
					 
						 

						 
						 
						
						<div class="form-group row">
							<label class="control-label col-md-3">City</label>
							<div class="col-md-8">
								<input class="form-control" type="text"   
									name="city" value="${authority.city }">
							</div>
						</div>
						
						
						<div class="form-group row">
							<label class="control-label col-md-3">Username</label>
							<div class="col-md-8">
								<input class="form-control" type="text"   
									name="username" value="${authority.username }">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-8">
								<input class="form-control" type="text"   
									name="email" value="${authority.email }">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-8">
								<input class="form-control" type="text"   
									name="password" value="${authority.password }">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Contact</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									 name="contact" value="${authority.contact }">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">District </label>
							<div class="col-md-8">
								<select class="form-control" id="district_select"
									name="district_select">

									 <option value="${authority.district }" selected>${authority.district }</option>
										<option value="Alapuzha">Alapuzha</option>
										<option value="Ernakulam">Ernakulam</option>
										<option value="Idukki">Idukki</option>
										<option value="Kannur">Kannur</option>
										<option value="Kasaragode">Kasaragode</option>
										<option value="Kollam">Kollam</option>
										<option value="Kottayam">Kottayam</option>
										<option value="Kozhikode">Kozhikode</option>
										<option value="Malappuram">Malappuram</option>
										<option value="Palakkad">Palakkad</option>
										<option value="PathanamThitta">PathanamThitta</option>
										<option value="Thiruvananthapuram">Thiruvananthapuram</option>
										<option value="Thrissur">Thrissur</option>
										<option value="Wayanad">Wayanad</option>
										
									 
								</select>
							</div>
						</div>

						<button class="btn btn-primary btn-lg" type="submit"> Update Details
							 </button>
						<input type="hidden" name="command" value="update_authority">
						<input type="hidden" name="id" value="${authority.id }">
					</form>
				</div>
			</div>



		</div>
	</div>
	 
	</main>
</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>