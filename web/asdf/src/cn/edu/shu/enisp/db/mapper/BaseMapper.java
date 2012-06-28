package cn.edu.shu.enisp.db.mapper;

public abstract class BaseMapper<T> {
    public static final String TABLENAME = "base";

    public static final String ID = "id";

    public T model;

    public BaseMapper(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract String getProperty(String field);
    public abstract void setProperty(String field, String value);
}
