package cn.edu.shu.enisp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.shu.enisp.model.BaseModel;

public class DBOperator {

	private Connection mConnection;
	private Statement mStatement;
	private PreparedStatement mPreparedStatement;
	private DBResult mDBResult = new DBResult();

	private enum ExecuteType {INSERT, DELETE, UPDATE, SELECT};

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

    public boolean insertObjectToDB(BaseModel model) {
        if (executeUpdateOperation(ExecuteType.INSERT, model)) {
            ResultSet resultSet = executeQueryOperation(ExecuteType.SELECT, model);
        }
        return false;
    }

    public boolean deleteObjectFromDB(BaseModel model) {
        return executeUpdateOperation(ExecuteType.DELETE, model);
    }

    public boolean updateObjectToDB(BaseModel model) {
        return executeUpdateOperation(ExecuteType.UPDATE, model);
    }

    public ResultSet selectObjectFromDB(BaseModel model) {
        return executeQueryOperation(ExecuteType.SELECT, model);
    }

    public boolean executeUpdateOperation(ExecuteType type, BaseModel model) {
        boolean result = false;
        String statementString = null;

		try {
			startDBConnection();
			
            switch(type) {
            case INSERT:
                statementString = getInsertStatementString(model);
                break; 
            case DELETE:
                statementString = getDeleteStatementString(model);
                break;
            case UPDATE:
                statementString = getUpdateStatementString(model);
                break; 
            default:
                break;
            }
            if (statementString != null) {
                mStatement = mConnection.createStatement();
                int row_count = mStatement.executeUpdate(statementString);
                if (row_count > 0) {
                   result = true; 
                }
            }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeDBConnection();
		}
        return result;
    }

    public ResultSet executeQueryOperation(ExecuteType type, BaseModel model) {
        ResultSet result = null;
		try {
			startDBConnection();
			
            switch(type) {
            case SELECT:
                mPreparedStatement = getSelectPreparedStatement(model);
                break; 
            default:
                break;
            }
            if (mPreparedStatement != null) {
                result = mPreparedStatement.executeQuery();
            }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeDBConnection();
		}
        return result;
    }

	public String getInsertStatementString(BaseModel object) {
        StringBuilder fieldsStringBuilder = new StringBuilder();
        StringBuilder valueStringBuilder = new StringBuilder();
        Iterator iterator = object.getIterator();
        boolean firstFlag = true;
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            if (key.equals(object.getID())) {
               continue; 
            }
            if (!firstFlag) {
                fieldsStringBuilder.append(", ");
                valueStringBuilder.append(", ");
            } else {
                firstFlag = false;
            }
            fieldsStringBuilder.append(key);
            valueStringBuilder.append((String) entry.getValue());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(object.getTableName());
        sb.append(" (");
        sb.append(fieldsStringBuilder.toString());
        sb.append(") values (");
        sb.append(valueStringBuilder.toString());
        sb.append(")");

        System.out.println(sb.toString());
        /* return sb.toString(); */
        return null;
    }

	public String getDeleteStatementString(BaseModel object) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(object.getTableName());
        sb.append(" where ");
        sb.append(object.getID());
        sb.append(" = ");
        sb.append(object.getProperty(object.getID()));

        System.out.println(sb.toString());
        /* return sb.toString(); */
        return null;
    }

	public String getUpdateStatementString(BaseModel object) {
        StringBuilder fieldAndValueStringBuilder = new StringBuilder();
        Iterator iterator = object.getIterator();
        boolean firstFlag = true;
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            if (key.equals(object.getID())) {
               continue; 
            }
            if (!firstFlag) {
                fieldAndValueStringBuilder.append(", ");
            } else {
                firstFlag = false;
            }
            fieldAndValueStringBuilder.append(key);
            fieldAndValueStringBuilder.append("='");
            fieldAndValueStringBuilder.append((String) entry.getValue());
            fieldAndValueStringBuilder.append("'");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(object.getTableName());
        sb.append(" set ");
        sb.append(fieldAndValueStringBuilder.toString());
        sb.append(" where ");
        sb.append(object.ID);
        sb.append(" = ");
        sb.append(object.getProperty(object.ID));

        System.out.println(sb.toString());
        /* return sb.toString(); */
        return null;
    }
    
	public PreparedStatement getSelectPreparedStatement(BaseModel object) {
        StringBuilder sb = new StringBuilder(); 
        sb.append("select * from ");
        sb.append(object.getTableName());
        sb.append(" where ");

        String objectID = object.getProperty(object.getID());
        if (objectID  != null && objectID.length() > 0 ) {
            sb.append(object.getID());    
            sb.append(" = ");
            sb.append(objectID);
        } else {
            StringBuilder fieldAndValueStringBuilder = new StringBuilder();
            Iterator iterator = object.getIterator();
            boolean firstFlag = true;
            while(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                if (key.equals(object.getID())) {
                    continue; 
                }
                if (!firstFlag) {
                    fieldAndValueStringBuilder.append(" and ");
                } else {
                    firstFlag = false;
                }
                fieldAndValueStringBuilder.append(key);
                fieldAndValueStringBuilder.append("='");
                fieldAndValueStringBuilder.append((String) entry.getValue());
                fieldAndValueStringBuilder.append("'");
            }
            sb.append(fieldAndValueStringBuilder.toString());
        }

        System.out.println(sb.toString());
        return null;
    }
}
