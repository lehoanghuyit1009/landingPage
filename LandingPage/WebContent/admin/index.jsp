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
		 <%
		  		int countUser = 0;
		  		int countCategory = 0;
		  		int countNews = 0;
		  		int countSlide =0;
		  		int countContact=0;
		  		int countComment=0;
		  		if(request.getAttribute("countUser")!= null)
		  			countUser = (int)request.getAttribute("countUser");
		  		if(request.getAttribute("countCat")!= null)
		  			countCategory = (int)request.getAttribute("countCat");
		  		if(request.getAttribute("countNews")!= null)
		  			countNews = (int)request.getAttribute("countNews");
		  		if(request.getAttribute("countSlide")!= null)
		  			countSlide = (int)request.getAttribute("countSlide");
		  		if(request.getAttribute("countContact")!= null)
		  			countContact = (int)request.getAttribute("countContact");
		  		if(request.getAttribute("countComment")!= null)
		  			countComment = (int)request.getAttribute("countComment");
		  %>
          <div class="row">
		 <%if(accessUsers) { %>
          <!-- Icon Cards-->
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-users"></i>
                  </div>
                  <div class="mr-5"><%=countUser%> User!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/user/index">
                  <span class="float-left">View Detail</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
             <%} %>
            <%if(accessCategories) { %>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                  </div>
                  <div class="mr-5"><%=countCategory %> Main Category!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/category/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
           <%} %>
            <%if(accessNewses) { %>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="far fa-newspaper"></i>
                  </div>
                  <div class="mr-5"><%=countNews%> News</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/news/index">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
           <%} %>
            <%if(accessSlides) { %>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="far fa-image"></i>
                  </div>
                  <div class="mr-5"><%=countSlide%> Slide </div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/slide/index">
                  <span class="float-left">View Detail</span>
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
                  <div class="mr-5"><%=countContact %> Contact</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/contact/index">
                  <span class="float-left">View Detail</span>
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
                  <div class="mr-5"><%=countComment %> Comment</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/admin/comment/index">
                  <span class="float-left">View Detail</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
          <%} %>
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