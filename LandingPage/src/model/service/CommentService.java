package model.service;

import java.util.ArrayList;
import java.util.HashMap;

import model.bean.Comment;
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
		newsDAO = new  NewsDAO();
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
		for (Comment comment : listComment) {
			String newsName = newsDAO.getNewsName(comment.getIdNews());
			if (newsName != null && listNewsName.get(comment.getIdNews()) == null) {
				listNewsName.put(comment.getIdNews(), newsName);
			}
		}
		return listNewsName;
	}
}
