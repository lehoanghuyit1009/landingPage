package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Role;
import model.bean.User;
import util.DBConnection;
import util.DefineUtil;

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
						new Role(rs.getInt("role"), rs.getString("name")), rs.getInt("enable"));
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

	public int countItems() {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(id) as count FROM user ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<User> getListUser(int offset) {
		ArrayList<User> list = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT u.*,r.* FROM user as u INNER JOIN role as r ON r.id = u.role ORDER BY u.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), rs.getString("email"),
						new Role(rs.getInt("u.role"), rs.getString("name")), rs.getInt("enable")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return list;
	}

	public void upDateActive(int trangthai, int id) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE user SET enable = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, trangthai);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
	}

	public int insertItem(User user) {
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO user(username,fullname,password,email,role) VALUE(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getFullname());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getEmail());
			pst.setInt(5, user.getRole().getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return 0;
	}

	public int isExitUsername(String username) {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(username) as count FROM user WHERE username =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return 0;
	}

	public int editItem(User user) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE user SET fullname=?,password=?,email=?,role=? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getFullname());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
			pst.setInt(4, user.getRole().getId());
			pst.setInt(5, user.getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return 0;
	}

	public int delItem(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM user WHERE id =?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}

	public User getItemByUsernamePasswordActive(String username, String password) {
		String sql = "SELECT u.*,r.* FROM user as u INNER JOIN role as r ON r.id = u.role WHERE u.username=? AND password =? AND enable=1";
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), rs.getString("email"),
						new Role(rs.getInt("role"), rs.getString("name")), rs.getInt("enable"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
