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

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CategoryService categoryService;
	private CommentService commentService;

	public PublicCatController() {
		super();
		newsService = new NewsService();
		commentService = new CommentService();
		categoryService = new CategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}

		int numberOfItems = newsService.countItemsByCatId(id);
		if (numberOfItems > 0) {
			int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage < 1 || currentPage > numberOfPages)
				currentPage = 1;
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", currentPage);
			ArrayList<News> listNews = newsService.getItemsByCatIdPagination(id, offset);
			HashMap<Integer, String> listUserName = newsService.getListUserName(listNews);
			HashMap<Integer, Integer> listCountComment = commentService.getListCountComment(listNews);
			request.setAttribute("listCountComment", listCountComment);
			request.setAttribute("listNews", listNews);
			request.setAttribute("listUserName", listUserName);
		}
		Category category = categoryService.getItemById(id);
		request.setAttribute("category", category);
		RequestDispatcher rd = request.getRequestDispatcher("/public/category.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
