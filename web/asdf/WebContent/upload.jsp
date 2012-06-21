<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
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
    
    String sql2 ="update " + tableName + " set logopic=? where enterprisename='"+entname +"'";
    
    PreparedStatement ps = null;
    
    String uploadPath = getServletContext().getRealPath("")+"\\data\\logopic"; 
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if(isMultipart==true){
       try{
          FileItemFactory factory = new DiskFileItemFactory();
          ServletFileUpload upload = new ServletFileUpload(factory);
          List<FileItem> items = upload.parseRequest(request);
          Iterator<FileItem> itr = items.iterator();
          
          Class.forName("com.mysql.jdbc.Driver").newInstance();
      	  Connection conn=DriverManager.getConnection(url);
     	  ps=conn.prepareStatement(sql2);

          if(itr.hasNext()){
             FileItem item=(FileItem)itr.next();
             String fileName=item.getName();        
             if(fileName!=null){
                 File fullFile=new File(item.getName());
                 File savedFile=new File(uploadPath,fullFile.getName());
                 String fullname = "data/logopic/" + fullFile.getName();
                 item.write(savedFile);
                 ps.setString(1,fullname);
                 ps.executeUpdate();
             }
          }
          out.println("Your Logo upload successful. Please <a href=\"uploadlogo.jsp\">return</a>.");
       }
       catch(Exception e){
    	   e.printStackTrace();
    	   }
       }
       else{
    	   out.println("the enctype must be multipart/form-data");
    	   }
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>