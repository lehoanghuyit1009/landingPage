<%@page import="util.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/template/public/inc/header.jsp" %>
		<div class="site-main-container">
			<section class="contact-page-area pt-50 pb-120">
				<div class="container">
					<div class="row contact-wrap">
						<div class="col-lg-3 d-flex flex-column address-wrap">
							<div class="single-contact-address d-flex flex-row">
								<div class="icon">
									<span class="fa fa-address-card-o" style="font-size: 22px;"></span>
								</div>
								<div class="contact-details ">
									<h5 class="ml-2 title-prolfe"> MY PROFILE</h5>
								</div>
							</div>
							<%
								String fullname="";
								String email ="";
								if(session.getAttribute(DefineUtil.USER_LOGIN)!= null){
									User userLogin = AuthUtil.getUserLogin(request); 
									fullname = userLogin.getFullname();
									email = userLogin.getEmail();
								}
								
							%>
							<div class="single-contact-address d-flex flex-row">
								<div class="img-profile">
									<img src="<%=request.getContextPath()%>/template/public/img/r3.jpg" width="82px" height="82px" style="border-radius: 50%;" alt="">
								</div>
							</div>
							<div class="single-contact-address d-flex flex-row">
								<div class="contact-details">
									<h2 class="fullname"><%=fullname %>  <a class="edit-profile" href="#form-profile" data-toggle="collapse" aria-expanded="false" aria-controls="form-profile"><span class="fa fa-pencil"></span></a></h2>
									<span class="fa fa-envelope"></span><p class="email-profile">
										 <%=email %>
									</p>
								</div>
							</div>
						</div>
						<div class="col-lg-9 collapse" id="form-profile">
							<form class="form-area contact-form text-right" action="" method="post">
								<div class="row">
									<div class="col-lg-4">
										<input name="fullname" placeholder="Enter fullname" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter fullname'" class="common-input mb-20 form-control" required="required" type="text">
										<input name="email" placeholder="Enter email address" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'" class="common-input mb-20 form-control" required="required" type="email">
										<input name="password" placeholder="Enter password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter password'" class="common-input mb-20 form-control" required="required" type="password">
										<%
											if(request.getParameter("msg")!=null){
												String msg =request.getParameter("msg");
												if("1".equals(msg)){
													out.print("<div class='alert-msg alert alert-info' style='text-align: left;'>Edit profile successfully</div>");
												}else{
													out.print("<div class='alert-msg alert alert-warning' style='text-align: left;'>Have an error !</div>");
												}
											}
										%>
										<button class="btn btn-primary flex-cell" type="submit">Edit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
			<!-- End contact-page Area -->
		</div>
		<script src="<%=request.getContextPath()%>/template/public/js/vendor/jquery-2.2.4.min.js"></script>
		<%@ include file="/template/public/inc/footer.jsp" %>