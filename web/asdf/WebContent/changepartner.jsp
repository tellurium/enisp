<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.sql.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Your Partners</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login-box">
<form name="form1" method="post" action="changep.jsp">
        <p><label for="partner">Cooperative Enterprises:</label></p>
        <p>
<%
    String driverName="com.mysql.jdbc.Driver";
    String userName="root";
    String userPasswd="123456";

    String dbName="enispdb";
    String tableName="enterprisetable";
    String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;
    Object obj= request.getSession().getAttribute("enterprisename");
    String entname=(String) obj;
    
     String sql ="select partnerid,B.enterprisename from enterprisetable as A,enterprisetable as B,cooperativeenttable where A.id=cooperativeenttable.id and B.id=cooperativeenttable.partnerid and A.enterprisename='"+ entname +"'";
     String sql2 ="select id,enterprisename from enterprisetable where enterprisename<>'"+entname+"' and enterprisename not in (select B.enterprisename from enterprisetable as A,enterprisetable as B,cooperativeenttable where A.id=cooperativeenttable.id and B.id=cooperativeenttable.partnerid and A.enterprisename='"+ entname +"')";
 
     Statement stmt= null;
     ResultSet rs =null;
     
     try
     {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 Connection conn=DriverManager.getConnection(url);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         while(rs.next()){
%>

        <input type="checkbox" name="partner" id="partner" value="<%=rs.getInt("partnerid")%>" checked="checked"> <%=rs.getString("enterprisename") %> <br>

<%       }      
         
         rs = stmt.executeQuery(sql2);
         while(rs.next()){
%>

        <input type="checkbox" name="partner" id="partner" value="<%=rs.getInt("id")%>" > <%=rs.getString("enterprisename") %> <br>

<%       }      
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
    		 }catch(SQLException ex){
    			 rs = null;
    		 }
    	 }
    	 if(stmt != null){
    		 try{
    			 stmt.close();
    		 }catch(SQLException ex){ }
    		 stmt = null;
    	 }
     }
%>
</p>
<p><input type="submit" name="button" id="button" value="Submit">  <a href="index.jsp">Return</a></p>
</form>
</div>
</body>
</html>