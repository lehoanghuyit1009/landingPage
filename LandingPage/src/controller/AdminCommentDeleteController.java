package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.CommentService;

public class AdminCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;

	public AdminCommentDeleteController() {
		super();
		this.commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0, page = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		if (commentService.deleteItem(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/comment/index?page=" + page + "&&msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/comment/index?page=" + page + "&&msg=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
