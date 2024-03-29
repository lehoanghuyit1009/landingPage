<%@page import="java.util.HashMap"%>
<%@page import="util.StringUtil"%>
<%@page import="util.DateUtil"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-lg-4">
	<div class="sidebars-area" id="sidebars-area">
		<%
			if(request.getAttribute("listRelate")!= null){
		%>
		<div class="single-sidebar-widget most-popular-widget">
			<h6 class="title">Relate news</h6>
			<%
				if(request.getAttribute("listRelate")!=null){
					ArrayList<News> listRelate = (ArrayList<News>)request.getAttribute("listRelate");
					if (listRelate != null && listRelate.size() > 0) { 
						HashMap<Integer,Integer> listCountCommentRelate = (HashMap<Integer,Integer>)request.getAttribute("listCountCommentRelate");
						for (News item : listRelate) {
			%>
			<div class="single-list flex-row d-flex">
				<div class="thumb">
					<img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt="" style="width: 100px; height: 90px;">
				</div>
				<div class="details">
					<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(item.getName())%>-<%=item.getId()%>">
						<h6><%=StringUtil.getText(item.getName(), 70)%></h6>
					</a>
					<ul class="meta">
						<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=DateUtil.getDateFormat(item.getDateCreate())%></a></li>
						<li><a href="#"><span class="lnr lnr-bubble"></span><%=listCountCommentRelate.get(item.getId()) %></a></li>
					</ul>
				</div>
			</div>
			<%
						}
					}
				}
			%>
		</div>			
		<%
			}
		%>
		<div class="single-sidebar-widget most-popular-widget">
			<h6 class="title">Most Popular</h6>
			<%
				if(request.getAttribute("listPopular")!=null){
					ArrayList<News> listPopular = (ArrayList<News>)request.getAttribute("listPopular");
					if (listPopular != null && listPopular.size() > 0) { 
						HashMap<Integer,Integer> listCountCommentPopular = (HashMap<Integer,Integer>)request.getAttribute("listCountCommentPopular");
						for (News itemNews : listPopular) {
			%>
			<div class="single-list flex-row d-flex">
				<div class="thumb">
					<img src="<%=request.getContextPath()%>/files/<%=itemNews.getPicture()%>" alt="" style="width: 100px; height: 90px;">
				</div>
				<div class="details">
					<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(itemNews.getName())%>-<%=itemNews.getId()%>">
						<h6><%=StringUtil.getText(itemNews.getName(), 70)%></h6>
					</a>
					<ul class="meta">
						<li><a href="#"><span class="lnr lnr-calendar-full"></span><%=DateUtil.getDateFormat(itemNews.getDateCreate())%></a></li>
						<li><a href="#"><span class="lnr lnr-bubble"></span><%=listCountCommentPopular.get(itemNews.getId()) %></a></li>
					</ul>
				</div>
			</div>
			<%
						}
					}
				}
			%>
		</div>
		
		<%-- <div class="single-sidebar-widget ads-widget">
			<img class="img-fluid" src="<%=request.getContextPath()%>/template/public/img/sidebar-ads.jpg" alt="">
		</div> --%>
		<div class="single-sidebar-widget social-network-widget">
			<h6 class="title">Social Networks</h6>
			<ul class="social-list">
				<li class="d-flex justify-content-between align-items-center fb">
					<div class="icons d-flex flex-row align-items-center">
						<i class="fa fa-facebook" aria-hidden="true"></i>
						<p>983 Likes</p>
					</div>
					<a href="https://www.facebook.com/Blog-l%E1%BA%ADp-tr%C3%ACnh-769940133366459/notifications/" target="_blank">Like our page</a>
				</li>
				<li class="d-flex justify-content-between align-items-center tw">
					<div class="icons d-flex flex-row align-items-center">
						<i class="fa fa-twitter" aria-hidden="true"></i>
						<p>983 Followers</p>
					</div>
					<a href="https://twitter.com/tranhuuhongson" target="_blank">Follow Us</a>
				</li>
				<li class="d-flex justify-content-between align-items-center yt">
					<div class="icons d-flex flex-row align-items-center">
						<i class="fa fa-youtube-play" aria-hidden="true"></i>
						<p>983 Subscriber</p>
					</div>
					<a href="https://www.youtube.com/channel/UCGoukydCPpE8SMNhKZ2mIDw"  target="_blank">Subscribe</a>
				</li>
			</ul>
		</div>
	</div>
</div>