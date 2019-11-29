package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.service.CommentService;
import model.service.NewsService;
import util.DefineUtil;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CommentService commentService;

	public PublicSearchController() {
		super();
		newsService = new NewsService();
		commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == null || "".equals(name)) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		name = name.trim();
		int numberOfItems = newsService.countItemsSearch(name);
		if (numberOfItems > 0) {
			int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage < 1)
				currentPage = 1;
			else if (currentPage > numberOfPages)
				currentPage = numberOfPages;
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			
			ArrayList<News> listNews = newsService.getItemsSearch(name, offset);
			HashMap<Integer, String> listUserName = newsService.getListUserName(listNews);
			HashMap<Integer, Integer> listCountComment = commentService.getListCountComment(listNews);
			request.setAttribute("listCountComment", listCountComment);
			request.setAttribute("listUserName", listUserName);
			request.setAttribute("listNews", listNews);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", numberOfItems);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
