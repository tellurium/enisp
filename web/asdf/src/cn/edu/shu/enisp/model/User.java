package cn.edu.shu.enisp.model;

public class User {
    public static final String TABLENAME = "user";

    public static final String ID = "id";
    public static final String USERNAME = "username"; 
    public static final String PRIVILEGE = "privilege"; 
    public static final String PASSWORD = "password"; 
    public static final String TIME = "time"; 

    private String id;
    private String username;
    private String privilege;
    private String password;
    private String time;

    public User() {
    }
    
    public User(String username, String privilege, String password, String time) {
        this.username = username;
        this.privilege = privilege;
        this.password = password;
        this.time = time;
    }

    public User(String id, String username, String privilege, String password, String time) {
        this(username, privilege, password, time);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPrivilege() {
        return this.privilege;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
