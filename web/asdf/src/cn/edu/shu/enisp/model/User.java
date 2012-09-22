package cn.edu.shu.enisp.model;

// 政府用户
public class User extends BaseModel {
    public static final String TABLENAME = "user";

    public static final String ID = "id";
    public static final String USERNAME = "username"; 
    public static final String PRIVILEGE = "privilege"; 
    public static final String PASSWORD = "password"; 
    public static final String STATUS = "status"; 

    public static final String PRIVILEGE_NORMAL = "1";
    public static final String PRIVLIEGE_ADMIN = "2";
    public static final String PRIVLIEGE_ENTERPRISE = "3";

    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";

    public User() {
        super();
    }
    
    public User(String username, String privilege, String password) {
        this();
        modelMap.put(USERNAME, username);
        modelMap.put(PRIVILEGE, privilege);
        modelMap.put(PASSWORD, password);
    }

    public User(String id, String username, String privilege, String password) {
        this(username, privilege, password);
        modelMap.put(ID, id);
    }

    @Override
    public String getID() {
        return User.ID;
    }

    @Override
    public String getTableName() {
        return User.TABLENAME;
    }

    @Override
    public void setUpMap() {
        _setUpMap(ID, PRIVILEGE, USERNAME, PASSWORD);
    }
}
