package cn.edu.shu.enisp.model;

// 公司表和Java类映射
public class Enterprise {
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
	
	private String id;
	private String enterprisename;
	private String establishmenttime;
	private String address;
	private String telephonenumber;
	private String faxnumaber;
	private String email;
	private String officalwebsite;
	private String logopic;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEnterprisename() {
		return enterprisename;
	}
	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}
	
	public String getEstablishmenttime() {
		return establishmenttime;
	}
	public void setEstablishmenttime(String establishmenttime) {
		this.establishmenttime = establishmenttime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTelephonenumber() {
		return telephonenumber;
	}
	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}
	
	public String getFaxnumaber() {
		return faxnumaber;
	}
	public void setFaxnumaber(String faxnumaber) {
		this.faxnumaber = faxnumaber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOfficalwebsite() {
		return officalwebsite;
	}
	public void setOfficalwebsite(String officalwebsite) {
		this.officalwebsite = officalwebsite;
	}
	
	public String getLogopic() {
		return logopic;
	}
	public void setLogopic(String logopic) {
		this.logopic = logopic;
	}
	
	
}
