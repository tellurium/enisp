package cn.edu.shu.enisp.model;

// 用户表和Java类映射
public class UserInfo {
	public static final String TABLENAME = "userinfo";
	
	public static final String ID = "userid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ENTERPRISEID = "enterpriseid"; 
	
	private String id;
	private String username;
	private String password;
	private String enterpriseid;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String enterpriseid) {
        this.username = username;
        this.password = password;
        this.enterpriseid = enterpriseid;
    }

    public UserInfo(String id, String username, String password, String enterpriseid) {
        this(username, password, enterpriseid);
        this.id = id;
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserInfoname() {
		return username;
	}
	public void setUserInfoname(String username) {
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
