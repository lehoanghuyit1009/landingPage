package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.News;
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
		
		String sql = "select * from category  ORDER  BY id ASC  LIMIT ?,?";
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
		String sql = "SELECT COUNT(*) AS count FROM category";
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
	
	public int update(String name,int id) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE category SET name=? WHERE id=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
	}
	
	public int countItemsAll() {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM category";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return result;
	}
	
	public ArrayList<Category> getItemsAllAndSort() {
		return this.getItemsAll(0);
	}
	
	public ArrayList<Category> getItemsAll(int parentId) {
		ArrayList<Category> result = new ArrayList<>();
		ArrayList<Category> list = this.getItemsByParentId(parentId);
		for (Category item : list) {
			result.add(item);
			result.addAll(this.getItemsAll(item.getId()));
		}
		return result;
	}
	
	public ArrayList<Category> getItemsByParentId(int id) {
		ArrayList<Category> listCategories = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category WHERE parent_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				listCategories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listCategories;
	}
	
	public Category getItem(int id) {
		Category category = null;
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				category = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return category;
	}
	
	
	
	private int delItem(int id) {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM category WHERE id = ?";
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
	
	public ArrayList<News> getItemsByCatId(int id) {
		ArrayList<News> listNewses = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM news  WHERE category = ? ORDER BY id ASC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				listNewses.add(new News(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("created_by"),
						rs.getString("picture"), null, rs.getInt("active")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return listNewses;
	}
	
	
	
	

	public int delete(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM category WHERE id=?";
		//;DELETE FROM news WHERE cid = ?;
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, id);
			//pst.setInt(2, id);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return result;
		
	}
	
	public int deleteNewsByCid(int id) {
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM news WHERE cid = ?";
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

	public int countSearchItems(String name) {
		Category category =null;
		int result = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM category WHERE name LIKE ?";
		try {
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			
			
			if (rs.next()) {
				result = rs.getInt("count");
				category = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return result;
	}

	public ArrayList<Category> getListSearchCategory(int offset,String name) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM category WHERE name LIKE ?  LIMIT ?, ?";
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


