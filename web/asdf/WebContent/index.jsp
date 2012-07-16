<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ page import="cn.edu.shu.enisp.UserServlet" %>
    <%@ page import="cn.edu.shu.enisp.model.User" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enterprise Networked Innovation Service Platform</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
    if(request.getSession().getAttribute("username")==null)
    {
%>
	<div id="login-box">
	<form name="form1" method="post" action="UserLogin">
	<h2>登录</h2>
	你好，请登录或注册...
	<br />
	<%
	String status_info = (String) request.getAttribute(UserServlet.STATUS_INFO);
	if (status_info != null && status_info.length() > 0) {
        String info_flag = (String) request.getAttribute(UserServlet.STATUS_INFO_FLAG);
        if(info_flag == null || info_flag.length() == 0) {
            out.println("<div id=\"status-info\"><p>" + status_info + "</p></div>");
        } else {
            out.println("<div id=\"status-info-positive\"><p>" + status_info + "</p></div>");
        }
	}
	%>
	<br />
	<div id="login-box-name" style="margin-top:20px;">
		用户名:</div><div id="login-box-field" style="margin-top:20px;">
        <input name="<%= User.USERNAME %>" class="form-login" title="Username" value="" size="30" maxlength="2048" />
	</div>
	<div id="login-box-name">密码:</div>
	<div id="login-box-field">
        <input name="<%= User.PASSWORD %>" type="password" class="form-login" title="Password" value="" size="30" maxlength="2048" />
	</div>
	<br />
	<span class="login-box-options"><a href="register.jsp" style="margin-left:30px;">还没注册？</a></span>
	<br />
	<br />
	<input type="submit" id="login-submit-button" value="">
    </form>
<%
    }
    else
    {
%>

    
<%
    response.sendRedirect("home.jsp");
    }
%>

</div>
</body>
</html>
