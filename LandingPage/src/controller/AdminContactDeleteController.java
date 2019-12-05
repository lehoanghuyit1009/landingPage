package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.service.ContactService;

public class AdminContactDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService;

	public AdminContactDeleteController() {
		super();
		this.contactService = new ContactService();
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
		Contact objCat = contactService.getItemById(id);
		if (objCat == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		if (contactService.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?msg=3&page=" + page);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?msg=0&page=" + page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
