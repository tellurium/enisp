package cn.edu.shu.enisp.session;

import javax.servlet.http.HttpServletRequest;

public class EnispSession {
    public static final String USERNAME = "username";
    public static final String PRIVILEGE = "privilege";

    public static void setUsername(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME, username);
    }
    public static String getUsername(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(USERNAME);
    }

    public static void setPrivilege(HttpServletRequest request, String privilege) {
        request.getSession().setAttribute(PRIVILEGE, privilege);
    }
    public static String getPrivilege(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(PRIVILEGE);
    }
}
