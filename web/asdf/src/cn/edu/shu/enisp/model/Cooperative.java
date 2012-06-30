package cn.edu.shu.enisp.model;

// ����˾֮���ϵ�ı��Java��ӳ��
public class Cooperative extends BaseModel {
	public static final String TABLENAME = "cooperativeenttable";
	
	public static final String ID = "num";
	public static final String SOURCEID = "id";
	public static final String TARGETID = "partnerid";

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
        putMappingField(ID);
        putMappingField(SOURCEID);
        putMappingField(TARGETID);
    }

}
