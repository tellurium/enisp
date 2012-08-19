<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="cn.edu.shu.enisp.UserServlet" %>
<%@ page import="cn.edu.shu.enisp.model.User" %>
<head>
    <title>注册</title>
    <link href="css/change-info.css" rel="stylesheet" type="text/css" />
    <script src="javascript/simple_validator.js"></script>
</head>
<body>
    <div id="register-container">
        <div id="register-title">
            <h1>修改用户信息</h1>
        </div>

        <div id="change-info">
            <form id="form2" name="form2" method="post" action="ChangeInfo" onsubmit="return checkform();">
                <h2>普通用户</h2>
                <%
                String status_info = (String) request.getAttribute(UserServlet.STATUS_INFO);
                if (status_info != null && status_info.length() > 0) {
                    String info_flag = (String) request.getAttribute(UserServlet.STATUS_INFO_FLAG);
                    if(info_flag == null || info_flag.length() == 0) {
                        status_info = "";
                    } else {
                        out.println("<div id=\"status-info-positive\"><p>" + status_info + "</p></div>");
                    }
                } else {
                    status_info = " ";
                }
                %>
                <div id="status-info"><p id="info"><%= status_info %></p></div>
                <table>
                    <tr>
                        <td><label for="password">Password:</label></td>
                        <td><input type="password" class="form-login" name="<%= User.PASSWORD %>"></td>
                    </tr>
                    <tr>
                        <td><label for="confirm_password">Confirm Password:</label></td>
                        <td><input type="password" class="form-login" name="confirm_password"></td>
                    </tr>
                    <tr>
                        <td><input type="button" class="normal-button" id="back" onClick="history.back()" value="Back"></input></td>
                        <td><input type="submit" class="normal-button" id="submit" value="Submit" onclick="return valid();"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
</body>
</html>
