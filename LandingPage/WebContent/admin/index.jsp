<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/template/admin/inc/header-leftbar.jsp" %>
      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="<%=request.getContextPath()%>/admin">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Overview</li>
          </ol>
		 <%--  <%
		  		UserDAO userDAO = new UserDAO();
		  		int countUser = userDAO.countItems();
		  		CategoryDAO categoryDAO = new CategoryDAO();
		  		int countCategory = categoryDAO.countItems();
		  		NewsDAO newsDAO = new NewsDAO();
		  		int countNews = newsDAO.countItems();
		  		SlideDAO slideDAO = new SlideDAO();
		  		int countSlide = slideDAO.countItems();
		  %> --%>
          <div class="row">
		 <%--  <%if(accessUsers) { %> --%>
          <!-- Icon Cards-->
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-users"></i>
                  </div>
                  <div class="mr-5"><%-- <%=countUser%> --%>11 Users!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/user/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <%-- <%} %>
            <%if(accessCategories) { %> --%>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                  </div>
                  <div class="mr-5"><%-- <%=countCategory %> --%>11 Main Categories!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/category/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
           <%--  <%} %>
            <%if(accessNewses) { %> --%>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="far fa-newspaper"></i>
                  </div>
                  <div class="mr-5"><%-- <%=countNews%> --%>11 News</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/news/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
           <%--  <%} %>
            <%if(accessSlides) { %> --%>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="far fa-image"></i>
                  </div>
                  <div class="mr-5"><%-- <%=countSlide%> --%>11 Slide avalible</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/slide/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-info o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-phone"></i>
                  </div>
                  <div class="mr-5">11 Contacts</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" target="_blank" href="<%=request.getContextPath()%>/">
                  <span class="float-left">Go to page</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-dark o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-comment"></i>
                  </div>
                  <div class="mr-5">11 Comments</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" target="_blank" href="<%=request.getContextPath()%>/admin/comment/index">
                  <span class="float-left">Go to page</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
          <%--   <%} %> --%>
        </div>
        <!-- /.container-fluid -->
        <%@ include file="/template/admin/inc/footer.jsp" %>
        <%@ include file="/template/admin/inc/script.jsp" %>
		<script>
		$('title').html('landingpage.com');
		</script>
        
        <!-- code script here -->
        <script type="text/javascript">document.getElementById('dashboard').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>