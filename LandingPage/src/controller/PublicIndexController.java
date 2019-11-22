package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.Slide;
import model.service.CategoryService;
import model.service.CommentService;
import model.service.NewsService;
import model.service.SlideService;
import util.DefineUtil;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CommentService commentService;

	public PublicIndexController() {
		super();
		newsService = new NewsService();
		commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numberOfItems = newsService.countItems();
		if (numberOfItems > 0) {
			int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));
				} else {
					currentPage = 1;
				}
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/notFound");
				return;
			}
			if (currentPage < 1 || currentPage > numberOfPages)
				currentPage = 1;

			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", currentPage);
			ArrayList<News> listNews = newsService.getListNews(offset);
			HashMap<Integer, String> listUserName = newsService.getListUserName(listNews);
			HashMap<Integer, Integer> listCountComment = commentService.getListCountComment(listNews);
			request.setAttribute("listCountComment", listCountComment);
			request.setAttribute("listNews", listNews);
			request.setAttribute("listUserName", listUserName);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
