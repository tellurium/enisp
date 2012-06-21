package cn.edu.shu.enisp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.shu.enisp.model.Enterprise;

public class EnterpriseHandler extends DBHandler {
	private PreparedStatement mPreparedStatement;
	private Enterprise mEnterprise;
	
	@Override
	public Object getObjectFromResultSet(ResultSet resultset)
			throws SQLException {
		Enterprise enterprise = new Enterprise();
		enterprise.setId(resultset.getString(Enterprise.ID));
		enterprise.setAddress(resultset.getString(Enterprise.ADDRESS));
		enterprise.setEmail(resultset.getString(Enterprise.EMAIL));
		enterprise.setEnterprisename(resultset.getString(Enterprise.ENTERPRISENAME));
		enterprise.setEstablishmenttime(resultset.getString(Enterprise.ESTABLISHMENTTIME));
		enterprise.setFaxnumaber(resultset.getString(Enterprise.FAXNUMABER));
		enterprise.setLogopic(resultset.getString(Enterprise.LOGOPIC));
		enterprise.setOfficalwebsite(resultset.getString(Enterprise.OFFICALWEBSITE));
		enterprise.setTelephonenumber(resultset.getString(Enterprise.TELEPHONENUMBER));
		return enterprise;
	}

	@Override
	public PreparedStatement getInsertPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mEnterprise = (Enterprise)object;
		mPreparedStatement = connection.prepareStatement("insert into " + Enterprise.TABLENAME +
				"("+Enterprise.ADDRESS + ", " + Enterprise.EMAIL + ", " + Enterprise.ENTERPRISENAME +
				", " + Enterprise.FAXNUMABER + ", " + Enterprise.OFFICALWEBSITE +
				", " + Enterprise.TELEPHONENUMBER + ", " + Enterprise.ESTABLISHMENTTIME + ")" + 
				" values(?, ?, ?, ?, ?, ?, ?)");
//		mPreparedStatement = connection.prepareStatement("insert into " + Enterprise.TABLENAME +
//				"("+Enterprise.ADDRESS + ", " + Enterprise.EMAIL + ", " + Enterprise.ENTERPRISENAME +
//				", " + Enterprise.FAXNUMABER + ", " + Enterprise.OFFICALWEBSITE +
//				", " + Enterprise.TELEPHONENUMBER + ")" + 
//				" values(?, ?, ?, ?, ?, ?)");
		mPreparedStatement.setString(1, mEnterprise.getAddress());
		mPreparedStatement.setString(2, mEnterprise.getEmail());
		mPreparedStatement.setString(3, mEnterprise.getEnterprisename());
		mPreparedStatement.setString(4, mEnterprise.getFaxnumaber());
//		mPreparedStatement.setString(5, mEnterprise.getLogopic());
		mPreparedStatement.setString(5, mEnterprise.getOfficalwebsite());
		mPreparedStatement.setString(6, mEnterprise.getTelephonenumber());
		mPreparedStatement.setString(7, mEnterprise.getEstablishmenttime());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getDeletePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mEnterprise = (Enterprise)object;
		mPreparedStatement = connection.prepareStatement("delete from " + Enterprise.TABLENAME + 
				" where " + Enterprise.ID + "= ?");
		mPreparedStatement.setString(1, mEnterprise.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getUpdatePreparedStatement(Connection connection,
			Object object) throws SQLException {
		mEnterprise = (Enterprise)object;
		mPreparedStatement = connection.prepareStatement("update " + Enterprise.TABLENAME + 
				" set " + Enterprise.ADDRESS + " = ?, " + Enterprise.EMAIL + " = ?, " + 
				Enterprise.ENTERPRISENAME + " = ?, " + Enterprise.FAXNUMABER + " = ?, " + Enterprise.LOGOPIC + 
				" = ?, " + Enterprise.OFFICALWEBSITE + " = ?, " + Enterprise.TELEPHONENUMBER + " = ?, " + 
				Enterprise.ESTABLISHMENTTIME + 
				" = ? where " + Enterprise.ID + " = ?");
		mPreparedStatement.setString(1, mEnterprise.getAddress());
		mPreparedStatement.setString(2, mEnterprise.getEmail());
		mPreparedStatement.setString(3, mEnterprise.getEnterprisename());
		mPreparedStatement.setString(4, mEnterprise.getFaxnumaber());
		mPreparedStatement.setString(5, mEnterprise.getLogopic());
		mPreparedStatement.setString(6, mEnterprise.getOfficalwebsite());
		mPreparedStatement.setString(7, mEnterprise.getTelephonenumber());
		mPreparedStatement.setString(8, mEnterprise.getEstablishmenttime());
		mPreparedStatement.setString(9, mEnterprise.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getSelectPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mEnterprise = (Enterprise)object;
		mPreparedStatement = connection.prepareStatement("select * from " + Enterprise.TABLENAME + 
				" where " + Enterprise.ADDRESS + " = ? and " + Enterprise.EMAIL + " = ? and " + 
				Enterprise.ENTERPRISENAME + " = ? and " + Enterprise.FAXNUMABER + " = ? and " + 
				Enterprise.OFFICALWEBSITE + " = ? and " + Enterprise.TELEPHONENUMBER + " = ?");
		mPreparedStatement.setString(1, mEnterprise.getAddress());
		mPreparedStatement.setString(2, mEnterprise.getEmail());
		mPreparedStatement.setString(3, mEnterprise.getEnterprisename());
		mPreparedStatement.setString(4, mEnterprise.getFaxnumaber());
		mPreparedStatement.setString(5, mEnterprise.getOfficalwebsite());
		mPreparedStatement.setString(6, mEnterprise.getTelephonenumber());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}

	@Override
	public PreparedStatement getQueryPreparedStatement(Connection connection,
			Object object) throws SQLException {
		mEnterprise = (Enterprise)object;
		mPreparedStatement = connection.prepareStatement("select * from " + Enterprise.TABLENAME + 
				" where " + Enterprise.ID + " = ?");
		mPreparedStatement.setString(1, mEnterprise.getId());
		System.out.println(mPreparedStatement.toString());
		return mPreparedStatement;
	}
}
