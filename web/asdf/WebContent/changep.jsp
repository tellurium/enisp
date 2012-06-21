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
    String tableName="enterprisetable";
    String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;

    Object obj= request.getSession().getAttribute("enterprisename");
    String entname=(String) obj;
    String[] partners = request.getParameterValues("partner");
 
     int id =0;
     String sql;
     String sql2 ="select id from enterprisetable where enterprisename='"+entname+"'";
     String sql3 ="insert into cooperativeenttable(id,partnerid) values (?,?)";
     
     int updateResult=0;
     int i=0;
     Statement stmt= null;
     ResultSet rs =null;
     PreparedStatement ps = null;
     try
     {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 Connection conn=DriverManager.getConnection(url);
         stmt = conn.createStatement();
         rs=stmt.executeQuery(sql2);
         if(rs.next())
         {	 id=rs.getInt("id");
             sql ="delete from cooperativeenttable where id='"+id+"' or partnerid='"+id+"'";
             stmt.executeUpdate(sql);
         }
         else
        	 out.println("Sorry, wrong information. Please <a href=\"index.jsp\">return</a>.");

         for(i=0;i<partners.length;i++)
         {
        	 ps = conn.prepareStatement(sql3);
        	 ps.setInt(1,id);
        	 ps.setInt(2,Integer.parseInt(partners[i]));
        	 updateResult=ps.executeUpdate();
         }
         for(i=0;i<partners.length;i++)
         {
        	 ps = conn.prepareStatement(sql3);
        	 ps.setInt(1,Integer.parseInt(partners[i]));
        	 ps.setInt(2,id);
        	 updateResult=ps.executeUpdate();
         }
         if(updateResult==0){
    		 out.println("Sorry, register failed. Please <a href=\"changeinfo.jsp\">return</a>.");
    	 }
    		 
    	 else
       		out.println("Your partners information changed successful. Please <a href=\"changepartner.jsp\">return</a>.");
    		 
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
    	 if(ps != null){
    		 try{
    			 ps.close();
    		 }catch(SQLException ex){
    			 ps = null;
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
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login-box"></div>
</body>
</html>