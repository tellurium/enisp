<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.sql.*" %>
<%
    String driverName="com.mysql.jdbc.Driver";
    String userName="root";
    String userPasswd="987987";

    String dbName="enispdb";
    String enterpriseTableName="enterprisetable";
    String userTableName="userinfo";
    String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;

    String name = request.getParameter("username");
    String pwd = request.getParameter("password");
    String pwd01 = request.getParameter("password01");
    String entname = request.getParameter("enterprisename");
    String add = request.getParameter("address");
    String tel = request.getParameter("telephonenumber");
    String fax = request.getParameter("faxnumber");
    String web = request.getParameter("officalwebsite");
    String mail = request.getParameter("email");
    
    String INSERT_USER_SQL = "insert into " + userTableName +
    		"(username, password, enterpriseid) values (?, ?, ?)";
    String INSERT_ENTERPRISE_SQL = "insert into " + enterpriseTableName + 
    		"(enterprisename,address,telephonenumber,faxnumber,officalwebsite,email) values ('" 
    		+entname+"','"+add+"','"+tel+"','"+fax+"','"+web+"','"+ mail+"')";
    String SELECT_ENTERPRISE_SQL = "select id from " + enterpriseTableName + "where enterprisename = '" + entname + "' and address = '" + add + "' "
    		+ "and telephonenumber = '" + tel + "' and faxnumber = '" + fax + "' and officalwebsite = '" + web + "' and email = '" + mail + "'";
    
    int insertResult=0;
    Statement stmt= null;
    ResultSet rs = null;
    Connection conn = null;

    try
    {
	  	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	  	 conn = DriverManager.getConnection(url);
	     stmt = conn.createStatement();
	     // rs = stmt.executeQuery(sql);
	     
	     // 新建用户操作有两步：
	     // Step 1: 首先要在enterprise表中插入一行
	     insertResult = stmt.executeUpdate(INSERT_ENTERPRISE_SQL);
	     out.println("insert enterprise result is " + insertResult);
	     // Step 2: 然后获得该新插入的enterprise字段的id
	     rs = stmt.executeQuery(SELECT_ENTERPRISE_SQL);
	     if	(rs == null) return ;
	     if(rs.next()) {
	    	 insertResult = rs.getInt(1);
		 }
	     // Step 3: 根据获得的id
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    finally
    {
   	 if(rs != null){
   		 try{
   			 rs.close();
   		 }catch(SQLException e){
   			 e.printStackTrace();
   		 }
   	 }

   	 if(stmt != null){
   		 try{
   			 stmt.close();
   		 }catch(SQLException e){
   			e.printStackTrace();
   		 }
   	 }
   	 
   	 if(conn != null) {
   		 try {
   			 conn.close();
   		 } catch (SQLException e) {
   		 	 e.printStackTrace();
   		 }
   	 }
    }
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>