<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../nav/nav.jsp"></jsp:include>
<title>Add New Authority</title>
<script type="text/javascript">
function validate(){

	var pattern = new RegExp("^[0-9]{10}$");
	var password= /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

	 if (pattern.test(document.getElementById('contact').value) && password.test(document.getElementById('password').value)) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}


</script>



</head>
<body>
<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
				<i class="fa fa-edit"></i> 
			</h1>
			<p></p>
		</div>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"></li>
			<li class="breadcrumb-item"><a href="#"></a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-9">
			<div class="tile">
				<h3 class="tile-title">Add Authority  </h3>
				<div class="tile-body">
					<form class="form-horizontal" method="post"
						action="${pageContext.servletContext.contextPath}/AuthorityControllerServlet"  onsubmit="return validate()">
						 

						<div class="form-group row">
							<label class="control-label col-md-3">Authority Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter authority name" name="name">
							</div>
						</div>
						 

						<div class="form-group row">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter email address" name="email">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Username</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter email address" name="username">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter password" name="password">
							</div>
						</div>
						 

						 
						 
						
						<div class="form-group row">
							<label class="control-label col-md-3">City</label>
							<div class="col-md-8">
								<input class="form-control" type="text"   placeholder="Enter city"
									name="city">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Contact</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter contact" name="contact">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">District </label>
							<div class="col-md-8">
								<select class="form-control" id="district_select"
									name="district_select">

									 
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

						<button class="btn btn-primary btn-lg" type="submit">Add
							New</button>
						<input type="hidden" name="command" value="add_authority">
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