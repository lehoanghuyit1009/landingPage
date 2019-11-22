<%@page import="model.bean.User"%>
<%@page import="model.bean.Role"%>
<%@page import="java.util.ArrayList"%>
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
            <li class="breadcrumb-item active">Edit</li>
          </ol>
          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Edit A User
            </div>
            <div id="user-exist">
            <%
            	String msg = request.getParameter("msg");
            	if ("0".equals(msg)) {
            		out.print("<div class='alert alert-warning message'><i class='fas fa-times'></i>  Username is existed.</div>");
            	}
            %>
            </div>
            <div class="card-body">
            <%
            if(request.getAttribute("user")!= null){
        		User user = (User)request.getAttribute("user");
            %>
	          <form action="" method="post">
	            <div class="form-group">
	              <div class="form-label-group">
	                <input type="text" name="username" readonly="readonly" value="<%=user.getUsername()%>" id="inputUsername" class="form-control"
	                 	placeholder="Username" pattern=".{6,32}" title="Username from 6 to 32 character" 
	                 	maxlength="32" required autofocus="autofocus" onchange="checkUsernameExist()" /> <!-- ajax username exist -->
	                <label for="inputUsername">Username</label>
	              </div>
	            </div>
	            <div class="form-group">
	              <div class="form-label-group">
	                <input type="text" name="fullname" value="<%=user.getFullname()%>" id="inputFullname" class="form-control"
	                	placeholder="Fullname" pattern=".{6,32}" title="Fullname from 6 to 32 character" 
	                 	maxlength="32" placeholder="Fullname" required="required" />
	                <label for="inputFullname">Fullname</label>
	              </div>
	            </div>
	            <div class="form-group">
	              <div class="form-label-group">
	                <input type="email" name="email" value="<%=user.getEmail()%>" id="email" class="form-control"
	                	 placeholder="Email" required="required" />
	                <label for="inputFullname">Email</label>
	              </div>
	            </div>
	            <div class="form-group">
	              <div class="form-label-group">
	                <input type="password" name="password" value="" id="inputPassword" class="form-control"
	                	placeholder="Password" pattern=".{5,32}" title="Password from 6 to 32 character" 
	                	maxlength="32" required/>
	                <label for="inputPassword">Password</label>
	              </div>
	            </div>
	            <div class="form-group">
				    <label for="role">User permissions</label>
				    <select name="role" class="form-control" id="role">
				    <%
				    	ArrayList<Role> listRole= (ArrayList<Role>)request.getAttribute("listRole");
				    	if (listRole.size() > 0) {
				    	for (Role item : listRole) {
				    		String active ="";
				    		if(item.getId() == user.getRole().getId()){
				    			active="selected='selected'";
				    		}else{
				    			active="";
				    		}
				    %>
					    <option <%=active%>
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
	          <%
            	}
	          %>
	        </div>
          </div>
          
        </div>
        <!-- /.container-fluid -->
		<%@ include file="/template/admin/inc/footer.jsp" %>
        <%@ include file="/template/admin/inc/script.jsp" %>
        <script type="text/javascript">document.getElementById('user').classList.add('active');</script>
        
        <%@ include file="/template/admin/inc/end-html.jsp" %>