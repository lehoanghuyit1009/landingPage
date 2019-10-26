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
              <a href="<%=request.getContextPath()%>/admin/categories">Categories</a>
            </li>
            <li class="breadcrumb-item active">Edit</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Edit A Category
            </div>
            <div id="category-exist" value="h test">
            <%
            	String msg = request.getParameter("msg");
            	if ("0".equals(msg)) {
            		out.print("<div class='alert alert-warning message'><i class='fas fa-times'></i>  Category is existed.</div>");
            	}
            %>
            </div>
            <div class="card-body">
	          <form action="<%=request.getContextPath()+"/admin/category/edit" %>" method="post">
	            <div class="form-group">
	            
	             <%
	            	Category cat = (Category) request.getAttribute("category");
	                int idcat = 0;
	                String name = "";
	                if (cat != null) {
	                	idcat= cat.getParentCategoryId();
	                	name = cat.getName();
	                }
	                
	              %>
	              <div class="form-label-group">
	                <input type="text" name="name" value="<%=name %>" id="inputname" class="form-control"
	                 	 placeholder="Category" pattern=".{3,32}" title="Category name from 3 to 32 letter" 
	                 	maxlength="32" required autofocus="autofocus" />
	                <label for="name">Category</label> 
	              </div>
	            </div>
	            <%
	            	Category category = (Category) request.getAttribute("category");
	                int id = 0;
	                if (category != null)
	                	id= category.getId();
	                
	            %>
	            <div class="form-group d-inline-block">
	            	<button type="submit" id="submit" class="btn btn-primary" name="submit">
	            		<i class="fas fa-plus"></i>
	            		Edit
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
		$('title').html('Category Edit');
		</script>
        <!-- code script here -->
        <script type="text/javascript">document.getElementById('categories').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>