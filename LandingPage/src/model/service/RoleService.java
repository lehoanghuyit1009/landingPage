package model.service;

import java.util.ArrayList;

import model.bean.Role;
import model.dao.RoleDAO;

public class RoleService {
	private RoleDAO roleDAO;

	public RoleService() {
		this.roleDAO = new RoleDAO();
	}

	public ArrayList<Role> getListRole() {
		return roleDAO.getListRole();
	}

	public Role getRoleById(int rid) {
		return roleDAO.getRoleById(rid);
	}
}
