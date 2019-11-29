package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.bean.User;
import model.service.CategoryService;
import model.service.CommentService;
import model.service.ContactService;
import model.service.NewsService;
import model.service.SlideService;
import model.service.UserService;
import util.AuthUtil;
import util.StringUtil;

public class PublicProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public PublicProfileController() {
		super();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/profile.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		password = StringUtil.md5(password);
		User user = AuthUtil.getUserLoginPublic(request);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		user.setEmail(email);
		user.setFullname(fullname);
		user.setPassword(password);
		if (userService.editItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/profile?msg=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/profile?msg=0");
		}
	}

}
