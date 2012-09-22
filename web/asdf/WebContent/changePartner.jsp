<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="cn.edu.shu.enisp.CooperativentServlet" %>
<%@ page import="cn.edu.shu.enisp.EnterpriseServlet" %>
<%@ page import="cn.edu.shu.enisp.UserServlet" %>
<%@ page import="cn.edu.shu.enisp.model.BaseModel" %>
<%@ page import="cn.edu.shu.enisp.model.Enterprise" %>
<%@ page import="cn.edu.shu.enisp.model.User" %>
<%@ page import="java.util.List" %>
<head>
    <title>修改伙伴关系</title>
    <link href="css/change-info.css" rel="stylesheet" type="text/css" />
    <link href="css/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="register-title">
        <h1>修改伙伴关系</h1>
    </div>

    <hr />

    <div id="wrapper">
        <% String s = request.getAttribute(CooperativentServlet.MESSAGE) == null ? " " : request.getAttribute(CooperativentServlet.MESSAGE).toString();%>
        <div><p style="color:green;"><%= s %></p></div>
        <div class="content">
            <%
            List<BaseModel> partner_list = (List<BaseModel>)request.getAttribute(EnterpriseServlet.PARTNER_LIST);
            %>
            <%
            if(partner_list.size() > 0) {
            %> 
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <th>合作公司图标</th>
                    <th>合作公司名称</th>
                    <th class="center">管理操作</th>
                </tr>
                <%
                for(BaseModel partner : partner_list) {
                %>
                <tr>
                    <td><img src="images<%= partner.getProperty(Enterprise.LOGOPIC) %>" height="80" width="50%"></img></td>
                    <td width="25%"><%= partner.getProperty(Enterprise.ENTERPRISENAME)%></td>
                    <td class="center" width="15%">
                        <a href="DeleteCooperativent?id=<%=partner.getProperty(Enterprise.ID)%>">删除</a>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
            <%
            }
            %>
            <input type="button" class="normal-button" id="back" onClick="location.href='home.jsp'" 
                   value="Home" style="float: left; margin-bottom: 20px;">
            </input>
            <input type="button" class="normal-button" id="submit" value="Add" 
                   onclick="location.href='addPartner.jsp'" style="float: right; margin-bottom: 20px;">
            </input>
        </div>

    </div>

</body>
</html>
