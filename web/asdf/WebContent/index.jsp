<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<%
	String status_info = (String)request.getAttribute("status_info");
	if (status_info != null && status_info.length() > 0) {
		out.println("<div id=\"status-info\"><p>" + status_info + "</p></div>");
	}
	%>
	<form name="form1" method="post" action="loginaction.jsp">
	<H2>登录</H2>
	你好，请登录或注册...
	<br />
	<br />
	<div id="login-box-name" style="margin-top:20px;">
		用户名:</div><div id="login-box-field" style="margin-top:20px;">
		<input name="username" class="form-login" title="Username" value="" size="30" maxlength="2048" />
	</div>
	<div id="login-box-name">密码:</div>
	<div id="login-box-field">
	<input name="password" type="password" class="form-login" title="Password" value="" size="30" maxlength="2048" />
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
    response.sendRedirect("afterlogin.jsp");
    }
%>

</div>
</body>
</html>