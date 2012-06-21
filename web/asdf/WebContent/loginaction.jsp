<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.sql.*" %>
<%
    String driverName="com.mysql.jdbc.Driver";
    String userName="root";
    String userPasswd="123456";

    String dbName="enispdb";
    String tableName="userinfo";    
    String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;

     String name = request.getParameter("username");
     String pwd = request.getParameter("password");
     String sql ="select * from " + tableName + " where username='" + name + "' and password='" + pwd + "'";
     
     Statement stmt= null;
     ResultSet rs =null;
     try
     {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 Connection conn=DriverManager.getConnection(url);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if(rs.next())
         {
             session.setAttribute("username",name);
             session.setAttribute("userid", rs.getString("userid"));
             session.setAttribute("enterpriseid",rs.getString("enterpriseid"));
             response.sendRedirect("index.jsp");
         }
         else
        	 out.println("Sorry, wrong UserName or Password. Please <a href=\"index.jsp\">login</a> again.");
		// out.println(sql);
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
