package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import util.DBConnection;
import util.DefineUtil;

public class ContactDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public int addItem(Contact contact) {
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO contact(name,email,subject,content,status) VALUE(?,?,?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getSubject());
			pst.setString(4, contact.getContent());
			pst.setInt(5, contact.getStatus());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}

	public int countItems() {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) as count FROM contact";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return result;
	}

	public ArrayList<Contact> getListContact(int offset) {
		ArrayList<Contact> list = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM contact ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("subject"), rs.getString("content"), rs.getInt("status")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}

	public void upDateActive(int trangthai, int id) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE contact SET status = ? WHERE id = ?";
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

	public Contact getItemById(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM contact WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("subject"), rs.getString("content"), rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return null;
	}

	public int delItem(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM contact WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return 0;
	}
}
