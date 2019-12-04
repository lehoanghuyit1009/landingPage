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

public class NewsDAO {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	private Connection conn;

	public String getNewsName(int idNews) {
		conn = DBConnection.getConnection();
		String sql = "SELECT name FROM news WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}

	public ArrayList<News> getListNewsByCid(int id) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE cid = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return list;
	}

	public int delItemByCid(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM news WHERE cid =?";
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

	public int countItems() {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from news";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return 0;
	}

	public ArrayList<News> getListNews(int offset) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}

	}

	public int insertItem(News news) {
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO `news`(`name`, `description`, `detail`, `picture`, `address`, `area`, `cost`, `cid`,createBy) VALUES (?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getDescription());
			pst.setString(3, news.getDetail());
			pst.setString(4, news.getPicture());
			pst.setString(5, news.getAddress());
			pst.setDouble(6, news.getArea());
			pst.setDouble(7, news.getCost());
			pst.setInt(8, news.getCategory().getId());
			pst.setInt(9, news.getCreateBy());
			result = pst.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.close(pst, conn);
		}

	}

	public News getItemById(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE n.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				return new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}

	public int editItem(News news) {

		conn = DBConnection.getConnection();
		String sql = "UPDATE `news` SET name=?,`description`=?,`detail`=?,`picture`=?,`address`=?,`area`=?,`cost`=?,`cid`=? WHERE id=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getDescription());
			pst.setString(3, news.getDetail());
			pst.setString(4, news.getPicture());
			pst.setString(5, news.getAddress());
			pst.setDouble(6, news.getArea());
			pst.setDouble(7, news.getCost());
			pst.setInt(8, news.getCategory().getId());
			pst.setInt(9, news.getId());
			result = pst.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
		return 0;
	}

	public int delItemById(int id) {
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM news WHERE id =?";
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

	public ArrayList<News> getPopularItems(int numberPerPage) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id ORDER BY view DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, 3);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public void increaseView(int id, int views) {
		conn = DBConnection.getConnection();
		String sql = "UPDATE news SET view =?  WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, views);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pst, conn);
		}
	}

	public ArrayList<News> getRelateItems(int cid, int id, int number) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE c.id=? AND n.id!=? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, id);
			pst.setInt(3, 0);
			pst.setInt(4, 3);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public int countItemsByCatId(int id) {
		int count = 0;
		conn = DBConnection.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE cid = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return count;
	}

	public ArrayList<News> getItemsByCatIdPagination(int cid, int offset) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE c.id=? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, 0);
			pst.setInt(3, 3);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public int countItemsSearch(String name) {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from news WHERE name like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return 0;
	}

	public ArrayList<News> getItemsSearch(String name, int offset) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE n.name LIKE ? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public int countItems(int id) {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from news WHERE createBy=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<News> getListNews(int offset, int id) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE createBy=? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public News getItemByIdAndUid(int id, int uid) {
		conn = DBConnection.getConnection();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE n.id=? AND createBy=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, uid);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
		return null;
	}


	public ArrayList<News> getListNews(int offset, int id, String name) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE createBy=? AND n.name like ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, "%" + name + "%");
			pst.setInt(3, offset);
			pst.setInt(4, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

	public int countItems(int id, String name) {
		conn = DBConnection.getConnection();
		String sql = "SELECT count(*) as count from news WHERE name like =? AND createBy =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return 0;
	}

	public ArrayList<News> getListNewsSearch(int offset, String name) {
		conn = DBConnection.getConnection();
		ArrayList<News> list = new ArrayList<>();
		String sql = "SELECT c.*,n.* FROM news as n INNER JOIN category as c on n.cid = c.id WHERE n.name like ? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new News(rs.getInt("n.id"), rs.getString("n.name"), rs.getString("description"),
						rs.getString("detail"), rs.getString("address"), rs.getString("picture"), rs.getInt("view"),
						rs.getDouble("cost"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getDouble("area"),
						rs.getInt("createBy")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(rs, pst, conn);
		}
	}

}
