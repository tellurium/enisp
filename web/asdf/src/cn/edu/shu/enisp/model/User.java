package cn.edu.shu.enisp.model;

// �û����Java��ӳ��
public class User {
	public static final String TABLENAME = "userinfo";
	
	public static final String ID = "userid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ENTERPRISEID = "enterpriseid"; 
	
	private String id;
	private String username;
	private String password;
	private String enterpriseid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
}
