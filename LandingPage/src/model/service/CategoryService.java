package model.service;

import java.util.ArrayList;

import model.bean.Category;
import model.dao.CategoryDAO;

public class CategoryService {
	private CategoryDAO categoryDAO;

	public CategoryService() {
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

}
