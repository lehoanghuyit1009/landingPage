package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnection;
import util.DefineUtil;

public class CategoryDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public ArrayList<Category> getListCategory(int offset) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				listCat.add(new Category(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listCat;
	}

	public int countItems() {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) as count FROM category";
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

	public int insert(String name) {
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO category VALUE(?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setString(2, name);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}

	public Category getItemById(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}

	public int edit(int id, Category category) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE `category` SET name=? WHERE id=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category.getName());
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}

	public int delItem(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM category WHERE id =?";
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

	public ArrayList<Category> getAllListCat() {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				listCat.add(new Category(rs.getInt("id"), rs.getString("name")));
			}
			return listCat;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public int countItemsSearch(String name) {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) as count FROM category WHERE name like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
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

	public ArrayList<Category> getItemsSearch(String name, int offset) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category WHERE name like ? ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				listCat.add(new Category(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listCat;
	}
}
