<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scrap Collector</title>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
            $('#form').submit(function(e) {
                $('#messages').removeClass('hide').addClass('alert alert-success alert-dismissible').slideDown().show();
                $('#messages_content').html('<h4>MESSAGE HERE</h4>');
                $('#modal').modal('show');
                e.preventDefault();
            });
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
			<li class="breadcrumb-item">Forms</li>
			<li class="breadcrumb-item"><a href="#"></a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Add Scrap Collector</h3>
				<div class="tile-body">
					<form class="form-horizontal" method="post"
						action="${pageContext.servletContext.contextPath}/FileUploadServlet?image=scrapcollector_image"
						enctype="multipart/form-data"  >
						<div class="form-group row">
							<label class="control-label col-md-3">First Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter first name" name="fname" pattern="^[A-Za-z -]+$"  required>
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">Last Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter last name" name="lname" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Gender</label>

							<div class=" col-md-8 animated-radio-button">
								<label> <input type="radio" name="gender" value="male"><span
									class="label-text">Male</span>
								</label> <label> <input type="radio" name="gender"
									value="female"><span class="label-text">Female</span>
								</label>
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-8">
								<input class="form-control" type="email"
									placeholder="Enter email address" name="email" required>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-8">
								<input class="form-control" type="password"
									placeholder="Enter password" name="password" id="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Date of birth</label>
							<div class="col-md-8">

								<input class="form-control" id="demoDate" type="text"
									name="date" placeholder="Select Date">
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">Choose an image</label>
							<div class="col-md-8">

								<input class="form-control" id="demoDate" type="file"
									name="image" name="date" placeholder="Select Date">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Address</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter address" name="address" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Contact</label>
							<div class="col-md-8">
								<input class="form-control" type="number"
									placeholder="Enter contact" name="contact" id="contact" pattern="^[0-9]{10}$" title="Must contain 10 digit Number" required maxlength="10">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">City</label>
							<div class="col-md-8">
								<input class="form-control" type="text" placeholder="Enter city"
									name="city" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Authority </label>
							<div class="col-md-8">
								<select class="form-control" id="authority_select"
									name="authority_select">

									<c:forEach var="list" items="${authority_list}">
										<option value="${list.id }">${list.city }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					 <div class="showback">
						<button class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal" type="button">Add	New</button>
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      
                      <h4 class="modal-title" id="myModalLabel">Save</h4>
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                      Do You Want to Proceed
                    </div>
                    <div class="modal-footer">
                     <button  class="btn btn-primary" type="submit">Save changes</button>
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                     
                    </div>
                  </div>
                </div>
              </div>
            </div>
						<input type="hidden" name="command" value="add_scrap_type">
						
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