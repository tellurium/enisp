package cn.edu.shu.enisp.model;

public class Test extends BaseModel {
    public static final String TABLENAME = "test";

    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String DES = "des";
    public static final String TIME = "time";

    public Test(){
    }

    public Test(String text, String des, String time){
        modelMap.put(TEXT, text);
        modelMap.put(DES, des);
        modelMap.put(TIME, time);
    }

    public Test(String id, String text, String des, String time){
        this(text, des, time);
        modelMap.put(ID, id);
    }
    
    @Override
    public String getID() {
        return Test.ID;
    }

    @Override
    public String getTableName() {
        return Test.TABLENAME;
    }

    @Override
    public void setUpMap() {
        putMappingField(ID);
        putMappingField(TEXT);
        putMappingField(DES);
        putMappingField(TIME);
    }
}
