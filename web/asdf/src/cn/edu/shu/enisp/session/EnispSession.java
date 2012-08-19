package cn.edu.shu.enisp.session;

import javax.servlet.http.HttpServletRequest;
import cn.edu.shu.enisp.model.User;

public class EnispSession {
    public static final String USERNAME = "username";
    public static final String PRIVILEGE = "privilege";
    public static final String USERID = User.ID;
    public static final String ENTERPRISEID = "enterpriseid";

    public static void invalidate(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static void setUsername(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME, username);
    }
    public static String getUsername(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(USERNAME);
    }

    public static void setUserId(HttpServletRequest request, String id) {
        request.getSession().setAttribute(USERID, id);
    }
    public static String getUserId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(USERID);
    }

    public static void setEnterpriseId(HttpServletRequest request, String id) {
        request.getSession().setAttribute(ENTERPRISEID, id);
    }
    public static String getEnterpriseId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(ENTERPRISEID);
    }

    public static void setPrivilege(HttpServletRequest request, String privilege) {
        request.getSession().setAttribute(PRIVILEGE, privilege);
    }
    public static String getPrivilege(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(PRIVILEGE);
    }
}
