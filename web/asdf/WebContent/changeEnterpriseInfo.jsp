<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ page import="cn.edu.shu.enisp.UserServlet" %>
<head>
    <title>注册</title>
    <link href="css/change-info.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="register-container">
        <div id="register-title">
            <h1>修改用户信息</h1>
        </div>

        <div id="change-info">
            <form name="form2" method="post" action="ChangeInfo">
                <h2>企业用户</h2>
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
                <table>
                    <tr>
                        <td><label for="password">Password:</label></td>
                        <td><input type="text" class="form-login" name="hello" value="Hello"></td>
                    </tr>
                    <tr>
                        <td><label for="confirm_password">Confirm Password:</label></td>
                        <td><input type="text" class="form-login" name="hello" value="Hello"></td>
                    </tr>
                    <tr>
                        <td><input type="button" class="normal-button" id="back" onClick="history.back()" value="Back"></input></td>
                        <td><input type="submit" class="normal-button" id="submit" value="Submit"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
</body>
