package cn.edu.shu.enisp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DBHandler {
	
	private Connection mConnection;
	private Statement mStatement;
	private PreparedStatement mPreparedStatement;
	private DBResult mDBResult = new DBResult();
	
	public enum ExecuteType {INSERT, DELETE, UPDATE, SELECT, QUERY};
	
	static {
		try {
			Class.forName(DBConfiguration.DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void startDBConnection() {
		try {
			mConnection = DriverManager.getConnection(DBConfiguration.DBURL, 
					DBConfiguration.USERNAME, DBConfiguration.USERPWD);
			mStatement = mConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDBConnection() {
		try {
			if (mDBResult.mResultSet != null) {
				mDBResult.mResultSet.close();
				mDBResult.mResultSet = null;
			}
			if (mPreparedStatement != null) {
				mPreparedStatement.close();
				mPreparedStatement = null;
			}
			if (mStatement != null) {
				mStatement.close();
				mStatement = null;
    		}
			if (mConnection != null) {
    			mConnection.close();
    			mConnection = null;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean executeDBOperation(ExecuteType type, Object object, List<Object> resultList) {
		boolean result = false;
		
		try {
			startDBConnection();
			
			switch (type) {
			case INSERT:
				mDBResult.rowCount = insertObjectToDB(object);
				break;
			case UPDATE:
				mDBResult.rowCount = updateObjectToDB(object);
				break;
			case DELETE:
				mDBResult.rowCount = deleteObjectFromDB(object);
				break;
			case SELECT:
				mDBResult.mResultSet = selectObjectFromDB(object);
				while(mDBResult.mResultSet.next()) {
					resultList.add(getObjectFromResultSet(mDBResult.mResultSet));
				}
				break;
			case QUERY:
				mDBResult.mResultSet = queryObjectFromDB(object);
				while(mDBResult.mResultSet.next()) {
					resultList.add(getObjectFromResultSet(mDBResult.mResultSet));
				}
				break;
			default:
				break;
			}
			
			if (mDBResult.rowCount > 0 || (resultList != null && resultList.size() > 0)) {
				result = true;
			}
		} catch(SQLException e) {
			
		} finally {
			closeDBConnection();
		}
		mDBResult.reset();
		return result;
	}
	
	public int insertObjectToDB(Object object) throws SQLException {
		mPreparedStatement = getInsertPreparedStatement(mConnection, object);
		return mPreparedStatement.executeUpdate();
	}

	public int deleteObjectFromDB(Object object) throws SQLException {
		mPreparedStatement = getDeletePreparedStatement(mConnection, object);
		return mPreparedStatement.executeUpdate();
	}

	public int updateObjectToDB(Object object) throws SQLException {
		mPreparedStatement = getUpdatePreparedStatement(mConnection, object);
		return mPreparedStatement.executeUpdate();
	}

	public ResultSet selectObjectFromDB(Object object) throws SQLException {
		mPreparedStatement = getSelectPreparedStatement(mConnection, object);
		return mPreparedStatement.executeQuery();
	}
	
	public ResultSet queryObjectFromDB(Object object) throws SQLException {
		mPreparedStatement = getQueryPreparedStatement(mConnection, object);
		return mPreparedStatement.executeQuery();
	}
	
	public abstract PreparedStatement getInsertPreparedStatement(Connection connection, Object object) throws SQLException;
	public abstract PreparedStatement getDeletePreparedStatement(Connection connection, Object object) throws SQLException;
	public abstract PreparedStatement getUpdatePreparedStatement(Connection connection, Object object) throws SQLException;
	public abstract PreparedStatement getSelectPreparedStatement(Connection connection, Object object) throws SQLException;
	public abstract PreparedStatement getQueryPreparedStatement(Connection connection, Object object) throws SQLException;
	
	public abstract Object getObjectFromResultSet(ResultSet resultset) throws SQLException;
}
