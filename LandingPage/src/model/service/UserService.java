package model.service;

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
	
}
