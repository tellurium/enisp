package cn.edu.shu.enisp.model;

// 公司表和Java类映射
public class Enterprise extends BaseModel {
	public static final String TABLENAME = "enterprisetable";
	
	public static final String ID = "id";
	public static final String ENTERPRISENAME = "enterprisename";
	public static final String ESTABLISHMENTTIME = "establishmenttime";
	public static final String ADDRESS = "address";
	public static final String TELEPHONENUMBER = "telephonenumber";
	public static final String FAXNUMABER = "faxnumber";
	public static final String EMAIL = "email";
	public static final String OFFICALWEBSITE = "officalwebsite";
	public static final String LOGOPIC= "logopic";
	
    public Enterprise() {
        super();
    }
    
    public Enterprise(String enterprisename, String establishmenttime, String address, 
            String telephonenumber, String faxnumber, String email, String officalwebsite, 
            String logopic) {
        this();
        modelMap.put(ENTERPRISENAME, enterprisename);
        modelMap.put(ESTABLISHMENTTIME, establishmenttime);
        modelMap.put(ADDRESS, address);
        modelMap.put(TELEPHONENUMBER, telephonenumber);

        modelMap.put(FAXNUMABER, faxnumber);
        modelMap.put(EMAIL, email);
        modelMap.put(OFFICALWEBSITE, officalwebsite);
        modelMap.put(LOGOPIC, logopic);
    }
    
    public Enterprise(String id, String enterprisename, String establishmenttime, String address, 
            String telephonenumber, String faxnumber, String email, String officalwebsite, 
            String logopic) {
        this(enterprisename, establishmenttime, address, telephonenumber, 
                faxnumber, email, officalwebsite, logopic);
        modelMap.put(ID, id);
    }

    @Override
    public String getID() {
        return Enterprise.ID;
    }

    @Override
    public String getTableName() {
        return Enterprise.TABLENAME;
    }

    @Override
    public void setUpMap() {
        _setUpMap(ID, ENTERPRISENAME, ESTABLISHMENTTIME, ADDRESS, 
                TELEPHONENUMBER, FAXNUMABER, EMAIL, OFFICALWEBSITE, LOGOPIC);
    }
}
