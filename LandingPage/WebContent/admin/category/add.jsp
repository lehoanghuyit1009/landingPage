<%@page import="model.bean.Category"%>
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
            <li class="breadcrumb-item">
              <a href="<%=request.getContextPath()%>/admin/category/index">Category</a>
            </li>
            <li class="breadcrumb-item active">Add</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Add A Category
            </div>
            <div id="category-exist">
            </div>
            <div class="card-body">
	          <form action="<%=request.getContextPath()+"/admin/category/add" %>" method="post">
	            <div class="form-group">
	              <div class="form-label-group">
	                <input type="text" name="name" value="" id="name" class="form-control"
	                 	placeholder="Category" pattern=".{3,32}" title="Category name from 3 to 32 letter" 
	                 	maxlength="32" required autofocus="autofocus" />
	                <label for="name">Category</label>
	              </div>
	            </div>
	            <div class="form-group d-inline-block">
	            	<button type="submit" id="submit" class="btn btn-primary" name="submit">
	            		<i class="fas fa-plus"></i>
	            		Add
	            	</button>
	            </div>
	          </form>
	        </div>
            
            <div class="card-footer small text-muted">Hi ADMIN. Have a nice day.</div>
          </div>
          
        </div>
        <!-- /.container-fluid -->
		<%@ include file="/template/admin/inc/footer.jsp" %>
        <%@ include file="/template/admin/inc/script.jsp" %>
        <script>
		$('title').html('Category Add');
		</script>
        <!-- code script here -->
        <script type="text/javascript">document.getElementById('category').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>