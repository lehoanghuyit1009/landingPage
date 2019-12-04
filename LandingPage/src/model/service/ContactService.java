package model.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.bean.Category;
import model.bean.Contact;
import model.dao.ContactDAO;

public class ContactService {
	private ContactDAO contactDAO;

	public ContactService() {
		this.contactDAO = new ContactDAO();
	}

	public int addItem(Contact contact) {
		return contactDAO.addItem(contact);
	}

	public int countItems() {

		return contactDAO.countItems();
	}

	public ArrayList<Contact> getListContact(int offset) {
		return contactDAO.getListContact(offset);
	}

	public void upDateActive(int trangthai, int id) {
		contactDAO.upDateActive(trangthai, id);
	}

	public Contact getItemById(int id) {
		return contactDAO.getItemById(id);
	}

	public int delItem(int id) {
		return contactDAO.delItem(id);
	}

	public int countItemsSearch(String name) {
		return contactDAO.countItemsSearch(name);
	}

	public ArrayList<Contact> getItemsSearch(String name, int offset) {
		return contactDAO.getItemsSearch(name, offset);
	}

}
