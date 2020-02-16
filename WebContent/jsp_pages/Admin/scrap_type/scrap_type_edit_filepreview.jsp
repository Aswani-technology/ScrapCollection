<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../header/header.jsp"></jsp:include>
<jsp:include page="../nav/nav.jsp"></jsp:include>

 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
 
function readURL(input) {
	
	alert("heoo");
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
} 
 
</script>


<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Hours per Day'],
  ['Work', 8],
  ['Eat', 2],
  ['TV', 4],
  ['Gym', 2],
  ['Sleep', 8]
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'My Average Day', 'width':550, 'height':400};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>
</head>
<body>

<div id="piechart"></div>
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
		<div class="col-md-6">
			<div class="tile">
				<h3 class="tile-title">Register</h3>
				<div class="tile-body">
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="control-label col-md-3">Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text"
									placeholder="Enter full name">
							</div>
						</div>
						 
						<div class="form-group row">
							<label class="control-label col-md-3">Address</label>
							<div class="col-md-8">
								<textarea class="form-control" rows="4"
									placeholder="Enter your address"></textarea>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Gender</label>
							<div class="col-md-9">
								<div class="form-check">
									<label class="form-check-label"> <input
										class="form-check-input" type="radio" name="gender">Male
									</label>
								</div>
								<div class="form-check">
									<label class="form-check-label"> <input
										class="form-check-input" type="radio" name="gender">Female
									</label>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3">Identity Proof</label>
							<div class="col-md-8">
								<input class="form-control" type="file" onchange="readURL(this);"   >
								  <img id="blah" src="#" alt="your image"  width="400px;"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-8 col-md-offset-3">
								<div class="form-check">
									<label class="form-check-label"> <input
										class="form-check-input" type="checkbox">I accept the
										terms and conditions
									</label>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</main>


</body>
</html>