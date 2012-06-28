package cn.edu.shu.enisp.model;

// 代表公司之间关系的表和Java类映射
public class Cooperative {
	public static final String TABLENAME = "cooperativeenttable";
	
	public static final String ID = "num";
	public static final String SOURCEID = "id";
	public static final String TARGETID = "partnerid";
	
	private String id;
	private String sourceid;
	private String targetid;

    public Cooperative() {
    }

    public Cooperative(String sourceid, String targetid) {
        this.sourceid = sourceid;
        this.targetid = targetid;
    }
	
    public Cooperative(String id, String sourceid, String targetid) {
        this(sourceid, targetid);
        this.id = id;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
}
