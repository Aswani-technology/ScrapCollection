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
			<li class="breadcrumb-item"><a href="#">Edit Scrap Collector</a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Edit Scrap Collector</h3>
				<div class="tile-body">
					<form class="form-horizontal" method="post"
						action="${pageContext.servletContext.contextPath}/ScrapCollectorController">
						<div class="form-group row">
							<label class="control-label col-md-3">First Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="fname" value="${scrap_collector.first_name }">
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-3">Last Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="lname" value="${scrap_collector.last_name }">
							</div>
						</div>
						 

						<div class="form-group row">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-8">
								<input class="form-control" type="email"
									  name="email" value="${scrap_collector.email }">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="control-label col-md-3">Password</label>
							<div class="col-md-8">
								<input class="form-control" type="password"
									 name="password" value="${ scrap_collector.password}" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
							</div>
						</div>
						 
 
						<div class="form-group row">
							<label class="control-label col-md-3">Address</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="address" value="${ scrap_collector.address}">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Contact</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									  name="contact" value="${ scrap_collector.contact}" pattern="^[0-9]{10}$" title="Must contain 10 digit Number">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">City</label>
							<div class="col-md-8">
								<input class="form-control" type="text" 
									name="city" value="${ scrap_collector.city}">
							</div>
						</div>
						<%-- <div class="form-group row">
							<label class="control-label col-md-3">Authority </label>
							<div class="col-md-8">
								<select class="form-control" id="authority_select"
									name="authority_select">

									<c:forEach var="list" items="${authority_list}">
										<option value="${list.id }">${list.city }</option>
									</c:forEach>
								</select>
							</div>
						</div> --%>
<div class="showback">
						<button class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal" type="button">Update Data
							</button>
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                    
                      <h4 class="modal-title" id="myModalLabel">Update</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" class="fa fa-angle-right">&times;</button>
                    </div>
                    <div class="modal-body">
                      Do You Want to Proceed
                    </div>
                    <div class="modal-footer">
                    <button  class="btn btn-primary" type="submit">Update changes</button>
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      
                    </div>
                  </div>
                </div>
              </div>
            </div>
						<input type="hidden" name="command" value="updateScrapCollector">
						<input type="hidden" name="id" value="${ scrap_collector.id}">
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