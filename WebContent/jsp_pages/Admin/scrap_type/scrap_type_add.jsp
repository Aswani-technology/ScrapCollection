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
          <h1><i class="fa fa-edit"></i> </h1>
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
            <h3 class="tile-title">Add Scrap Type</h3>
            <div class="tile-body">
              <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/ScrapTypeController" method="post">
                <div class="form-group row">
                  <label class="control-label col-md-3">Name</label>
                  <div class="col-md-8">
                    <input class="form-control" type="text" placeholder="Enter scrap type name" name="name">
                  </div>
                </div>
                <input type="hidden" name="command" value="add_scrap_type">
                <button class="btn btn-primary btn-lg" type="submit">Add New</button>
                
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