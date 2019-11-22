package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.service.CategoryService;
import util.DefineUtil;

public class AdminCategoryIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService;

	public AdminCategoryIndexController() {
		super();
		categoryService = new CategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numberOfItems = categoryService.countItems();
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
			ArrayList<Category> listCategory = categoryService.getListCategory(offset);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", numberOfItems);
			request.setAttribute("listCategory", listCategory);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
