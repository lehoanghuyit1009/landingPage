package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Role;
import model.bean.User;
import model.service.RoleService;
import model.service.UserService;
import util.StringUtil;

public class AdminUserAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;

	public AdminUserAddController() {
		super();
		this.userService = new UserService();
		roleService = new RoleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Role> listRole = roleService.getListRole();
		request.setAttribute("listRole", listRole);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int rid = 0;
		try {
			rid = Integer.parseInt(request.getParameter("role"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		if (userService.isExitUsername(username)) {
			ArrayList<Role> listRole = roleService.getListRole();
			request.setAttribute("listRole", listRole);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
		}
		Role role = roleService.getRoleById(rid);
		if (role == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		password = StringUtil.md5(password);

		User user = new User(0, username, fullname, password, email, role, 0);
		if (userService.inserItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
		}
	}

}
