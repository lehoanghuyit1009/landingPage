package model.bean;

public class Slide {
	private int id;
	private String name;
	private String link;
	private String picture;
	private int sort;
	private int active;

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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Slide(int id, String name, String link, String picture, int sort, int active) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.picture = picture;
		this.sort = sort;
		this.active = active;
	}

	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", name=" + name + ", link=" + link + ", picture=" + picture + ", sort=" + sort
				+ ", active=" + active + "]";
	}

}
