package model.bean;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String subject;
	private String content;
	private int status;
	private int idNews;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(int id, String name, String email, String subject, String content, int status, int idNews) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.idNews = idNews;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", content="
				+ content + ", status=" + status + ", idNews=" + idNews + "]";
	}

}
