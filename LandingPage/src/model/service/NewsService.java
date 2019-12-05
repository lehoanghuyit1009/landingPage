package model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import util.DefineUtil;

public class NewsService {
	private NewsDAO newsDAO;
	private CommentDAO commentDAO;
	private UserDAO userDAO;

	public NewsService() {
		super();
		this.newsDAO = new NewsDAO();
		this.commentDAO = new CommentDAO();
		this.userDAO = new UserDAO();
	}

	public int countItems() {
		return newsDAO.countItems();
	}

	public ArrayList<News> getListNews(int offset) {
		return newsDAO.getListNews(offset);
	}

	public int insertItem(News news) {
		return newsDAO.insertItem(news);
	}

	public News getItemById(int id) {
		return newsDAO.getItemById(id);
	}

	public int editItem(News news) {
		return newsDAO.editItem(news);
	}

	public int delItem(int id, HttpServletRequest request) {
		News news = newsDAO.getItemById(id);
		if (news == null) {
			return 0;
		}
		if (newsDAO.delItemById(id) > 0) {
			if (!"".equals(news.getPicture())) {
				String path = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD + File.separator
						+ news.getPicture();
				File file = new File(path);
				file.delete();
			}
			commentDAO.deleteItemByNewsID(news.getId());
			return 1;
		}

		return 0;
	}

	public HashMap<Integer, String> getListUserName(ArrayList<News> listNews) {
		HashMap<Integer, String> listUserName = new HashMap<>();
		if (listNews != null) {
			for (News news : listNews) {
				String usename = userDAO.getFullNameByUserId(news.getCreateBy());
				if (usename != null && listUserName.get(news.getCreateBy()) == null) {
					listUserName.put(news.getCreateBy(), usename);
				}
			}
			return listUserName;
		}
		return null;
	}

	public ArrayList<News> getPopularItems(int numberPerPage) {
		return newsDAO.getPopularItems(numberPerPage);
	}

	public void increaseView(int id, int views) {
		newsDAO.increaseView(id, views);
	}

	public ArrayList<News> getRelateItems(int cid, int id, int number) {
		return newsDAO.getRelateItems(cid, id, number);
	}

	public int countItemsByCatId(int id) {
		return newsDAO.countItemsByCatId(id);
	}

	public ArrayList<News> getItemsByCatIdPagination(int id, int offset) {
		return newsDAO.getItemsByCatIdPagination(id, offset);
	}

}
