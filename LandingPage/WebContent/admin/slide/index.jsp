<%@page import="model.bean.Slide"%>
<%@page import="util.MessageUtil"%>
<%@page import="util.DefineUtil"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.News"%>
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
            <li class="breadcrumb-item active">Slide</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Slides List
             </div>
            <div class="card-body">
              <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                	<div class="row justify-content-between">
                		<div class="col-sm-12 col-md-2">
                			<!-- Button add -->
					        <a href="<%=request.getContextPath()%>/admin/slide/add" id="btn-add" class="btn btn-primary">
					        	<i class="fas fa-plus"></i>
					        	Add
					        </a>
                		</div>
                		<div class="col-sm-12 col-md-5">
	                		<%
	                			/* message msg=0|1|2|3|4 */
	                			MessageUtil.getMessage(request, out);
	                		%>
                		</div>
	                	<div class="col-sm-12 col-md-5">
	                		<form action="<%=request.getContextPath()%>/admin/slide/search" method="post" class="d-flex input-group" id="search-form">
	                			<%
	                				String name = "";
	                				if (request.getParameter("name") != null) {
	                					name = request.getParameter("name");
	                				}
	                			%>
					        	<input type="text" class="form-control" value="<%=name%>" name="name" required placeholder="Search slides" aria-label="Search" aria-describedby="basic-addon2" />
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
		                	<table class="table table-hover table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="table-layout: fixed;">
		                  		<thead>
		                    		<tr role="row">
		                    			<th class="sorting_asc text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 1px;">
		                    				Id
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 108px;">
		                    				Name
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 149px;">
		                    				Picture
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 103px;">
		                    				Link
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 12px;">
		                    				Sort
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 22px;">
		                    				Active
		                    			</th>
		                    			<th class="sorting text-center" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 73px;">
		                    				Action
		                    			</th>
		                    		</tr>
		                  		</thead>
		                  		<tbody>
		                  			<%
			                  			int currentPage = 1;
	                            		if (request.getAttribute("currentPage") != null)
	                                		currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
	                            		
		                  				@SuppressWarnings("unchecked")
		                  				ArrayList<Slide> listSlides =  (ArrayList<Slide>) request.getAttribute("listSlide");
		                  				if (listSlides != null && listSlides.size() > 0) {
		                  					for (Slide item : listSlides) {
		                  						String url = request.getContextPath()+"/admin/slide";
		                  			%>
		                  			<tr role="row" class="odd">
					                    <td class="sorting_1 text-center"><%=item.getId()%></td>
					                    <td class="text-center"><%=item.getName()%></td>
					                    <td class="text-center">
					                    	<%
					                    		if (!"".equals(item.getPicture())) {
					                    	%>
					                    	<img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt="" class="img-thumbnail" style="width: 100px;height: 100px">
					                    	<%
					                    		} else {
					                    	%>
					                    	<p>No image</p>
					                    	<%
					                    		}
					                    	%>
					                    </td>
					                    <td class="text-center"><%=item.getLink()%></td>
					                    <td class="text-center"><%=item.getSort()%></td>
										<td class="center text-center" id="active-<%=item.getId()%>">
												<% if(item.getActive() == 1){ %>
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
											<a href="<%=url %>/edit?page=<%=currentPage%>&id=<%=item.getId()%>" class="btn btn-info">
								          		<i class="fas fa-edit"></i></a>
											<a href="<%=url %>/del?page=<%=currentPage%>&id=<%=item.getId()%>" class="btn btn-danger"
												onclick="return confirm('Are you want to delete <%=item.getName()%>?')">
								          		<i class="fas fa-trash-alt"></i></a>
								          	</div>
											</td>
		                   			</tr>
		                   			<%
		                  					}
		                  				} else {
		                    		%>
		                    		<tr><td colspan="7" style="text-align: center"><strong>0 slide.</strong></td></tr>
		                    		<%
		                  				}
		                    		%>
								</tbody>
		                	</table>
                		</div>
               		</div>
               		<%
                            	
                    	if (listSlides != null && listSlides.size() > 0) {
                    		int numberOfPages = Integer.parseInt(request.getAttribute("numberOfPages").toString());
                        	int numberOfItems = Integer.parseInt(request.getAttribute("numberOfItems").toString());
                    %>
               		<div class="row">
               			<div class="col-sm-12 col-md-5">
               				<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
               					<%
               						int from = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE + 1;
               						int to = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE + listSlides.size();
               					%>
               					Showing <%=from%> to <%=to%> of <%=numberOfItems%> slides
               				</div>
               			</div>
               			<div class="col-sm-12 col-md-7">
               				<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
               					<ul class="pagination">
	               					<%
	                              		String href = request.getContextPath() + "/admin/slide/index?page=";
	                              	%>
	                              	<!-- Xử lí nut previous -->
               						<li class="paginate_button page-item previous <%if(currentPage == 1) out.print("disabled");%>" id="dataTable_previous">
               							<a href="<%=href + (currentPage - 1)%>" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">
               								Previous
               							</a>
               						</li>
               						
               						<!-- Xử lí những nút ở giữa -->
               						<%
               							if (currentPage <= DefineUtil.NUMBER_PAGINATION_PER_PAGE) { /* Trường hợp 1 */
                                    		int len = numberOfPages < DefineUtil.NUMBER_PAGINATION_PER_PAGE ? numberOfPages : DefineUtil.NUMBER_PAGINATION_PER_PAGE;
                                        	for (int i = 1; i <= len; i++) {
               						%>
               						<li class="paginate_button page-item <%if(currentPage == i) out.print("active");%>">
               							<a href="<%=href + i%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								<%=i%>
               							</a>
               						</li>
               						<%
                                        	}
               								if (numberOfPages > DefineUtil.NUMBER_PAGINATION_PER_PAGE) {
               						%>
               						<li class="paginate_button page-item">
               							<a href="<%=href + numberOfPages%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								End
               							</a>
               						</li>
               						<%
               								}
               							} else if (currentPage > numberOfPages - DefineUtil.NUMBER_PAGINATION_PER_PAGE) { /* Trường hợp 2 */
               						%>
               						<li class="paginate_button page-item">
               							<a href="<%=href + 1%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								One
               							</a>
               						</li>
               						<%
	                                 		for (int i = numberOfPages - DefineUtil.NUMBER_PAGINATION_PER_PAGE + 1; i <= numberOfPages; i++) {
	                                %>
	                                <li class="paginate_button page-item <%if(currentPage == i) out.print("active");%>">
               							<a href="<%=href + i%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								<%=i%>
               							</a>
               						</li>
	                                <%
	                                 		}
	                                %>
               						<%
               							} else {
               						%>
               						<li class="paginate_button page-item">
               							<a href="<%=href + 1%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								One
               							</a>
               						</li>
               						<%
               								for (int i = currentPage - DefineUtil.NUMBER_PAGINATION_PER_PAGE / 2; i <= currentPage + DefineUtil.NUMBER_PAGINATION_PER_PAGE / 2; i++) {
               						%>
               						<li class="paginate_button page-item <%if(currentPage == i) out.print("active");%>">
               							<a href="<%=href + i%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								<%=i%>
               							</a>
               						</li>
               						<%
               								}
               						%>
               						<li class="paginate_button page-item">
               							<a href="<%=href + numberOfPages%>" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
               								End
               							</a>
               						</li>
               						<%
               							}
               						%>
               						
               						<!-- Xử lí nut next -->
               						<li class="paginate_button page-item next <%if(currentPage == numberOfPages) out.print("disabled");%>" id="dataTable_next">
               							<a href="<%=href + (currentPage + 1)%>" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">
               								Next
               							</a>
               						</li>
               					</ul>
               				</div>
               			</div>
               		</div>
               		<%}%>
               	</div>
              </div>
            </div>
         <%--  <div class="card-footer small text-muted">Hi <%=userLogin.getUsername()%>. Have a nice day.</div> --%>
        </div>

        </div>
        <!-- /.container-fluid -->

        <%@ include file="/template/admin/inc/footer.jsp" %>
        <%@ include file="/template/admin/inc/script.jsp" %>
        <script>document.getElementById('slide').classList.add('active');</script>
        <script>
	    function getActive(trangthai,id){
	    	$.ajax({
	    		url: '<%=request.getContextPath()%>/admin/slide/index',
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
        <%@ include file="/template/admin/inc/end-html.jsp" %>
        