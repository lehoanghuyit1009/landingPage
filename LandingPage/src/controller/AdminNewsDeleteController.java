package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.CategoryDAO;
import model.service.CategoryService;
import model.service.NewsService;
import util.AuthUtil;

public class AdminNewsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;

	public AdminNewsDeleteController() {
		super();
		newsService = new NewsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0, page = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		User userLogin = AuthUtil.getUserLogin(request);
		News news = null;
		if ("user".equals(userLogin.getRole().getName())) {
			news = newsService.getItemByIdAndUid(id, userLogin.getId());
		} else {
			news = newsService.getItemById(id);
		}

		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		if (newsService.delItem(id, request) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=3&page=" + page);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=0&page=" + page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
