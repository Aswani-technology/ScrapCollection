<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../header/header.jsp"></jsp:include>
<jsp:include page="../nav/nav.jsp"></jsp:include>

<!-- <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script> -->
<script>
	function readURL(input) {

		alert("heoo");
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(150).height(200);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
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
		<div class="col-lg-2"></div>
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Register</h3>
				<div class="tile-body">
					<form class="form-horizontal" action="ScrapTypeController" method="post">
						<div class="form-group row">
							<label class="control-label col-md-3"> Scrap Type Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text" name="name"
									placeholder="Enter scrap type name..."
									value="${scrap_type.name }">
							</div>
						</div>

						 

						<div class="bs-component">
							<button class="btn btn-primary btn-lg" type="submit">Update</button>
							
							<input type="hidden" name="command" value="update_scrap_type">
							<input type="hidden" name="id" value="${scrap_type.id }">
					</form>
				</div>
			</div>
		</div>
	</div>
	</main>


</body>
</html>