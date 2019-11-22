package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import model.service.CategoryService;

public class AdminCategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;

	public AdminCategoryDeleteController() {
		super();
		categoryService = new CategoryService();
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
		Category objCat = categoryService.getItemById(id);
		if (objCat == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		if (categoryService.delItem(id,request) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=3&page=" + page);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=0&page=" + page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
