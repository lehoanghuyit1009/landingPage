package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.Slide;
import model.service.CategoryService;
import model.service.CommentService;
import model.service.NewsService;
import model.service.SlideService;
import util.DefineUtil;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CommentService commentService;

	public PublicDetailController() {
		super();
		newsService = new NewsService();
		commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		News news = newsService.getItemById(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		Cookie[] cookies = request.getCookies();
		boolean check = true;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("itemNews-" + news.getId()) && cookie.getMaxAge() != 0) {
				check = false;
				break;
			}
		}
		if (check) {
			newsService.increaseView(id, news.getViews() + 1);
			Cookie cookie = new Cookie("itemNews-" + news.getId(), news.getId() + "");
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
		}
		ArrayList<News> listNews = new ArrayList<>();
		listNews.add(news);
		request.setAttribute("news", news);
		ArrayList<News> listRelate = newsService.getRelateItems(news.getCategory().getId(), id,
				DefineUtil.NUMBER_PER_PAGE);
		HashMap<Integer, Integer> listCountComment = commentService.getListCountComment(listNews);
		HashMap<Integer, String> listUserName = newsService.getListUserName(listNews);
		request.setAttribute("listCountComment", listCountComment);
		request.setAttribute("listRelate", listRelate);
		request.setAttribute("listUserName", listUserName);

		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
