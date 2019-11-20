package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Role;
import util.DBConnection;

public class RoleDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public ArrayList<Role> getListRole() {
		ArrayList<Role> listRole = new ArrayList<Role>();
		conn = DBConnection.getConnection();
		String sql = "select * from role  ORDER  BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				listRole.add(new Role(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listRole;
	}
	
	public ArrayList<Role> getItems() {
		ArrayList<Role> listRole = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM role ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				listRole.add(new Role(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return listRole;
	}
}
