package cn.edu.shu.enisp.model;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseModel {
    public static final String TABLENAME = "base";

    public static final String ID = "id";

    protected Map<String, String> modelMap = new HashMap<String, String>();

    protected abstract void setUpMap();
    public abstract String getTableName();
    public abstract String getID();

    public BaseModel() {
        setUpMap();
    }

    public int getFieldSize() {
        return modelMap.size();
    }

    public Iterator getIterator() {
        return modelMap.entrySet().iterator();
    }

    private void putMappingField(String field) {
        modelMap.put(field, "");
    }

    public String getProperty(String field) {
        return modelMap.get(field);
    }

    public void setProperty(String field, String value) {
        modelMap.put(field, value);
    }

    public List<BaseModel> getModelListFromResultSet(ResultSet resultSet) {
        return null;
    }

    protected void _setUpMap(String ... fields) {
        for (int i = 0; i < fields.length; i++) {
            this.putMappingField(fields[i]);
        }
    }
}
