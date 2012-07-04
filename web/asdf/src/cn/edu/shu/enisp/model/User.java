package cn.edu.shu.enisp.model;

public class User extends BaseModel {
    public static final String TABLENAME = "user";

    public static final String ID = "id";
    public static final String USERNAME = "username"; 
    public static final String PRIVILEGE = "privilege"; 
    public static final String PASSWORD = "password"; 
    public static final String TIME = "time"; 

    public static final String PRIVILEGE_NORMAL = "1";
    public static final String PRIVLIEGE_ADMIN = "2";
    public static final String PRIVLIEGE_ENTERPRISE = "3";

    public User() {
        super();
    }
    
    public User(String username, String privilege, String password, String time) {
        this();
        modelMap.put(USERNAME, username);
        modelMap.put(PRIVILEGE, privilege);
        modelMap.put(PASSWORD, password);
        modelMap.put(TIME, time);
    }

    public User(String id, String username, String privilege, String password, String time) {
        this(username, privilege, password, time);
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
        _setUpMap(ID, PRIVILEGE, USERNAME, PASSWORD, TIME);
    }
}
