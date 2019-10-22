package model.bean;

import java.sql.Timestamp;

public class News {
	private int id;
	private String name;
	private String description;
	private String detail;
	private String address;
	private String picture;
	private int views;
	private double cost;
	private Timestamp dateCreate;
	private Category category;
	private double area;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public News(int id, String name, String description, String detail, String address, String picture, int views,
			double cost, Timestamp dateCreate, Category category, double area) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.detail = detail;
		this.address = address;
		this.picture = picture;
		this.views = views;
		this.cost = cost;
		this.dateCreate = dateCreate;
		this.category = category;
		this.area = area;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", name=" + name + ", description=" + description + ", detail=" + detail
				+ ", address=" + address + ", picture=" + picture + ", views=" + views + ", cost=" + cost
				+ ", dateCreate=" + dateCreate + ", category=" + category + ", area=" + area + "]";
	}

}
