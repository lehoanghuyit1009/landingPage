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
		String sql = "select * from category LIMIT ?,?  ORDER  BY id DESC";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}
}
