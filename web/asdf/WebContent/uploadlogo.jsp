<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.sql.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Your Logo</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login-box">
    <form name="myform" action="upload.jsp" method="post" enctype="multipart/form-data">
   
<%
    String driverName="com.mysql.jdbc.Driver";
    String userName="root";
    String userPasswd="123456";

    String dbName="enispdb";
    String tableName="enterprisetable";
    String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;
    Object obj= request.getSession().getAttribute("enterprisename");
    String entname=(String) obj;
    
     String sql ="select * from " + tableName +" where enterprisename='"+ entname +"'";
 
     Statement stmt= null;
     ResultSet rs =null;
     
     try
     {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 Connection conn=DriverManager.getConnection(url);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if(rs.next()){ 

%>
       <p>Hello, <%=request.getSession().getAttribute("enterprisename") %>. Please upload your logo. </p>
       <img src="<%=request.getContextPath()%>/<%=rs.getString("logopic")%>" width="200"  ><br> 
       Logo:<br>
       <input type="file" name="logo"><br>
       <br>
       <input type="submit" name="submit" value="Commit"> <a href="index.jsp">Return</a>

<%         }        
         else
        	 out.println("Sorry, wrong information. Please <a href=\"index.jsp\">return</a>.");
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
 </form>
 </div>
</body>

</html>