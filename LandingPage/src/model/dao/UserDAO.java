package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Role;
import model.bean.User;
import util.DBConnection;

public class UserDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public User getItemById(int id) {
		String sql = "SELECT u.*,r.* FROM user as u INNER JOIN role as r ON r.id = u.role WHERE u.id =?";
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), rs.getString("email"),
						new Role(rs.getInt("id"), rs.getString("name")), rs.getInt("enable"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFullNameByUserId(int idUser) {
		conn = DBConnection.getConnection();
		String sql = "SELECT fullname FROM user WHERE id = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString("fullname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}

	
}
