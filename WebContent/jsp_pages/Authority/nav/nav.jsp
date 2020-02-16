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
 
 <body class="app sidebar-mini rtl">
    <!-- Navbar-->
    <header class="app-header"><a class="app-header__logo" href="index.html">Authority</a>
      <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <!-- Navbar Right Menu-->
      <ul class="app-nav">
        <li class="app-search">
          <input class="app-search__input" type="search" placeholder="Search">
          <button class="app-search__button"><i class="fa fa-search"></i></button>
        </li>
       
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
             
          
            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/LoginController?command=logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">Authority</p>
          <p class="app-sidebar__user-designation">${authority.name }</p>
        </div>
      </div>
      <ul class="app-menu">
     
   <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=profile"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">PROFILE</span></a></li>
       <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=scrapcollector"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">SCRAP COLLECTORS</span></a></li>
        <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=scrapcollection_request"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">COLLECTION REQUESTS</span></a></li>
        <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=assigned_task"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">ASSIGNED TASKS</span></a></li>
        <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=completed_task"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">COMPLETED TASKS</span></a></li>
         <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=complaints"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">COMPLAINTS</span></a></li>
          <li><a class="app-menu__item" href="${pageContext.servletContext.contextPath}/PageControllerServlet_Authority?page=feedbacks"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">FEEDBACKS</span></a></li>
        
      </ul>
    </aside>
</body>
</html>