<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ page import="cn.edu.shu.enisp.UserServlet" %>
    <%@ page import="cn.edu.shu.enisp.model.User" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administration</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <h2>Welcome, Dear Administrator</h2>

    <div id="navigation-bar">
        <ul>
            <li><a href="#">用户管理</a></li>
            <li><a href="#">请求管理</a></li>
            <li><a href="home.jsp">进入平台</a></li>
            <li><a href="changeNormalInfo.jsp">修改密码</a></li>
            <li><a href="UserLogout">退出系统</a></li>
        </ul>
    </div>

    <br />
    <hr />

    <div id="main-content">

    </div>

</body>
</html>
