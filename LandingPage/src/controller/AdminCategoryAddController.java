package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.service.CategoryService;

public class AdminCategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;

	public AdminCategoryAddController() {
		super();
		categoryService = new CategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (categoryService.insert(name) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=0");
		}
	}

}
