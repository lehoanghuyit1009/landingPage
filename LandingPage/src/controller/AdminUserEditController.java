package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Role;
import model.bean.Slide;
import model.bean.User;
import model.service.RoleService;
import model.service.UserService;
import util.AuthUtil;
import util.StringUtil;

public class AdminUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;

	public AdminUserEditController() {
		super();
		this.userService = new UserService();
		roleService = new RoleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User userLogin = AuthUtil.getUserLogin(request);
		ArrayList<Role> listRole = roleService.getListRole();
		request.setAttribute("listRole", listRole);
		int currentPage = 1, id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		// neu khong phai chinh id hoac k phai admin k dc sua

		if (userLogin.getId() == id || userLogin.getRole().getId() == 1) {
			User user = userService.getItemById(id);
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + currentPage);
				return;
			}
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/notFound");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1, id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		User userLogin = AuthUtil.getUserLogin(request);
		if (userLogin.getId() == id || userLogin.getRole().getId() == 1) {
			User user = userService.getItemById(id);
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
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
			Role role = roleService.getRoleById(rid);
			if (role == null) {
				response.sendRedirect(request.getContextPath() + "/notFound");
				return;
			}
			password = StringUtil.md5(password);
			user.setEmail(email);
			user.setFullname(fullname);
			user.setPassword(password);
			if (userLogin.getRole().getId() == 1) {
				user.setRole(role);
			}
			if (userService.editItem(user) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=1");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/notFound");
		}
	}

}
