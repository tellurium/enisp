package cn.yestar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.io.sql.ConnectionFactory;
import prefuse.data.io.sql.DatabaseDataSource;

public class DBHelper {
	public static final String driverName   = "com.mysql.jdbc.Driver";    
    public static final String dbURL        = "jdbc:mysql://localhost:3306/enispdb";    
    public static final String userName     = "root";    
    public static final String userPwd      = "987987";   
    public static Connection mConnection;
    public static Statement mStatement;
    public static ResultSet mResultSet;   
    public static DatabaseDataSource datasrc = null; 
    public static Graph mGraph;  

    public static Graph getDataFromDB() {
         try {    
             datasrc = ConnectionFactory.getDatabaseConnection(    
                     driverName, dbURL, userName, userPwd);        
             Table nodes = datasrc.getData("select id,enterprisename,address,officalwebsite,email,establishmenttime,telephonenumber from enterprisetable");    
             Table edges = datasrc.getData("select * from cooperativeenttable");   
             mGraph = new Graph(nodes, edges, false, "id", "id", "partnerid"); 
         } catch (Exception e) {    
             e.printStackTrace();    
         }
         return mGraph;
    }
    
    public static boolean addNodeToDB(Node node) {
    	System.out.println("Add a new node");
    	System.out.println(getAddNodeString(node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    	
    	executeSQLUpdateStatement(getAddNodeString(node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    	
    	int newNodeId = -1;
    	newNodeId = getNodeIdFromDB(node);
    	System.out.println("before node id is: " + node.getString(0));
    	node.setString(0, newNodeId + "");
    	System.out.println("after node id is: " + node.getString(0));
    	return true;
    }
    
    public static boolean updateNodeInDB(Node node) {
    	System.out.println("Update a node");
    	System.out.println(getUpdateNodeString(node.getString(0), node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    	executeSQLUpdateStatement(getUpdateNodeString(node.getString(0), node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    	return true;
    }
    
    public static boolean deleteNodeInDB(Node node) {
    	System.out.println("Delete a node");
    	System.out.println(getDeleteNodeString(node.getString(0)));
    	executeSQLUpdateStatement(getDeleteNodeString(node.getString(0)));
    	return true;
    }
    
    public static boolean addEdgeToDB(Edge edge) {
    	System.out.println("Add a edge");
    	executeSQLUpdateStatement(getAddEdgeString(edge.getSourceNode().getString(0), edge.getTargetNode().getString(0)));
    	
    	int newEdgeId = -1;
    	newEdgeId = getEdgeIdFromDB(edge);
    	System.out.println("before node id is: " + edge.getString(0));
    	edge.setString(0, newEdgeId + "");
    	System.out.println("after node id is: " + edge.getString(0));
    	return true;
    }
    
    public static boolean updateEdgeInDB(Edge edge) {
    	System.out.println("Update a edge");
    	executeSQLUpdateStatement(getUpdateEdgeString(edge.getString(0), edge.getString(1), edge.getString(2)));
    	return true;
    }
    
    public static boolean deleteEdgeInDB(Edge edge) {
    	System.out.println("Delete a node");
    	executeSQLUpdateStatement(getDeleteEdgeString(edge.getString(0)));
    	return true;
    }
    
    private static void executeSQLUpdateStatement(String statement) {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		mConnection = DriverManager.getConnection(dbURL, userName, userPwd);
    		mStatement = mConnection.createStatement();
    		mStatement.executeUpdate(statement);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (mStatement != null) {
    				mStatement.close();
        		}
    			if (mConnection != null) {
        			mConnection.close();
        		}
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private static int getNodeIdFromDB(Node node) {
    	int id = -1;
    	System.out.println(getQueryNodeIdString(node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		mConnection = DriverManager.getConnection(dbURL, userName, userPwd);
    		mStatement = mConnection.createStatement();
    		mResultSet = mStatement.executeQuery(getQueryNodeIdString(node.getString(1), node.getString(2), node.getString(3), node.getString(4),node.getString(5),node.getString(6)));
    		if (mResultSet == null) return id;
			while(mResultSet.next()) {
				id = mResultSet.getInt(1);
				if (id > -1) return id; 
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (mResultSet != null) {
    				mResultSet.close();
    			}
    			if (mStatement != null) {
    				mStatement.close();
        		}
    			if (mConnection != null) {
        			mConnection.close();
        		}
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return id;
    }
    
    private static int getEdgeIdFromDB(Edge edge) {
    	int id = -1;
    	System.out.println(getQueryEdgeIdString(edge.getString(1), edge.getString(2)));
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		mConnection = DriverManager.getConnection(dbURL, userName, userPwd);
    		mStatement = mConnection.createStatement();
    		mResultSet = mStatement.executeQuery(getQueryEdgeIdString(edge.getString(1), edge.getString(2)));
    		if (mResultSet == null) return id;
			while(mResultSet.next()) {
				id = mResultSet.getInt(1);
				if (id > -1) return id; 
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (mResultSet != null) {
    				mResultSet.close();
    			}
    			if (mStatement != null) {
    				mStatement.close();
        		}
    			if (mConnection != null) {
        			mConnection.close();
        		}
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return id;
    }
    
    /**
     * Node sql operation statements
     */
    private static String getQueryNodeIdString(String enterprisename, String address, String officalwebsite, String email, String establishmenttime, String telephonenumber) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("select id from enterprisetable where ");
    	sb.append("enterprisename='");
    	sb.append(enterprisename);
    	sb.append("' and ");
    	sb.append("address='");
    	sb.append(address);
    	sb.append("' and ");
    	sb.append("officalwebsite='");
    	sb.append(officalwebsite);
    	sb.append("' and ");
    	sb.append("email='");
    	sb.append(email);
    	sb.append("'");
    	return sb.toString();
    }
    
    // TODO:..
    private static String getAddNodeString(String enterprisename, String address, String officalwebsite, String email, String establishmenttime, String telephonenumber) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("insert into enterprisetable(enterprisename, address, officalwebsite, email, establishmenttime) values ");
    	sb.append("('");
    	sb.append(enterprisename);
    	sb.append("', '");
    	sb.append(address);
    	sb.append("', '");
    	sb.append(officalwebsite);
    	sb.append("', '");
    	sb.append(email);
    	// TODO:.. time may be is not correct
    	sb.append("', '");
    	sb.append("1900-00-00");
    	sb.append("')");			
    	return sb.toString();
    }
    
    // tested
    private static String getUpdateNodeString(String id, String enterprisename, String address, String officalwebsite, String email, String establishmenttime, String telephonenumber) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("update enterprisetable set ");
    	sb.append("enterprisename='");
    	sb.append(enterprisename);
    	sb.append("', ");
    	sb.append("address='");
    	sb.append(address);
    	sb.append("', ");
    	sb.append("officalwebsite='");
    	sb.append(officalwebsite);
    	sb.append("', ");
    	sb.append("email='");
    	sb.append(email);
    	sb.append("' where id=");
    	sb.append(id);
    	return sb.toString();
    }
    
    // TESTED...
    private static String getDeleteNodeString(String id) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("delete from enterprisetable where id=");
    	sb.append(id);
    	return sb.toString();
    }
    
    /**
     * Edge sql operation statements
     */
    private static String getQueryEdgeIdString(String id, String partnerid) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("select num from cooperativeenttable where ");
    	sb.append("id='");
    	sb.append(id);
    	sb.append("' and ");
    	sb.append("partnerid='");
    	sb.append(partnerid);
    	sb.append("'");
    	return sb.toString();
    }
    
    private static String getAddEdgeString(String id, String partnerid) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("insert into cooperativeenttable(id, partnerid) values ");
    	sb.append("('");
    	sb.append(id);
    	sb.append("', '");
    	sb.append(partnerid);
    	sb.append("')");			
    	return sb.toString();
    }
    
    // tested
    private static String getUpdateEdgeString(String num, String id, String partnerid) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("update cooperativeenttable set ");
    	sb.append("id='");
    	sb.append(id);
    	sb.append("', ");
    	sb.append("partnerid='");
    	sb.append(partnerid);
    	sb.append("' where id=");
    	sb.append(id);
    	return sb.toString();
    }
    
    // TESTED...
    private static String getDeleteEdgeString(String num) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("delete from cooperativeenttable where num=");
    	sb.append(num);
    	return sb.toString();
    }
}
