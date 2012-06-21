package cn.edu.shu.enisp.db;

import java.sql.ResultSet;

public class DBResult {
	public int rowCount;
	public ResultSet mResultSet;
	
	public DBResult() {
		rowCount = -1;
	}
	
	public void reset() {
		rowCount = -1;
	}
}
