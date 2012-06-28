package cn.edu.shu.enisp.db.mapper;

import cn.edu.shu.enisp.model.Test;

public class TestMapper extends BaseMapper<Test> {
    public static final String TABLENAME = "test";
    
    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String DES = "des";
    public static final String TIME = "time";

    public TestMapper(Test test) {
        super(test);
    }
    
    @Override
    public String getProperty(String field) {
        if (field.equals(ID)) {
            return model.getId();
        } else if (field.equals(TEXT)) {

        } else if (field.equals(DES)) {

        } else if (field.equals(TIME)) {

        }
        return "";
    }

    @Override
    public void setProperty(String field, String value) {

    }
}
