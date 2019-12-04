package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.NewsDAO;
import model.service.NewsService;
import util.AuthUtil;
import util.DefineUtil;

public class AdminNewsSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;

	public AdminNewsSearchController() {
		super();
		this.newsService = new NewsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == null || "".equals(name)) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		name = name.trim();
		User userLogin = AuthUtil.getUserLogin(request);
		int numberOfItems = 0;
		if ("user".equals(userLogin.getRole().getName())) {
			numberOfItems = newsService.countItems(userLogin.getId(),name);
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

				ArrayList<News> listNews = newsService.getListNews(offset, userLogin.getId(),name);
				request.setAttribute("numberOfPages", numberOfPages);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("numberOfItems", numberOfItems);
				request.setAttribute("listNews", listNews);
			}
		} else {
			numberOfItems = newsService.countItems(name);
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

				ArrayList<News> listNews = newsService.getListNewsSearch(offset,name);
				request.setAttribute("numberOfPages", numberOfPages);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("numberOfItems", numberOfItems);
				request.setAttribute("listNews", listNews);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
