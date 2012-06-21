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

     String name = request.getParameter("username");
     String pwd = request.getParameter("password");
     String pwd01 = request.getParameter("password01");
     String entname = request.getParameter("enterprisename");
     String add = request.getParameter("address");
     String tel = request.getParameter("telephonenumber");
     String fax = request.getParameter("faxnumber");
     String web = request.getParameter("officalwebsite");
     String mail = request.getParameter("email");
     
     if(name=="")
    	 out.println("Sorry, the empty UserName . Please <a href=\"changeinfo.jsp\">return</a>.");
     else if(pwd=="")
    	 out.println("Sorry, the empty password. Please <a href=\"changeinfo.jsp\">return</a>.");
     else if(pwd01=="")
    	 out.println("Sorry, the empty confirm password. Please <a href=\"changeinfo.jsp\">return</a>.");
     else if(entname=="")
    	 out.println("Sorry, the empty Enterprise Name. Please <a href=\"changeinfo.jsp\">return</a>.");


     String sql2 ="update " + tableName + " set password =?, enterprisename=?, address=?, telephonenumber=?, faxnumber=?, officalwebsite=?, email=? where username='"+name +"'";

     int updateResult=0;
     Statement stmt= null;
     ResultSet rs =null;
     PreparedStatement ps = null;
     
     try
     {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 Connection conn=DriverManager.getConnection(url);
         stmt = conn.createStatement();

         if(!pwd.equals(pwd01))
        	 out.println("Sorry, different passwords.Please <a href=\"changeinfo.jsp\">return</a>.");
         else{
        	 ps=conn.prepareStatement(sql2);
        	 ps.setString(1,pwd);
        	 ps.setString(2,entname);
        	 ps.setString(3,add);
        	 ps.setString(4,tel);
        	 ps.setString(5,fax);
        	 ps.setString(6,web);
        	 ps.setString(7,mail);
        	 updateResult=ps.executeUpdate();
        	 if(updateResult==0){
        		 out.println("Sorry, information didn't change. Please <a href=\"changeinfo.jsp\">return</a>.");
        	 }
        		 
        	 else
           		out.println("Your information changed successful. Please <a href=\"changeinfo.jsp\">return</a>.");
        		 
         }
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login-box">
</div>
</body>
</html>