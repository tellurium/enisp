package cn.edu.shu.enisp.model;

public class Test {
    private String id;
    private String text;
    private String des;
    private String time;

    public Test() {
    }

    public Test(String text, String des, String time) {
        this.text = text;
        this.des = des;
        this.time = time;
    }

    public Test(String id, String text, String des, String time) {
        this(text, des, time);
        this.id = id;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
