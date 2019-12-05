package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Slide;
import model.dao.SlideDAO;
import model.service.SlideService;
import util.DefineUtil;
import util.FileUtil;

@MultipartConfig
public class AdminSlideAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SlideService slideService;

	public AdminSlideAddController() {
		super();
		this.slideService = new SlideService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String link = request.getParameter("link");
		int sort = 0;
		try {
			sort = Integer.parseInt(request.getParameter("sort"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/notFound");
			return;
		}

		Part part = request.getPart("image");
		String fileName = FileUtil.rename(part.getSubmittedFileName());

		Slide slide = new Slide(0, name, link, fileName, sort, 0);
		if (slideService.addItem(slide) > 0) {
			if (!"".equals(fileName)) {
				String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdir();
				}
				part.write(dirPath + File.separator + fileName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/slide/index?msg=1");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/add.jsp?msg=0");
			rd.forward(request, response);
		}
	}

}
