<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="cn.edu.shu.enisp.model.User" 
import="cn.edu.shu.enisp.model.Enterprise"
import="cn.edu.shu.enisp.model.UserInfo"
import="cn.edu.shu.enisp.util.StringSnippet"
import="cn.edu.shu.enisp.UserServlet"
import="cn.edu.shu.enisp.factory.Factory"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>注册</title>
    <link href="css/register-page.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%
        String user_type = (String) request.getAttribute(UserServlet.USER_TYPE);
    %>
    <div id="register-container">
        <div id="register-title">
            <h1>请选择注册用户类型</h1>
        </div>

        <div id="register-main">
<div id="register-box-normal">
    <%
    String status_info_1 = "";
    User user = new User();
    if (user_type != null && user_type.equals(UserServlet.USER_NOARMAL)) {
        status_info_1 = (String)request.getAttribute(UserServlet.STATUS_INFO);
        user = (User) Factory.getObjectFromRequestAttribute(request, user);
    }
    %>
    <form name="form2" method="post" action="RegisterNormalUser">
        <h2>政府用户</h2>
        <div id="status-info"><p><%= status_info_1 %></p></div>
        <table>
            <tr>
                <td><label for="username">UserName:</label></td>
                <td><input type="text" class="form-login" name="<%= User.USERNAME %>" value="<%= user.getProperty(User.USERNAME) %>"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password"  class="form-login" name="<%= User.PASSWORD %>"></td>
            </tr>
            <tr>
                <td><label for="password01">Confirm Password:</label></td>
                <td><input type="password"  class="form-login" name="confirm_password"></td>
            </tr>
            <tr>
                <td><input type="button" class="normal-button" name="back" id="reset" value="Back" onClick="history.back()"></td>
                    <td><input type="submit" class="normal-button" name="button" id="submit" value="Create"></td>
            </tr>
        </table>
    </form>
</div>

<div id="register-box-enterprise">
    <%
    String status_info = "";
    UserInfo userinfo = new UserInfo();
    Enterprise enterprise = new Enterprise();
    String year = "";
    String month = "";
    String day = "";
    if (user_type != null && user_type.equals(UserServlet.USER_ENTERPRISE)) {
        status_info = (String)request.getAttribute(UserServlet.STATUS_INFO);
        userinfo = (UserInfo) Factory.getObjectFromRequestAttribute(request, userinfo);
        enterprise = (Enterprise) Factory.getObjectFromRequestAttribute(request, enterprise);
        year = (String) request.getAttribute(UserServlet.YEAR);
        month = (String) request.getAttribute(UserServlet.MONTH);
        day = (String) request.getAttribute(UserServlet.DAY);
    }
    %>
    <form name="form1" method="post" action="RegisterEnterpriseUser">
        <h2>企业用户</h2>
        <div id="status-info"><p><%= status_info %></p></div>
        <table>
            <tr>
                <td><label for="username">UserName:</label></td>
                <td><input type="text" class="form-login" name="<%= User.USERNAME %>" value="<%= userinfo.getProperty(UserInfo.USERNAME)%>"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password"  class="form-login" name="<%= User.PASSWORD %>"></td>
            </tr>
            <tr>
                <td><label for="password01">Confirm Password:</label></td>
                <td><input type="password"  class="form-login" name="<%= UserServlet.CONFIRM_PASSWORD %>"></td>
            </tr>
            <tr>
                <td><label for="enterprisename">Enterprise Name:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.ENTERPRISENAME %>" value="<%= enterprise.getProperty(Enterprise.ENTERPRISENAME) %>"></td>
            </tr>
            <tr>
                <td><label for="address">Address:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.ADDRESS %>" value="<%= enterprise.getProperty(Enterprise.ADDRESS) %>"></td>
            </tr>
            <tr>
                <td><label for="telephonenumber">Telephone Number:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.TELEPHONENUMBER %>" value="<%= enterprise.getProperty(Enterprise.TELEPHONENUMBER) %>"></td>
            </tr>
            <tr>
                <td><label for="faxnumber">Fax Number:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.FAXNUMABER %>" value="<%= enterprise.getProperty(Enterprise.FAXNUMABER) %>"></td>
            </tr>
            <tr>
                <td><label for="officalwebsite">Offical Webstite:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.OFFICALWEBSITE %>" value="<%= enterprise.getProperty(Enterprise.OFFICALWEBSITE) %>"></td>
            </tr>
            <tr>
                <td><label for="email">E-mail:</label></td>
                <td><input type="text"  class="form-login" name="<%= Enterprise.EMAIL %>" value="<%= enterprise.getProperty(Enterprise.EMAIL) %>"></td>
            </tr>
            <tr>
                <td><label for="establishtime">Establish Time:</label></td>
                <td><input type="text" class="establish-time" name="<%= UserServlet.YEAR %>" value="<%= year %>">
                <label>年</label>
                <input type="text" class="establish-time" name="<%= UserServlet.MONTH %>" value="<%= month %>">
                <label>月</label>
                <input type="text" class="establish-time" name="<%= UserServlet.DAY %>" value="<%= day %>">
                <label>日</label></td>
            </tr>
            <tr>
                <td>
                    <input type="button" class="normal-button" name="back" id="reset" value="Back" onClick="history.back()">
                </td>
                    <td><input type="submit" class="normal-button" name="button" id="submit" value="Create"></td>
            </tr>
        </table>
    </form>
</div>
        </div>
    </div>
</body>
</html>
