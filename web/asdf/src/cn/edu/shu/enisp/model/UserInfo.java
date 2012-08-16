package cn.edu.shu.enisp.model;

public class UserInfo extends BaseModel {
	public static final String TABLENAME = "userinfo";
	
	public static final String ID = "userid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ENTERPRISEID = "enterpriseid"; 
	
    public UserInfo() {
        super();
    }

    public UserInfo(String username, String password, String enterpriseid) {
        this();
        modelMap.put(USERNAME, username);
        modelMap.put(PASSWORD, password);
        modelMap.put(ENTERPRISEID, enterpriseid);
    }

    public UserInfo(String id, String username, String password, String enterpriseid) {
        this(username, password, enterpriseid);
        modelMap.put(ID, id);
    }
	
    @Override
    public String getID() {
        return UserInfo.ID;
    }

    @Override
    public String getTableName() {
        return UserInfo.TABLENAME;
    }

    @Override
    public void setUpMap() {
        _setUpMap(ID, USERNAME, PASSWORD, ENTERPRISEID);
    }
}
