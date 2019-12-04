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
import model.bean.User;
import model.service.CategoryService;
import model.service.NewsService;
import util.AuthUtil;
import util.DefineUtil;
import util.FileUtil;

@MultipartConfig
public class AdminNewsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private CategoryService categoryService;

	public AdminNewsAddController() {
		super();
		categoryService = new CategoryService();
		newsService = new NewsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Category> listCat = categoryService.getAllListCat();
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// get filename from part
		Part part = request.getPart("image");
		// ten anh
		String fileName = FileUtil.rename(part.getSubmittedFileName());
		Category category = categoryService.getItemById(catId);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		User userLogin = AuthUtil.getUserLogin(request);
		if (userLogin == null) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}
		News news = new News(0, name, description, detail, address, fileName, 0, cost, null, category, area,
				userLogin.getId());
		if (newsService.insertItem(news) > 0) {
			// handle upload file if
			if (!"".equals(fileName)) {
				String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
				
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdir();
				}
				part.write(dirPath + File.separator + fileName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}
}
