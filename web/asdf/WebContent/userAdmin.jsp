<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ page import="cn.edu.shu.enisp.model.User" %>
    <%@ page import="cn.edu.shu.enisp.model.UserInfo" %>
    <%@ page import="java.util.List" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administration</title>
<link href="css/login-box.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<link href="css/adminStyle.css" rel="stylesheet" type="text/css" />
<style>
    #query-arguments li {
        float: left;
    }
</style>
</head>
<body>
    <h2>Welcome, Dear Administrator</h2>

    <div id="navigation-bar">
        <ul>
            <li><a href="#" style="color: #5C7A00">用户管理</a></li>
            <li><a href="#">请求管理</a></li>
            <li><a href="home.jsp">进入平台</a></li>
            <li><a href="UserLogout">退出系统</a></li>
        </ul>
    </div>

    <br />
    <hr />

    <div id="wrapper">
        <div class="content">
            <div id="query-arguments">
                <ul style="list-style: none">
                    <li>用户名:&nbsp;<input type="text" /></li>
                    <li>用户类型:&nbsp;
                        <select>
                            <option value="*">所有用户</option>
                            <option value="1">政府用户</option>
                            <option value="3">企业用户</option>
                        </select>
                    </li>
                    <li>用户状态:&nbsp;
                        <select>
                            <option value="active">已激活</option>
                            <option value="inactive">未激活</option>
                        </select>
                    </li>
                </ul>   
                <ul style="list-style: none;">
                    <li><input type="button" value="Search" >
	
                    </input></li>
                </ul>   
            </div>

            <%
                List<User> user_list = (List<User>)request.getAttribute("user-list");
                List<UserInfo> userInfo_list = (List<UserInfo>)request.getAttribute("userinfo-list");
            %>
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <th>用户名</th>
                    <th>用户类型</th>
                    <th>用户状态</th>
                    <th class="center">管理操作</th>
                </tr>
                <tr>
                    <td>Division 1</td>
                    <td>Division 2</td>
                    <td>Division 3</td>
                    <td class="center">
                        <a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#">删除</a>
                    </td>
                </tr>
                <tr class="even">
                    <td>Division 1</td>
                    <td>Division 2</td>
                    <td>Division 3</td>
                    <td class="center">
                        <a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>Division 1</td>
                    <td>Division 2</td>
                    <td>Division 3</td>
                    <td class="center">
                        <a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#">删除</a>
                    </td>
                </tr>
            </table>



        </div>
    </div>

</body>
</html>
