package model.bean;

public class User {
	private int id;
	private String username;
	private String fullname;
	private String password;
	private String email;
	private Role role;
	private int enable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
	
	public void changeEnable() {
		int i = getEnable();
		if (i==1) setEnable(0);
		if (i==0) setEnable(1);
	}
	
	public String getNameOfEnable() {
		int i = getEnable();
		if (i==1) return "Active";
		else return "Inactive";
	}
	
	public String getColorEnable() {
		int i = getEnable();
		String content;
		if (i==1) content = "green";
		else content = "red";
		return content;
	}
	
	public String getIconEnable() {
		int i = getEnable();
		String content;
		if (i==1) content = "fas fa-check";
		else content = "fas fa-times";
		return content;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, String fullname, String password, String email, int enable, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enable = enable;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password
				+ ", email=" + email + ", role=" + role + ", enable=" + enable + "]";
	}

}
