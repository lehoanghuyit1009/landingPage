package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;

public class AdminUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminUserEditController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
			return;
		}

		User user = userDAO.getItem(id);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
			return;
		}
		request.setAttribute("user", user);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
			return;
		}

		User user = userDAO.getItem(id);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
			return;
		}

		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		int userRoleId = 3;
		try {
			userRoleId = Integer.parseInt(request.getParameter("user-role"));
		} catch (NumberFormatException e) {
		}

		if (!"".equals(password)) {
			user.setPassword(password);
		}
		user.setFullname(fullname);
		user.setEmail(email);
		user.getRole().setId(userRoleId);

		if (userDAO.editItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2&page=" + currentPage);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
			return;
		}

	}

}