package model.bean;

public class Category {
	private int id;
	private String name;
	private int parentCategoryId;
	
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
	}
	
	public Category(int id, String name, int parentCategoryId) {
		super();
		this.id = id;
		this.name = name;
		this.parentCategoryId = parentCategoryId;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
