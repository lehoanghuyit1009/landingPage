package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.bean.User;
import model.service.CategoryService;
import model.service.CommentService;
import model.service.ContactService;
import model.service.NewsService;
import model.service.SlideService;
import model.service.UserService;
import util.AuthUtil;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private ContactService contactService;
	private SlideService slideService;
	private CommentService commentService;
	private NewsService newsService;
	private CategoryService categoryService;

	public AdminIndexController() {
		super();
		userService = new UserService();
		contactService = new ContactService();
		slideService = new SlideService();
		commentService = new CommentService();
		newsService = new NewsService();
		categoryService = new CategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int countUser = userService.countItems();
		int countContact = contactService.countItems();
		int countSlide = slideService.countItems();
		int countComment = commentService.countItems();
		int countNews = 0;
		
		User userLogin = AuthUtil.getUserLogin(request);
		News news = null;
		if ("user".equals(userLogin.getRole().getName())) {
			countNews = newsService.countItems(userLogin.getId());
		} else {
			countNews = newsService.countItems();
		}
		int countCat = categoryService.countItems();
		request.setAttribute("countCat", countCat);
		request.setAttribute("countContact", countContact);
		request.setAttribute("countSlide", countSlide);
		request.setAttribute("countComment", countComment);
		request.setAttribute("countUser", countUser);
		request.setAttribute("countNews", countNews);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/index.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
