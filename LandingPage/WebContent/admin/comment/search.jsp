<%@page import="util.MessageUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.service.UserService"%>
<%@page import="model.dao.UserDAO"%>
<%@page import="model.bean.Comment"%>
<%@page import="util.DefineUtil"%>
<%@page import="sun.misc.MessageUtils"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/admin/inc/header-leftbar.jsp"%>

<div id="content-wrapper">

	<div class="container-fluid">
	<%
     				String name = "";
     				if (request.getParameter("name") != null) {
     					name = request.getParameter("name");
     				}
     			%>
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/admin">Dashboard</a></li>
			<li class="breadcrumb-item active">Comment</li>
		</ol>

		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i>
				<b> Result sear of: <%=name %></b>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row justify-content-between">
							<div class="col-sm-12 col-md-5">
							<%
								//	get message
	                			/* message msg=0|1|2|3|4 */
	                			MessageUtil.getMessage(request, out);
							%>
                		
							</div>
							<div class="col-sm-12 col-md-5">
								<form
									action="<%=request.getContextPath()%>/admin/comment/search"
									method="get" class="d-flex input-group" id="search-form">
									<input type="text" class="form-control" value="<%=name%>"
										name="name" required placeholder="Search comment"
										aria-label="Search" aria-describedby="basic-addon2" />
									<div class="input-group-append">
										<button class="btn btn-primary" type="submit">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table class="table table-bordered dataTable" id="dataTable"
									width="100%" cellspacing="0" role="grid"
									aria-describedby="dataTable_info"
									style="width: 100%; table-layout: fixed;">
									<thead>
										<tr role="row">
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-sort="ascending" width="15px;"
												aria-label="Name: activate to sort column descending">Id</th>
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending">Username</th>
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending">NewsName</th>
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending">Content</th>
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending">DateCreate</th>
											<th class="sorting text-center" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"width="50px;
												aria-label="Position: activate to sort column ascending">Status</th>
											<th class="sorting text-center" tabindex="0" 
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-label="Start date: activate to sort column ascending">Action</th>
										</tr>
									</thead>
									<tbody>
										<%
											int currentPage = 1;
											if (request.getAttribute("currentPage") != null)
												currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
											HashMap<Integer, String> listUserName = new HashMap<>();
											if(request.getAttribute("listUserName") != null){
												listUserName = (HashMap<Integer, String>)request.getAttribute("listUserName");
											}
											HashMap<Integer, String> listNewsName = new HashMap<>();
											if(request.getAttribute("listNewsName") != null){
												listNewsName = (HashMap<Integer, String>)request.getAttribute("listNewsName");
											}
											@SuppressWarnings("unchecked")
											ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
											if (listComment != null && listComment.size() > 0) {
												UserService service = new UserService();
												String url = request.getContextPath()+"/admin/comment";
												for (Comment item : listComment) {
										%>
										<tr role="row" class="odd">
											<td class="text-center sorting_1" ><%=item.getId()%></td>
											<td class="text-center"><%=listUserName.get(item.getIdUser()) %></td>
											<td class="text-center"><%=listNewsName.get(item.getIdNews()) %></td>
											<td class="text-center"><%=item.getContent()%></td>
											<td class="text-center"><%=item.getDateCreate()%></td>
											<td class="center text-center" id="active-<%=item.getId()%>">
												<% if(item.getStatus() == 1){ %>
													<a href="javascript:void(0)" onclick="return getActive(1,<%=item.getId()%>)">
														<img src="<%=request.getContextPath()%>/template/admin/images/active.gif" />
													</a>
												<%
												}else{
												%>
													<a href="javascript:void(0)" onclick="return getActive(0,<%=item.getId()%>)">
														<img src="<%=request.getContextPath()%>/template/admin/images/deactive.gif" />
													</a>
												<%
												}
												%>
											</td>
											<td class="center text-center">
												<div>
													<a
														href="<%=url%>/delete?page=<%=currentPage%>&id=<%=item.getId()%>"
														class="btn btn-danger"
														onclick="return confirm('Are you want to delete <%=item.getId()%>?')">
														<i class="fas fa-trash-alt"></i>
													</a>
												</div>
											</td>
										</tr>
										<%
											}
											} else {
										%>
										<tr>
											<td colspan="7" style="text-align: center"><strong>0
													comment.</strong></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<%
							if (listComment != null && listComment.size() > 0) {
								int numberOfPages = Integer.parseInt(request.getAttribute("numberOfPages").toString());
								int numberOfItems = Integer.parseInt(request.getAttribute("numberOfItems").toString());
						%>
						<div class="row">
							<div class="col-sm-12 col-md-5">
								<div class="dataTables_info" id="dataTable_info" role="status"
									aria-live="polite">
									<%
										int from = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE + 1;
											int to = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE + listComment.size();
									%>
									Showing
									<%=from%>
									to
									<%=to%>
									of
									<%=numberOfItems%>
									comment.
								</div>
							</div>
							<div class="col-sm-12 col-md-7">
								<div class="dataTables_paginate paging_simple_numbers"
									id="dataTable_paginate">
									<ul class="pagination">
										<%
											String href = request.getContextPath() + "/admin/comment/search?name="+name+"&page=";
										%>
										<!-- Xử lí nut previous -->
										<li
											class="paginate_button page-item previous <%if (currentPage == 1)out.print("disabled");%>"
											id="dataTable_previous"><a
											href="<%=href + (currentPage - 1)%>"
											aria-controls="dataTable" data-dt-idx="0" tabindex="0"
											class="page-link"> Previous </a></li>

										<!-- Xử lí những nút ở giữa -->
										<%
											if (currentPage <= DefineUtil.NUMBER_PAGINATION_PER_PAGE) { /* Trường hợp 1 */
													int len = numberOfPages < DefineUtil.NUMBER_PAGINATION_PER_PAGE
															? numberOfPages
															: DefineUtil.NUMBER_PAGINATION_PER_PAGE;
													for (int i = 1; i <= len; i++) {
										%>
										<li
											class="paginate_button page-item <%if (currentPage == i)out.print("active");%>">
											<a href="<%=href + i%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> <%=i%>
										</a>
										</li>
										<%
											}
													if (numberOfPages > DefineUtil.NUMBER_PAGINATION_PER_PAGE) {
										%>
										<li class="paginate_button page-item"><a
											href="<%=href + numberOfPages%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> End </a></li>
										<%
											}
												} else if (currentPage > numberOfPages - DefineUtil.NUMBER_PAGINATION_PER_PAGE) { /* Trường hợp 2 */
										%>
										<li class="paginate_button page-item"><a
											href="<%=href + 1%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> One </a></li>
										<%
											for (int i = numberOfPages - DefineUtil.NUMBER_PAGINATION_PER_PAGE + 1; i <= numberOfPages; i++) {
										%>
										<li
											class="paginate_button page-item <%if (currentPage == i)out.print("active");%>">
											<a href="<%=href + i%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> <%=i%>
										</a>
										</li>
										<%
											}
										%>
										<%
											} else {
										%>
										<li class="paginate_button page-item"><a
											href="<%=href + 1%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> One </a></li>
										<%
											for (int i = currentPage - DefineUtil.NUMBER_PAGINATION_PER_PAGE / 2; i <= currentPage
															+ DefineUtil.NUMBER_PAGINATION_PER_PAGE / 2; i++) {
										%>
										<li
											class="paginate_button page-item <%if (currentPage == i)out.print("active");%>">
											<a href="<%=href + i%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> <%=i%>
										</a>
										</li>
										<%
											}
										%>
										<li class="paginate_button page-item"><a
											href="<%=href + numberOfPages%>" aria-controls="dataTable"
											data-dt-idx="1" tabindex="0" class="page-link"> End </a></li>
										<%
											}
										%>

										<!-- Xử lí nut next -->
										<li
											class="paginate_button page-item next <%if (currentPage == numberOfPages)out.print("disabled");%>"
											id="dataTable_next"><a
											href="<%=href + (currentPage + 1)%>"
											aria-controls="dataTable" data-dt-idx="7" tabindex="0"
											class="page-link"> Next </a></li>
									</ul>
								</div>
							</div>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="card-footer small text-muted">
				Hi
				<%-- <%=userLogin.getUsername()%> --%>
				. Have a nice day.
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

	<%@ include file="/template/admin/inc/footer.jsp"%>
	<%@ include file="/template/admin/inc/script.jsp"%>
	
	<!-- code script here -->
	<script>
		document.getElementById('comment').classList.add('active');
	</script>
	<script>
	    function getActive(trangthai,id){
	    	$.ajax({
	    		url: '<%=request.getContextPath()%>/admin/comment/index',
	    		type: 'POST',
	    		cache: false,
	    		data: {atrangthai: trangthai, aid: id},
	    		success: function(data){
	    			$('#active-'+id).html(data);
	    		},
	    		error: function (){
	    			alert('Có lỗi xảy ra');
	    		}
	    	});
	    	return false;
	    }
	</script>
	<%@ include file="/template/admin/inc/end-html.jsp"%>