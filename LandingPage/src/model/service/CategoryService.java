package model.service;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.CommentDAO;
import model.dao.NewsDAO;
import util.DefineUtil;

public class CategoryService {
	private CategoryDAO categoryDAO;
	private NewsDAO newsDAO;
	private CommentDAO commentDAO;

	public CategoryService() {
		this.commentDAO = new CommentDAO();
		this.newsDAO = new NewsDAO();
		this.categoryDAO = new CategoryDAO();
	}

	public ArrayList<Category> getListCategory(int offset) {
		return categoryDAO.getListCategory(offset);
	}

	public int countItems() {
		return categoryDAO.countItems();
	}

	public int insert(String name) {
		return categoryDAO.insert(name);
	}

	public Category getItemById(int id) {
		return categoryDAO.getItemById(id);
	}

	public int edit(int id, Category category) {
		return categoryDAO.edit(id, category);
	}

	public int delItem(int id, HttpServletRequest request) {
		ArrayList<News> listPicture = newsDAO.getListNewsByCid(id);
		if (categoryDAO.delItem(id) > 0) {
			if (listPicture != null) {
				for (News news : listPicture) {
					if (!"".equals(news.getPicture())) {
						String path = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD
								+ File.separator + news.getPicture();
						File file = new File(path);
						file.delete();
					}
					commentDAO.deleteItemByNewsID(news.getId());
				}
			}
			newsDAO.delItemByCid(id);
			return 1;
		} else
			return 0;
	}

	public ArrayList<Category> getAllListCat() {
		return categoryDAO.getAllListCat();
	}

}
