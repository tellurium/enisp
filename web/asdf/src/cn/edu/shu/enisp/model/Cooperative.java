package cn.edu.shu.enisp.model;

public class Cooperative extends BaseModel {
	public static final String TABLENAME = "cooperativeenttable";
	
	public static final String ID = "num";
	public static final String SOURCEID = "id";
	public static final String TARGETID = "partnerid";

    public Cooperative() {
        super();
    }

    public Cooperative(String sourceid, String targetid) {
        this();
        modelMap.put(SOURCEID, sourceid);
        modelMap.put(TARGETID, targetid);
    }

    public Cooperative(String id, String sourceid, String targetid) {
        this(sourceid, targetid);
        modelMap.put(ID, id);
    }


    @Override
    public String getID() {
        return Cooperative.ID;
    }

    @Override
    public String getTableName() {
        return Cooperative.TABLENAME;
    }
	
    @Override
    public void setUpMap() {
        _setUpMap(ID, SOURCEID, TARGETID);
    }
}
