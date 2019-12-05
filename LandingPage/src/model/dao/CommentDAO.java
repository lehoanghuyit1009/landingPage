package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Comment;
import util.DBConnection;
import util.DefineUtil;

public class CommentDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;
	
	public int countItems() {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from comment";
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

	public int insert(String name) {

		return 0;
	}

	public ArrayList<Comment> getListComment(int offset) {
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM comment ORDER BY id DESC LIMIT ?,?";
		ArrayList<Comment> listComment = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				listComment.add(
						new Comment(rs.getInt("id"), rs.getString("content"), rs.getInt("idUser"), rs.getInt("idNews"),
								rs.getTimestamp("dateCreate"), rs.getInt("id_parent"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listComment;
	}

	public void upDateActive(int trangthai, int id) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE comment SET status = ? WHERE id = ?";
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

	public int deleteItem(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE from comment WHERE id = ?";
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

	public void deleteItemByNewsID(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE from comment WHERE idNews = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
	}

	public int countByNewsId(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from comment WHERE idNews=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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

}
