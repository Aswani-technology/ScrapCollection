<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
</head>
<body>

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
 
    <script src="${pageContext.servletContext.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="${pageContext.servletContext.contextPath}/assets/js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
    <!-- Data table plugin-->
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#sampleTable').DataTable();</script>
    <!-- Google analytics script-->
    <script type="text/javascript">
      if(document.location.hostname == 'pratikborsadiya.in') {
      	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      	ga('create', 'UA-72504830-1', 'auto');
      	ga('send', 'pageview');
      }
    </script>
    
    
    
      <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/plugins/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/plugins/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/plugins/bootstrap-datepicker.min.js"></script>
     <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/lib/form-validation-script.js"></script>
    
     <script type="text/javascript">
      $('#sl').click(function(){
      	$('#tl').loadingBtn();
      	$('#tb').loadingBtn({ text : "Signing In"});
      });
      
      $('#el').click(function(){
      	$('#tl').loadingBtnComplete();
      	$('#tb').loadingBtnComplete({ html : "Sign In"});
      });
      
      $('#demoDate').datepicker({
      	format: "dd/mm/yyyy",
      	autoclose: true,
      	todayHighlight: true
      });
      
      $('#demoSelect').select2();
    </script>
</body>
</html>