package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.javafx.geom.AreaOp.CAGOp;

import model.bean.Category;
import model.bean.News;
import model.service.CategoryService;
import model.service.NewsService;
import util.DefineUtil;
import util.FileUtil;

@MultipartConfig
public class AdminNewsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CategoryService categoryService;

	public AdminNewsEditController() {
		super();
		categoryService = new CategoryService();
		newsService = new NewsService();
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
		News news = newsService.getItemById(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		request.setAttribute("news", news);
		ArrayList<Category> listCat = categoryService.getAllListCat();
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0, page = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		News news = newsService.getItemById(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		String name = request.getParameter("name");
		int catId = 0;
		double area = 0d, cost = 0d;
		try {
			catId = Integer.parseInt(request.getParameter("category"));
			area = Double.parseDouble(request.getParameter("area"));
			cost = Double.parseDouble(request.getParameter("cost"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}

		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		String address = request.getParameter("address");

		Part part = request.getPart("image");
		String fileName = "";
		if (!"".equals(part.getSubmittedFileName())) {
			fileName = FileUtil.rename(part.getSubmittedFileName());
		} else {
			fileName = news.getPicture();
		}

		Category category = categoryService.getItemById(catId);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		// tin moi
		News objNews = new News(news.getId(), name, description, detail, address, fileName, 0, cost, null, category,
				area);
		if (newsService.editItem(objNews) > 0) {
			// handle upload file if
			if (!"".equals(part.getSubmittedFileName())) {
				String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdir();
				}
				part.write(dirPath + File.separator + fileName);
				String path = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD + File.separator
						+ news.getPicture();
				File file1 = new File(path);
				file1.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=2&page=" + page);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit?id=" + id + "&msg=0&page=" + page);
			rd.forward(request, response);
		}

	}

}
