<%@page import="model.bean.*"%>
<%@page import="model.dao.*" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	pageEncoding="UTF-8"%>

<%@ include file="/template/admin/inc/header-leftbar.jsp"%>

<div id="content-wrapper">
	<div class="container-fluid">

		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/admin">Dashboard</a></li>
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/admin/user">User</a></li>
			<li class="breadcrumb-item active">Add</li>
		</ol>

		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Add A User
			</div>

			<div class="card-body">
			<%
	            	String username = "", password = "", fullname = "";
            		/* from doGet AdminEditUserController */
            		int userRoleId = 3;
	            	if (request.getAttribute("user") != null) {
	            		User user = (User) request.getAttribute("user");
	            		username = user.getUsername();
	            		password = user.getPassword();
	            		fullname = user.getFullname();
	            		userRoleId = user.getRole().getId();
	            	}
	            %>
				<form action="<%=request.getContextPath() + "/admin/user/add"%>"
					method="post">
					<div class="form-group">

						<div class="form-label-group">
							<a>Username</a> <a style="color: red;">*</a> <input type="text"
								name="username"
								value="<%-- <%if(username != null) out.print(username);%> --%>"
								id="username" class="form-control" placeholder="username"
								pattern=".{3,32}" title="Username" maxlength="255" required
								autofocus="autofocus" />
						</div>

						<div class="form-label-group">
							<a>Password</a> <a style="color: red;">*</a> <input
								type="password" name="password"
								value="<%-- <%if(username != null) out.print(username);%> --%>"
								id="password" class="form-control" placeholder="password"
								pattern=".{3,32}" title="Username" maxlength="255" required
								autofocus="autofocus" />
						</div>

						<div class="form-label-group">
							<a>Fullname</a> <a style="color: red;">*</a> <input type="text"
								name="fullname"
								value="<%-- <%if(username != null) out.print(username);%> --%>"
								id="fullname" class="form-control" placeholder="username"
								pattern=".{3,32}" title="Fullname" maxlength="255" required
								autofocus="autofocus" />
						</div>

						<div class="form-label-group">
							<a>Email</a> <input type="email" name="email" value="" id="email"
								class="form-control" placeholder="email" title="Email"
								maxlength="255" autofocus="autofocus" />
						</div>


						<div class="form-group">
				    <label for="role">User Role</label>
				    <select name="role" class="form-control" id="role">
				    <%
				    	RoleDAO roleDAO = new RoleDAO();
				    	ArrayList<Role> listRole = roleDAO.getItems();
				    	if (listRole.size() > 0) {
				    		for (Role item : listRole) {
				    %>
					    <option 
					    	<%if(item.getId() == userRoleId) out.print(" selected");%> 
					    	value="<%=item.getId()%>"><%=item.getName()%>
					    </option>
					<%
				    		}
				    	}
					%>
				    </select>
				</div>
				
				<div class="form-group d-inline-block">
	            	<button type="submit" id="submit" class="btn btn-primary" name="submit">
	            		<i class="fas fa-plus"></i>
	            		Edit
	            	</button>
	            </div>
				</form>
			</div>

		</div>

	</div>
	</div>
	
        <!-- /.container-fluid -->

        <%@ include file="/template/admin/inc/script.jsp" %>
        <script>
		$('title').html('User Edit');
		</script>
        <!-- code script here -->
        <script type="text/javascript">document.getElementById('users').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>