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

public class AdminCategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;

	public AdminCategoryEditController() {
		super();
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
		Category objCat = categoryService.getItemById(id);
		if (objCat == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		request.setAttribute("category", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		String name = request.getParameter("name");
		if ("".equals(name)) {
			// err =1: name trong. validate
			response.sendRedirect(request.getContextPath() + "/admin/category/edit?id=" + id + "&err=1&page=" + page);
			return;
		}
		Category category = new Category(id, name);
		if (categoryService.edit(id, category) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=2&page=" + page);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/category/edit?id=" + id + "&msg=0&page=" + page);
		}
	}

}
