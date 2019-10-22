package model.bean;

public class Slide {
	private int id;
	private String link;
	private String picture;
	private int sort;
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Slide(int id, String link, String picture, int sort, String title) {
		super();
		this.id = id;
		this.link = link;
		this.picture = picture;
		this.sort = sort;
		this.title = title;
	}

	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", link=" + link + ", picture=" + picture + ", sort=" + sort + ", title=" + title
				+ "]";
	}

}
