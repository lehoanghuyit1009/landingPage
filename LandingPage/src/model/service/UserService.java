package model.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.bean.User;
import model.dao.UserDAO;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO();
	}

	public User getItemById(int id) {
		return userDAO.getItemById(id);
	}

	public int countItems() {
		return userDAO.countItems();
	}

	public ArrayList<User> getListUser(int offset) {
		return userDAO.getListUser(offset);
	}

	public void upDateActive(int trangthai, int id) {
		userDAO.upDateActive(trangthai, id);
	}

	public int inserItem(User user) {
		return userDAO.insertItem(user);
	}

	public boolean isExitUsername(String username) {
		if (userDAO.isExitUsername(username) > 0) {
			return true;
		}
		return false;
	}

	public int editItem(User user) {
		return userDAO.editItem(user);
	}

	public int delItem(int id) {
		return userDAO.delItem(id);
	}

	public User getItemByUsernamePasswordActive(String username, String password) {
		return userDAO.getItemByUsernamePasswordActive(username, password);
	}

}
