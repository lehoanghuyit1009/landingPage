package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.service.CommentService;
import util.DefineUtil;
import vn.edu.vinaenter.service.Pagination;

public class AdminCommentSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;

	public AdminCommentSearchController() {
		super();
		this.commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == null || "".equals(name)) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		name = name.trim();
		int numberOfItems = commentService.countItemsSearch(name);
		if (numberOfItems > 0) {
			int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			String page = request.getParameter("page");
			try {
				if (page != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));
				} else {
					currentPage = 1;
				}
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/notFound");
				return;
			}
			if (currentPage < 1)
				currentPage = 1;
			else if (currentPage > numberOfPages)
				currentPage = numberOfPages;
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			ArrayList<Comment> listComment = commentService.getItemsSearch(name, offset);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", numberOfItems);
			HashMap<Integer, String> listUserName = commentService.getListUserName(listComment);
			HashMap<Integer, String> listNewsName = commentService.getListNewsName(listComment);
			request.setAttribute("listUserName", listUserName);
			request.setAttribute("listNewsName", listNewsName);
			request.setAttribute("listComment", listComment);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int trangthai = Integer.parseInt(request.getParameter("atrangthai"));
		int id = Integer.parseInt(request.getParameter("aid"));
		if (trangthai == 1) {
			trangthai = 0;
			out.print("<a href=\"javascript:void(0)\" onclick=\"return getActive(0," + id + ")\">" + "<img src=\""
					+ request.getContextPath() + "/template/admin/images/deactive.gif\" />" + "</a>");
		} else {
			trangthai = 1;
			out.print("<a href=\"javascript:void(0)\" onclick=\"return getActive(1," + id + ")\">" + "<img src=\""
					+ request.getContextPath() + "/template/admin/images/active.gif\" />" + "</a>");
		}
		commentService.upDateActive(trangthai, id);
	}

}
