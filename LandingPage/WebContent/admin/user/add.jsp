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
              <a href="<%=request.getContextPath()%>/admin/user">User</a>
            </li>
            <li class="breadcrumb-item active">Add</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Add A User
            </div>
            <div id="category-exist">
            <%
            	String msg = request.getParameter("msg");
            	if ("0".equals(msg)) {
            		out.print("<div class='alert alert-warning message'><i class='fas fa-times'></i>  Category is existed.</div>");
            	}
            %>
            </div>
            <div class="card-body">
	          <form action="<%=request.getContextPath()+"/admin/user/add" %>" method="post">
	            <div class="form-group">
	            
	              <div class="form-label-group">
	              	<a>Username</a> <a style="color: red;">*</a>
	                <input type="text" name="username" value="<%-- <%if(username != null) out.print(username);%> --%>" id="username" class="form-control"
	                 	placeholder="username" pattern=".{3,32}" title="Username" 
	                 	maxlength="255" required autofocus="autofocus" />
	              </div>
	              
	              <div class="form-label-group">
	              	<a>Password</a> <a style="color: red;">*</a>
	                <input type="password" name="password" value="<%-- <%if(username != null) out.print(username);%> --%>" id="password" class="form-control"
	                 	placeholder="password" pattern=".{3,32}" title="Username" 
	                 	maxlength="255" required autofocus="autofocus" />
	              </div>
	              
	              <div class="form-label-group">
	              	<a>Fullname</a> <a style="color: red;">*</a>
	                <input type="text" name="fullname" value="<%-- <%if(username != null) out.print(username);%> --%>" id="fullname" class="form-control"
	                 	placeholder="username" pattern=".{3,32}" title="Fullname" 
	                 	maxlength="255" required autofocus="autofocus" />
	              </div>
	              
	              <div class="form-label-group">
	              	<a>Email</a>
	                <input type="email" name="email" value="<%-- <%if(username != null) out.print(username);%> --%>" id="email" class="form-control"
	                 	placeholder="email" pattern=".{3,32}" title="Email" 
	                 	maxlength="255" required autofocus="autofocus" />
	              </div>
	              

	              <div class="form-label-group">
	              	<a>Role</a><a style="color: red;">*</a>
	                <select name="role" value="<%-- <%if(username != null) out.print(username);%> --%>" id="role" class="form-control"
	                 	 pattern=".{3,32}" title="Email" >
	                 	 <option value = 2> User </option>
	                 	 <option value = 2> Admin </option>
	                 </select>
	                 	
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
        <script type="text/javascript">document.getElementById('categories').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>