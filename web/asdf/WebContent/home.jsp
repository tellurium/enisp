<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enterprise Networked Innovation Service Platform</title>
<link href="css/afterlogin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="menu">
             欢迎你，  <%=request.getSession().getAttribute("username") %> <a class="fontcolor"> | </a>   
               <a href="SelectUser">修改个人信息</a>   <a class="fontcolor"> | </a>   
               <a href="changepartner.jsp">修改伙伴信息</a> <a class="fontcolor"> | </a> 
               <a href="uploadlogo.jsp">上传图片</a>  <a class="fontcolor"> | </a> 
               <a href="logout.jsp">登出</a> 
    </div>   
    <div id="graphview">     
         <a href="graphview.jsp">图形视图</a>
    </div>
    <div id="radialgraphview">
    <a href="radialgraphview.jsp">径向图形视图</a>
    </div>
    <div id ="starburst">
    <a href="starburst.jsp">星爆图</a>
    </div>

</body>
</html>