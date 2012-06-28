package cn.edu.shu.enisp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.shu.enisp.model.UserInfo;

public class UserInfoHandler extends DBHandler {
	private PreparedStatement mPreparedStatement;
	private UserInfo mUserInfo;
	
	@Override
	public UserInfo getObjectFromResultSet(ResultSet resultset)
			throws SQLException {
		UserInfo user = new UserInfo();
		user.setId(resultset.getString(UserInfo.ID));
		user.setUserInfoname(resultset.getString(UserInfo.USERNAME));
		user.setPassword(resultset.getString(UserInfo.PASSWORD));
		user.setEnterpriseid(resultset.getString(UserInfo.ENTERPRISEID));
		return user;
	}

	@Override
	public PreparedStatement getInsertPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUserInfo = (UserInfo)object; 
		mPreparedStatement = connection.prepareStatement("insert into " + UserInfo.TABLENAME +
			"("+UserInfo.USERNAME + ", " + UserInfo.PASSWORD + ", " + UserInfo.ENTERPRISEID +")" + 
			" values(?, ?, ?)");
		mPreparedStatement.setString(1, mUserInfo.getUserInfoname());
		mPreparedStatement.setString(2, mUserInfo.getPassword());
		mPreparedStatement.setString(3, mUserInfo.getEnterpriseid());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getDeletePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUserInfo = (UserInfo)object; 
		mPreparedStatement = connection.prepareStatement("delete from " + UserInfo.TABLENAME + 
				" where " + UserInfo.ID + "= ?");
		mPreparedStatement.setString(1, mUserInfo.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getUpdatePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUserInfo = (UserInfo)object; 
		mPreparedStatement = connection.prepareStatement("update " + UserInfo.TABLENAME + 
				" set " + UserInfo.USERNAME + " = ?, " + UserInfo.PASSWORD + " = ? where " + 
				UserInfo.ID + " = ?");
		mPreparedStatement.setString(1, mUserInfo.getUserInfoname());
		mPreparedStatement.setString(2, mUserInfo.getPassword());
		mPreparedStatement.setString(3, mUserInfo.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getSelectPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUserInfo = (UserInfo)object; 
		mPreparedStatement = connection.prepareStatement("select * from " + UserInfo.TABLENAME + 
				" where " + UserInfo.ID + " = ?");
		mPreparedStatement.setString(1, mUserInfo.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getQueryPreparedStatement(Connection connection,
			Object object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
