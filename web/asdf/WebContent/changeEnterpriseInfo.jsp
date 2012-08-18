<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ page import="cn.edu.shu.enisp.UserServlet" 
    import="cn.edu.shu.enisp.factory.Factory"
    import="cn.edu.shu.enisp.model.UserInfo" 
    import="cn.edu.shu.enisp.model.Enterprise" 
    %>
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
                <h2>企业用户</h2>
                <%
                String status_info = (String) request.getAttribute(UserServlet.STATUS_INFO);
                if (status_info != null && status_info.length() > 0) {
                    String info_flag = (String) request.getAttribute(UserServlet.STATUS_INFO_FLAG);
                    if(info_flag == null || info_flag.length() == 0) {
                    } else {
                    out.println("<div id=\"status-info-positive\"><p>" + status_info + "</p></div>");
                    }
                } else {
                    status_info = " ";
                }
                Enterprise enterprise = new Enterprise();
                String year = "";
                String month = "";
                String day = "";

                enterprise = (Enterprise) Factory.getObjectFromRequestAttribute(request, enterprise);
                if (enterprise.getProperty(Enterprise.ID).length() > 0) {
                    year = (String) request.getAttribute(UserServlet.YEAR);
                    month = (String) request.getAttribute(UserServlet.MONTH);
                    day = (String) request.getAttribute(UserServlet.DAY);
                } else {
                    enterprise = new Enterprise();
                }
                %>
                <div id="status-info"><p id="info"><%= status_info %></p></div>
                <table>
                    <tr>
                        <td><label for="password">Password:</label></td>
                        <td><input type="password" class="form-login" name="<%= UserInfo.PASSWORD %>"></td>
                    </tr>
                    <tr>
                        <td><label for="confirm_password">Confirm Password:</label></td>
                        <td><input type="password" class="form-login" name="confirm_password"></td>
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
                        <td><input type="button" class="normal-button" id="back" onClick="history.back()" value="Back"></input></td>
                        <td><input type="submit" class="normal-button" id="submit" value="Submit" onclick="return valid();"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
</body>
