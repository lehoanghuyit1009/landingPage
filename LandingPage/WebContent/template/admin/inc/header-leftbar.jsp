<%@page import="util.AuthUtil"%>
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>landingpage.com - admin</title>
    <!-- Favicon-->
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/template/public/img/favicon.ico">
    <!-- Bootstrap core CSS-->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/css/style.css"  type="text/css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css"  type="text/css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.css"  type="text/css" rel="stylesheet">
	
    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/css/sb-admin.css"  type="text/css" rel="stylesheet">
  </head>
  <body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top" style="display: block;">
      <a class="navbar-brand mr-1" href="<%=request.getContextPath()%>/admin">landing.com</a>

      <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle">
        <i class="fas fa-bars"></i>
      </button>

      <!-- Navbar -->
	      <ul class="navbar-nav float-right ml-md-0">
	        <%
	        	User userLogin = AuthUtil.getUserLogin(request);
	        	if (userLogin != null) {
	        %>
	        <li class="nav-item dropdown no-arrow user-logo">
	          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	            <i class="fas fa-user-circle fa-fw"></i>
	          </a>
	          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
	            <a class="dropdown-item text-dark" href="" style="pointer-events: none;"><%if(userLogin != null)out.print(userLogin.getFullname());%></a>
	            <!-- ajax when click and chang html ex: Login 10 hours ago -->
	            <a class="dropdown-item text-secondary small" id="how-long-ago" href="" style="pointer-events: none;"></a>
	            <div class="dropdown-divider"></div>
		            <a class="dropdown-item text-primary" href="<%=request.getContextPath()%>/logout">Logout
		            	<i class="fas fa-sign-out-alt"></i>
		            </a>
		             <a class="dropdown-item text-primary" href="<%=request.getContextPath()%>/">Trang chủ
		            	<i class="fas fa-home"></i>
		            </a>
	          </div>
	        </li>
	       <!--  dong ngoac ơ tren -->
	       <%
	        	}
	        %>
	      </ul>
    </nav>
    <div id="wrapper">
	  <%
	  	/* Phân quyền user và hiển thị thanh left_bar */
	  	boolean accessDashboard = true;
	  	boolean accessCategories = true;
	  	boolean accessNewses = true;
	  	boolean accessUsers = true;
	  	boolean accessSlides = true;
	  	boolean accessContacts = true;
	  	
	  	if (userLogin != null) {
	  		switch (userLogin.getRole().getName()) {
	  		case "admin": 
	  			break;
	  		case "editor":
	  			accessUsers = true;
	  			accessCategories = false;
	  			accessSlides = false;
	  			accessContacts = false;
	  			break;
	  		case "user":
	  			accessUsers = false;
	  			accessCategories = false;
	  			accessDashboard = false;
	  			accessNewses = false;
	  			accessSlides = false;
	  			accessContacts = false;
	  			break;
	  		default:
	  			break;
	  		}
	  	}
	  %>
	  
      <!-- Sidebar -->
      <ul class="sidebar navbar-nav">
      	<% 
      		if (accessDashboard) { 
      	%>
        <li class="nav-item" id="dashboard">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </a>
        </li>
       <% 
       		}
        %>
        <% 
        	if (accessCategories) { 
        %>
        <li class="nav-item" id="category">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/category/index">
            <i class="fas fa-sitemap"></i>
            <span>Category</span></a>
        </li>
       <% 
       		} 
       %>
        <% 
        	if (accessNewses) { 
        %>
        <li class="nav-item" id="news">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/news/index">
            <i class="far fa-newspaper"></i>
            <span>News</span></a>
        </li>
     	<% 
     		}
        %>
        <% 
        	if (accessUsers) { 
        %>
        <li class="nav-item" id="user">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/user/index">
            <i class="far fa-user"></i>
            <span>User</span></a>
        </li>
       <% 
       		}
        %>
        <% 
        	if (accessSlides) { 
        %>
        <li class="nav-item" id="slide">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/slide/index">
            <i class="far fa-image"></i>
            <span>Slide</span></a>
        </li>
        <% 
        	}
        %>
        <% 
        	if (accessContacts){
        %>
        <li class="nav-item" id="contact">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/contact/index">
            <i class="far fa-address-card"></i>
            <span>Contact</span></a>
        </li>
        
        <li class="nav-item" id="comment">
          <a class="nav-link" href="<%=request.getContextPath()%>/admin/comment/index">
            <i class="far fa-address-card"></i>
            <span>Comment</span></a>
        </li>
    	<% 
    		} 
    	%>
      </ul>