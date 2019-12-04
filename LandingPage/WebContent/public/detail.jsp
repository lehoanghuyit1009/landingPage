<%@page import="util.DefineUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Comment"%>
<%@page import="model.bean.Category"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/template/public/inc/header.jsp" %>
		<%
			if(request.getAttribute("news")!= null){
				News news = (News)request.getAttribute("news");
				User userLogin = null;
				if(session.getAttribute(DefineUtil.USER_LOGIN) != null)
					userLogin = (User)session.getAttribute(DefineUtil.USER_LOGIN);
		%>
		<div class="site-main-container">
			<%@ include file="/template/public/inc/slide.jsp" %>
			<!-- Start latest-post Area -->
			<section class="latest-post-area pb-120">
				<div class="container no-padding">
					<div class="row">
						<div class="col-lg-8 post-list">
						<%
							if (news != null) {
								HashMap<Integer,Integer> listCountComment = (HashMap<Integer,Integer>)request.getAttribute("listCountComment");
								HashMap<Integer,String> listUserName = (HashMap<Integer,String>)request.getAttribute("listUserName");
								
						%>
							<!-- Start single-post Area -->
							<div class="single-post-wrap">
								<div class="content-wrap">
									<ul class="tags mt-10">
										<li><a href="<%=request.getContextPath()%>/category/<%=StringUtil.makeSlug(news.getCategory().getName())%>-<%=news.getCategory().getId()%>"><%=news.getCategory().getName()%></a></li>
									</ul>
									<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(news.getName())%>-<%=news.getId()%>">
										<h3><%=news.getName()%></h3>
									</a>
									<ul class="meta pb-20">
										<li><a href="#"><span class="lnr lnr-user"></span><%=listUserName.get(news.getCreateBy())%></a></li>
										<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=DateUtil.getDateFormat(news.getDateCreate())%></a></li>
										<li><a href="#"><span class="lnr lnr-bubble"></span><%=listCountComment.get(news.getId())%> Comments</a></li>
										<li><a href="#"><span class="fa fa-eye" style="font-size: 14px;margin-right: 10px;"></span><%=news.getViews()%> </a></li>
									</ul>
									<p>
										<%=news.getDetail()%>
									</p>
									<!-- <blockquote></blockquote> -->
								
								
								<div class="comment-sec-area mycomment-area">
									<div class="container">
										<div class="row flex-column">
											<%
												if(userLogin == null){
											%>
												<div class="alert alert-warning">
													<h5><a href="<%=request.getContextPath()%>/login">Login to comment this post</a></h5>
												</div>
											<%
												}
												if(request.getAttribute("listComment") != null){
												ArrayList<Comment> listComments = (ArrayList<Comment>) request.getAttribute("listComment");
												HashMap<Integer,String> listUserNameComment = (HashMap<Integer,String>)request.getAttribute("listUserNameComment");
												if (listComments != null && listComments.size() > 0) {
													HashMap<Integer, ArrayList<Comment>> listChildComment = (HashMap<Integer, ArrayList<Comment>>)request.getAttribute("listChildComment");
													
											%>
											<div class="header-comment">
												<h5>Comments</h5>
											</div>
											<%
													for (Comment itemComment : listComments) {  
											%>
											<div class="comment-list-wrapper-<%=itemComment.getId()%>">
												<div class="comment-list">
													<div class="single-comment justify-content-between d-flex">
														<div class="user justify-content-between d-flex">
															<div class="thumb">
																<i class="fa fa-user icon-color"></i>
															</div>
															<div class="desc">
																<h5><a href="#"><%=listUserNameComment.get(itemComment.getIdUser()) %></a></h5>
																<p class="date"><%=DateUtil.getDateFormatDetail(itemComment.getDateCreate())%> </p>
																<p class="comment">
																	<%=itemComment.getContent()%>
																</p>
															</div>
														</div>
														<div class="reply-btn">
															<a onclick="return reply( <%=itemComment.getId()%>, '<%=listUserNameComment.get(itemComment.getIdUser())%> ')" href="javascript:void(0)" class="btn btn-info text-uppercase">reply</a>
														</div>
													</div>
												</div>
												<%
														//một khối comment đặt trong <div class="comment-list-wrapper"> rồi trong cái này có thêm các comment con nữa
														ArrayList<Comment> list = listChildComment.get(itemComment.getId());
														for (Comment item : list) {
															String className = item.getId_parent() == 0 ? "" : "left-padding";
												%>
												<div class="comment-list <%=className%>">
													<div class="single-comment justify-content-between d-flex">
														<div class="user justify-content-between d-flex">
															<div class="thumb">
																<i class="fa fa-user icon-color"></i>
															</div>
															<div class="desc">
																<h5><a href="#"><%=listUserNameComment.get(item.getIdUser()) %></a></h5>
																<p class="date"><%=DateUtil.getDateFormatDetail(item.getDateCreate())%> </p>
																<p class="comment">
																	<%=item.getContent()%>
																</p>
															</div>
														</div>
														<div class="reply-btn">
															<a onclick="return reply(<%=itemComment.getId()%>, '<%=listUserNameComment.get(itemComment.getIdUser())%>')" href="javascript:void(0)" class="btn btn-info text-uppercase">reply</a>
														</div>
													</div>
												</div>
												<%
														}
												%>
												<div class="comment-list left-padding comment-reply comment-reply-<%=itemComment.getId()%>">
													<div class="single-comment">
														<div class="user d-flex">
															<div class="thumb">
																<i class="fa fa-user icon-color"></i>
															</div>
															<div class="desc" style="width: 100%">
																<form class="row" action="javascript:void(0)">
																	<div class="form-group col-sm-8">
																    	<input type="text" class="form-control myform-comment" name="comment" required="required" placeholder="Enter comment">
																  	</div>
																  	<div class="col-sm-4">
																  		<button type="button" onclick="upComment(<%=news.getId()%>, <%=itemComment.getId() %>,<%if(userLogin == null) out.print("0"); else out.print(userLogin.getId()); %>)"  class="btn btn-info">Send</button>
																  	</div>
																</form>
															</div>
														</div>
														
													</div>
												</div>
											</div>
											<%
													}
												/* ô comment ngoài cùng */
											%>
											<div class="comment-list-wrapper-">
												<div class="comment-list d-block comment-reply-0">
													<div class="single-comment">
														<div class="user d-flex">
															<div class="thumb">
																<i class="fa fa-user icon-color"></i>
															</div>
															<div class="desc" style="width: 100%">
																<form class="row" action="javascript:void(0)">
																	<div class="form-group col-sm-8">
																		<input type="text" class="form-control myform-comment" name="comment" required="required" placeholder="Enter comment">
																    	<!-- <textarea class="form-control myform-comment" name="comment" required="required"></textarea> -->
																  	</div>
																  	<div class="col-sm-4">
																  		<button type="button" onclick="upComment(<%=news.getId()%>, 0,<%if(userLogin == null) out.print("0"); else out.print(userLogin.getId()); %>)"  class="btn btn-info">Send</button>
																  	</div>
																</form>
															</div>
														</div>
														
													</div>
												</div>
											</div>
											<%
												} else { /* Chưa có comment nào thì có ô textarea cho nó nhập */
											%>
											<div class="header-comment">
												<h5>Comments</h5>
											</div>
											<div class="comment-list-wrapper-">
												<div class="comment-list left-padding d-block comment-reply-0">
													<div class="single-comment">
														<div class="user d-flex">
															<div class="thumb myicon">
																<i class="fa fa-user icon-color"></i>
															</div>
															<div class="desc" style="width: 100%">
																<form class="row" action="javascript:void(0)">
																	<div class="form-group col-sm-8">
																    	<input type="text" class="form-control myform-comment" name="comment" required="required" placeholder="Enter comment">
																  	</div>
																  	<div class="col-sm-4">
																  		<button type="button" onclick="upComment(<%=news.getId()%>, 0, <%if(userLogin == null) out.print("0"); else out.print(userLogin.getId()); %>)"  class="btn btn-info">Send</button>
																  	</div>
																</form>
															</div>
														</div>
														
													</div>
												</div>
											</div>
											<%
													}
												}
											%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
							}
						%>
						<!-- End single-post Area -->
					</div>
					
					<%@ include file="/template/public/inc/right_bar.jsp" %>
					
				</div>
			</div>
		</section>
		<!-- End latest-post Area -->
	</div>
	<%
		}
	%>
	<script src="<%=request.getContextPath()%>/template/public/js/vendor/jquery-2.2.4.min.js"></script>
	<script>
	var x = $('.latest-post-area').offset().top - 122;
	$("html, body").animate({ scrollTop: x }, 600);
	function reply(listCommentId, replyUserName) {
		$('.comment-reply-' + listCommentId).show();
		$('.comment-reply-' + listCommentId + ' input').val(replyUserName + "|   ").focus();
	};
	function upComment(newsId, parentCommentId, userCommentId) {
		//console.log(newsId + "-" + parentCommentId + "-" + userCommentId);
		//console.log($('.comment-reply-' + parentCommentId + ' textarea').val());
		$.ajax({
			type: "post",
			/* doPost PublicAddCommentController */
		    url: '<%=request.getContextPath()%>/comment',
		    data: {
		    	newsId: newsId,
		    	parentCommentId: parentCommentId,
		    	userCommentId: userCommentId,
		    	content: $('.comment-reply-' + parentCommentId + ' input').val()
		    },
		    dataType: "html",
		    success: function (response) {
		    	$('.comment-reply-' + parentCommentId).before(response);
		    	$('.comment-reply-' + parentCommentId + ' input').val('');
		    },
		    error: function() {
		    	console.log('lỗi ajax add comment');
		    }
		});
	};
	

	</script>
	<%@ include file="/template/public/inc/footer.jsp" %>