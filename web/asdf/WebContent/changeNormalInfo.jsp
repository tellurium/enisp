<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>注册</title>
    <link href="css/change-info.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="register-container">
        <div id="register-title">
            <h1>修改个人信息</h1>
        </div>

        <div id="change-normal-info">
            <form name="form2" method="post" action="RegisterNormalUser">
                <h2>普通用户</h2>
                <div id="status-info"><p>H</p></div>
                <table>
                    <tr>
                        <td><label for="username">UserName:</label></td>
                        <td><input type="text" class="form-login" name="hello" value="Hello"></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password:</label></td>
                        <td><input type="text" class="form-login" name="hello" value="Hello"></td>
                    </tr>
                    <tr>
                        <td><label for="confirm_password">Confirm Password:</label></td>
                        <td><input type="text" class="form-login" name="hello" value="Hello"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
</body>
