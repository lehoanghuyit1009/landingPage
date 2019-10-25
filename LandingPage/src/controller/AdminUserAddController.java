package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.Role;
import model.dao.UserDAO;


public class AdminUserAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminUserAddController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		if (userDAO.hasUser(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}

		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		int role = Integer.parseInt(request.getParameter("role"));

		
		User user = new User(0, username, fullname, password, email, 1, new Role(role, null));
		if (userDAO.addItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
			rd.forward(request, response);
		}
	}

}