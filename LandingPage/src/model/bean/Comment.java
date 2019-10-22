package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private String content;
	private int idUser;
	private int idNews;
	private Timestamp dateCreate;
	private int id_parent;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, String content, int idUser, int idNews, Timestamp dateCreate, int id_parent, int status) {
		super();
		this.id = id;
		this.content = content;
		this.idUser = idUser;
		this.idNews = idNews;
		this.dateCreate = dateCreate;
		this.id_parent = id_parent;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", idUser=" + idUser + ", idNews=" + idNews
				+ ", dateCreate=" + dateCreate + ", id_parent=" + id_parent + ", status=" + status + "]";
	}

}
