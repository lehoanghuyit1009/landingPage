package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.service.UserService;
import util.DefineUtil;

public class AdminUserIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public AdminUserIndexController() {
		super();
		this.userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numberOfItems = userService.countItems();
		if (numberOfItems > 0) {
			int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			String page = request.getParameter("page");
			try {
				if (page != null) {
					currentPage = Integer.parseInt(page);
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
			ArrayList<User> listUser = userService.getListUser(offset);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("numberOfItems", numberOfItems);
			request.setAttribute("listUser", listUser);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int trangthai = Integer.parseInt(request.getParameter("atrangthai"));
		int id = Integer.parseInt(request.getParameter("aid"));
		if (trangthai == 1) {
			trangthai = 0;
			out.print("<a href=\"javascript:void(0)\" onclick=\"return getActive(0," + id + ")\">" + "<img src=\""
					+ request.getContextPath() + "/template/admin/images/deactive.gif\" />" + "</a>");
		} else {
			trangthai = 1;
			out.print("<a href=\"javascript:void(0)\" onclick=\"return getActive(1," + id + ")\">" + "<img src=\""
					+ request.getContextPath() + "/template/admin/images/active.gif\" />" + "</a>");
		}
		userService.upDateActive(trangthai, id);
	}

}
