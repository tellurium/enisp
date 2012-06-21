package cn.edu.shu.enisp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.shu.enisp.model.User;

public class UserHandler extends DBHandler {
	private PreparedStatement mPreparedStatement;
	private User mUser;
	
	@Override
	public User getObjectFromResultSet(ResultSet resultset)
			throws SQLException {
		User user = new User();
		user.setId(resultset.getString(User.ID));
		user.setUsername(resultset.getString(User.USERNAME));
		user.setPassword(resultset.getString(User.PASSWORD));
		user.setEnterpriseid(resultset.getString(User.ENTERPRISEID));
		return user;
	}

	@Override
	public PreparedStatement getInsertPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUser = (User)object; 
		mPreparedStatement = connection.prepareStatement("insert into " + User.TABLENAME +
			"("+User.USERNAME + ", " + User.PASSWORD + ", " + User.ENTERPRISEID +")" + 
			" values(?, ?, ?)");
		mPreparedStatement.setString(1, mUser.getUsername());
		mPreparedStatement.setString(2, mUser.getPassword());
		mPreparedStatement.setString(3, mUser.getEnterpriseid());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getDeletePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUser = (User)object; 
		mPreparedStatement = connection.prepareStatement("delete from " + User.TABLENAME + 
				" where " + User.ID + "= ?");
		mPreparedStatement.setString(1, mUser.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getUpdatePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUser = (User)object; 
		mPreparedStatement = connection.prepareStatement("update " + User.TABLENAME + 
				" set " + User.USERNAME + " = ?, " + User.PASSWORD + " = ? where " + 
				User.ID + " = ?");
		mPreparedStatement.setString(1, mUser.getUsername());
		mPreparedStatement.setString(2, mUser.getPassword());
		mPreparedStatement.setString(3, mUser.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getSelectPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mUser = (User)object; 
		mPreparedStatement = connection.prepareStatement("select * from " + User.TABLENAME + 
				" where " + User.ID + " = ?");
		mPreparedStatement.setString(1, mUser.getId());
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
