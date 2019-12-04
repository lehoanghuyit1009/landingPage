package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.User;
import model.dao.CommentDAO;
import model.dao.UserDAO;
import model.service.CommentService;
import model.service.UserService;
import util.DateUtil;

public class PublicAddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;
	private UserService userService;

	public PublicAddCommentController() {
		super();
		commentService = new CommentService();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("userCommentId"));
		String content = request.getParameter("content");
		/* chưa đăng nhập thì không comment nhé */
		if (idUser == 0 || "".equals(content.trim()))
			return;

		int idNews = Integer.parseInt(request.getParameter("newsId"));
		int id_parent = Integer.parseInt(request.getParameter("parentCommentId"));

		User user = userService.getItemById(idUser);
		Timestamp dateCreate = new Timestamp(new Date().getTime());
		Comment comment = new Comment(0, content, idUser, idNews, dateCreate, id_parent, 1);
		// add comment to db
		PrintWriter out = response.getWriter();
		String className = id_parent == 0 ? "" : "left-padding";
		if (commentService.insertItem(comment) > 0) {
			out.println("<div class='comment-list " + className + "'>"
					+ "<div class='single-comment justify-content-between d-flex'>"
					+ "<div class='user justify-content-between d-flex'>" + "<div class='thumb'>"
					+ "<i class='fa fa-user icon-color'></i>" + "</div>" + "<div class='desc'>" + "<h5><a href='#'>"
					+ user.getFullname() + "</a></h5>" + "<p class='date'>" + DateUtil.getDateFormatDetail(dateCreate)
					+ " </p>" + "<p class='comment'>" + content + "</p>" + "</div>" + "</div>"
					+ "<div class='reply-btn'>" + "<a onclick=\"return reply(" + id_parent + ", '" + user.getFullname()
					+ "')\"" + " href='javascript:void(0)' class='btn btn-info text-uppercase'>reply</a>" + "</div>"
					+ "</div>" + "</div>");
		}

	}

}
