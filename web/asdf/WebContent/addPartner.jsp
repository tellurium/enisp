<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="cn.edu.shu.enisp.EnterpriseServlet" %>
<%@ page import="cn.edu.shu.enisp.UserServlet" %>
<%@ page import="cn.edu.shu.enisp.db.DBOperator" %>
<%@ page import="cn.edu.shu.enisp.model.BaseModel" %>
<%@ page import="cn.edu.shu.enisp.model.Enterprise" %>
<%@ page import="cn.edu.shu.enisp.model.User" %>
<%@ page import="cn.edu.shu.enisp.session.EnispSession" %>
<%@ page import="java.util.List" %>
<head>
    <title>添加伙伴关系</title>
    <link href="css/change-info.css" rel="stylesheet" type="text/css" />
    <link href="css/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="register-title">
        <h1>添加伙伴关系</h1>
    </div>

    <hr />

    <div id="wrapper">
        <div class="content">
            <form method="post" action="AddCooperativent">
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <td width="30%">合作公司名称</td>
                    <td>
                        <%

                        String enterprise_id = EnispSession.getEnterpriseId(request);
                        String sql_str = "select * from enterprisetable where id not in " +
                                         "(select partnerid from cooperativeenttable where id = " +
                                         enterprise_id +
                                         ") and id <> " + enterprise_id;
                        List<BaseModel> list = (new DBOperator()).selectObjectListBySQL(sql_str, new Enterprise());
                        %>
                        <%
                        if (list.size() > 0) {
                        %>
                        <select name="id">
                            <%
                            for(BaseModel partner : list) {
                            %>
                            <option value="<%=partner.getProperty(Enterprise.ID)%>">
                            <%=partner.getProperty(Enterprise.ENTERPRISENAME)%>
                            </option> 
                            <%
                            }
                            %>
                        </select>
                        <%}
                        %>
                    </td>
                </tr>

            </table>
            <input type="button" class="normal-button" id="back" onClick="history.back()" 
                   value="Back" style="float: left; margin-bottom: 20px;">
            </input>
            <input type="submit" class="normal-button" id="submit" value="Submit" 
                   style="float: right; margin-bottom: 20px;">
            </input>
            </form> 
        </div>

    </div>

</body>
</html>
