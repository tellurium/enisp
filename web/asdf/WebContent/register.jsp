<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.edu.shu.enisp.model.User" 
		 import="cn.edu.shu.enisp.model.Enterprise"
		 import="cn.edu.shu.enisp.util.StringSnippet"
		 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login-box">

<%
	String status_info = (String)request.getAttribute("status_info");
	String name = (String) request.getAttribute(User.USERNAME);
	String entname = (String) request.getAttribute(Enterprise.ENTERPRISENAME);
	String add = (String)request.getAttribute(Enterprise.ADDRESS);
	String tel = (String)request.getAttribute(Enterprise.TELEPHONENUMBER);
	String fax = (String) request.getAttribute(Enterprise.FAXNUMABER);
	String web = (String)request.getAttribute(Enterprise.OFFICALWEBSITE);
	String mail = (String)request.getAttribute(Enterprise.EMAIL);
	String establishtime = (String)request.getAttribute(Enterprise.ESTABLISHMENTTIME);
	
	name = StringSnippet.makeNotNull(name);
	entname = StringSnippet.makeNotNull(entname);
	add = StringSnippet.makeNotNull(add);
	tel = StringSnippet.makeNotNull(tel);
	fax = StringSnippet.makeNotNull(fax);
	web = StringSnippet.makeNotNull(web);
	mail = StringSnippet.makeNotNull(mail);
	establishtime = StringSnippet.makeNotNull(establishtime);
	
	if (status_info != null && status_info.length() > 0) {
		out.println("<div id=\"status-info\"><p>" + status_info + "</p></div>");
	}
%>
<form name="form1" method="post" action="RegisterUser">
<table>
    <tr>
        <td><label for="username">UserName:</label></td>
        <td><input type="text" name="<%= User.USERNAME %>" value="<%= name %>"></td>
    </tr>
    <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" name="<%= User.PASSWORD %>"></td>
    </tr>
    <tr>
        <td><label for="password01">Confirm Password:</label></td>
        <td><input type="password" name="confirm_password"></td>
    </tr>
    <tr>
        <td><label for="enterprisename">Enterprise Name:</label></td>
        <td><input type="text" name="<%= Enterprise.ENTERPRISENAME %>" value="<%= entname %>"></td>
    </tr>
    <tr>
        <td><label for="address">Address:</label></td>
        <td><input type="text" name="<%= Enterprise.ADDRESS %>" value="<%= add %>"></td>
    </tr>
    <tr>
        <td><label for="telephonenumber">Telephone Number:</label></td>
        <td><input type="text" name="<%= Enterprise.TELEPHONENUMBER %>" value="<%= tel %>"></td>
    </tr>
    <tr>
        <td><label for="faxnumber">Fax Number:</label></td>
        <td><input type="text" name="<%= Enterprise.FAXNUMABER %>" value="<%= fax %>"></td>
    </tr>
    <tr>
        <td><label for="officalwebsite">Offical Webstite:</label></td>
        <td><input type="text" name="<%= Enterprise.OFFICALWEBSITE %>" value="<%= web %>"></td>
    </tr>
    <tr>
        <td><label for="email">E-mail:</label></td>
        <td><input type="text" name="<%= Enterprise.EMAIL %>" value="<%= mail %>"></td>
    </tr>
    <tr>
        <td><label for="email">Establish Time:</label></td>
        <td><input type="text" name="<%= Enterprise.ESTABLISHMENTTIME %>" value="<%= establishtime %>"></td>
    </tr>
    <tr>
    	<td><input type="reset" name="reset" id="reset" value="Reset">
        <td><input type="submit" name="button" id="button" value="Create an Account"></td>
    </tr>
</table>
</form>
</div>
</body>
</html>