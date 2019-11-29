package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.service.UserService;
import util.AuthUtil;

public class AdminUserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public AdminUserDeleteController() {
		super();
		this.userService = new UserService();
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
		if (userLogin.getId() != id && userLogin.getRole().getId() == 1) {
			User user = userService.getItemById(id);
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/notFound");
				return;
			}
			if (userService.delItem(id) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=3&page=" + page);
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0&page=" + page);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/notFound");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
