package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Slide;
import util.DBConnection;
import util.DefineUtil;

public class SlideDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public int countItems() {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count FROM slide";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<Slide> getListSlide(int offset) {
		ArrayList<Slide> list = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM slide ORDER by id LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Slide(rs.getInt("id"), rs.getString("name"), rs.getString("link"), rs.getString("picture"),
						rs.getInt("sort"), rs.getInt("active")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addItem(Slide slide) {
		String sql = "INSERT INTO slide(name,link,picture,sort) VALUE(?,?,?,?)";
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, slide.getName());
			pst.setString(2, slide.getLink());
			pst.setString(3, slide.getPicture());
			pst.setInt(4, slide.getSort());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void upDateActive(int trangthai, int id) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE slide SET active = ? WHERE id = ?";
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

	public Slide getItem(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM slide WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Slide(rs.getInt("id"), rs.getString("name"), rs.getString("link"), rs.getString("picture"),
						rs.getInt("sort"), rs.getInt("active"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int editItem(Slide slide) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE slide SET name=?,link=?,sort=?,picture=? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, slide.getName());
			pst.setString(2, slide.getLink());
			pst.setInt(3, slide.getSort());
			pst.setString(4, slide.getPicture());
			pst.setInt(5, slide.getId());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return 0;
	}

	public ArrayList<Slide> getItems(int number) {
		ArrayList<Slide> listSlide = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM slide WHERE active = 1 ORDER BY sort ASC LIMIT ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				listSlide.add(new Slide(rs.getInt("id"), rs.getString("name"), rs.getString("link"),
						rs.getString("picture"), rs.getInt("sort"), rs.getInt("active")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listSlide;
	}

}
