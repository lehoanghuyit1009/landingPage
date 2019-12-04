package model.service;

import java.util.ArrayList;
import java.util.HashMap;

import model.bean.Comment;
import model.bean.News;
import model.bean.User;
import model.dao.CommentDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;

public class CommentService {
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	private NewsDAO newsDAO;

	public CommentService() {
		userDAO = new UserDAO();
		commentDAO = new CommentDAO();
		newsDAO = new NewsDAO();
	}

	public int countItems() {
		return commentDAO.countItems();
	}

	public ArrayList<Comment> getListComment(int offset) {
		return commentDAO.getListComment(offset);
	}

	public void upDateActive(int trangthai, int id) {
		commentDAO.upDateActive(trangthai, id);
	}

	public int deleteItem(int id) {
		return commentDAO.deleteItem(id);
	}

	public HashMap<Integer, String> getListUserName(ArrayList<Comment> listComment) {
		HashMap<Integer, String> listUserName = new HashMap<>();
		for (Comment comment : listComment) {
			String usename = userDAO.getFullNameByUserId(comment.getIdUser());
			if (usename != null && listUserName.get(comment.getIdUser()) == null) {
				listUserName.put(comment.getIdUser(), usename);
			}
		}
		return listUserName;
	}

	public HashMap<Integer, String> getListNewsName(ArrayList<Comment> listComment) {
		HashMap<Integer, String> listNewsName = new HashMap<>();
		if (listComment != null) {
			for (Comment comment : listComment) {
				String newsName = newsDAO.getNewsName(comment.getIdNews());
				if (newsName != null && listNewsName.get(comment.getIdNews()) == null) {
					listNewsName.put(comment.getIdNews(), newsName);
				}
			}
		}
		return listNewsName;
	}

	public HashMap<Integer, Integer> getListCountComment(ArrayList<News> listNews) {
		HashMap<Integer, Integer> listCountComment = new HashMap<>();
		if (listNews != null) {
			for (News news : listNews) {
				int count = commentDAO.countByNewsId(news.getId());
				listCountComment.put(news.getId(), count);
			}
		}
		return listCountComment;
	}

	public ArrayList<Comment> getListCommentByNid(int id, int i) {
		return commentDAO.getListCommentByNid(id, i);
	}

	public HashMap<Integer, ArrayList<Comment>> getListChildComment(ArrayList<Comment> listComment) {
		HashMap<Integer, ArrayList<Comment>> listChildComment = new HashMap<>();
		if (listComment != null && listComment.size() > 0) {
			for (Comment comment : listComment) {
				ArrayList<Comment> list = commentDAO.getListCommentByNid(comment.getIdNews(), comment.getId());
				listChildComment.put(comment.getId(), list);
			}
		}
		return listChildComment;
	}

	public HashMap<Integer, String> getlistUserNameComment(int id) {
		ArrayList<Integer> listIdUser = commentDAO.getIdUserByNid(id);
		HashMap<Integer, String> listUserNameComment = new HashMap<>();
		if (listIdUser != null || listIdUser.size() > 0) {
			for (Integer integer : listIdUser) {
				String usename = userDAO.getFullNameByUserId(integer);
				if (usename != null && listUserNameComment.get(integer) == null) {
					listUserNameComment.put(integer, usename);
				}
			}
		}
		return listUserNameComment;
	}

	public int insertItem(Comment comment) {
		return commentDAO.insert(comment);
	}

	public int countItemsSearch(String name) {
		return commentDAO.countItemsSearch(name);
	}

	public ArrayList<Comment> getItemsSearch(String name, int offset) {
		return commentDAO.getItemsSearch(name, offset);
	}
}
