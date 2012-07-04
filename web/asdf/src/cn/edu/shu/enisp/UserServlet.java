package cn.edu.shu.enisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.factory.Factory;
import cn.edu.shu.enisp.model.User;
import cn.edu.shu.enisp.model.UserInfo;
import cn.edu.shu.enisp.session.EnispSession;

public class UserServlet extends BaseServlet {
    // jsp attributes
    public static final String STATUS_INFO = "status_info";

    // actions
    public static final String LOGIN = "login";

    private String status_info;

    @Override
    public void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        if (action.equals(LOGIN)) {
            processLoginAction(request, response);
        }
    }

    // 处理用户登录请求
    private void processLoginAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = null;
        String privilege = null;
        String password = null;
        boolean isSuccessed = false;
        // 用户分为两类:
        // 1.以User对象储存的普通用户和管理员用户 2.以UserInfo对象储存的企业用户
        // Step 1: 首先从请求中获取用户对象(传入的参数用户名和密码在User和UserInfo相同,所以可以共用)
        User user = (User) Factory.getObjectFromRequest(request, new User());
        username = user.getProperty(User.USERNAME);
        password = user.getProperty(User.PASSWORD);

        // Step 2: 检查用户输入
        if (username == null|| password == null ||
            username.length() == 0 || password.length() == 0) {
            status_info = "请输入用户名和密码";
        } 
        // Step 3: 从User表中查找是否有该用户
        else {
            user = (User) mDBOperator.selectObjectFromDB(user);
            if (user != null) {
                username = user.getProperty(User.USERNAME);
                privilege = user.getProperty(User.PRIVILEGE);
                isSuccessed = true;   
            } 
            // Step 4: 从UserInfo表中查找是否有该用户
            else {
                UserInfo userinfo = (UserInfo) Factory.getObjectFromRequest(request, new UserInfo());
                userinfo = (UserInfo) mDBOperator.selectObjectFromDB(userinfo);
                if (userinfo != null) {
                    username = userinfo.getProperty(UserInfo.USERNAME);
                    privilege = User.PRIVLIEGE_ENTERPRISE;
                    isSuccessed = true;
                } 
                // Step 5: 当两张表中都没有该用户
                else {
                    status_info = "用户名或密码不正确,请重新登录或注册";
                }
            }
        }
        
        // Step : 登录跳转,分为成功登录和登录失败两种情况
        if (isSuccessed) {
            EnispSession.setUsername(request, username);
            EnispSession.setPrivilege(request, privilege);
            goJSP("/home.jsp", request, response);
        } 
        else {
            request.setAttribute(STATUS_INFO, status_info);
            goJSP("/index.jsp", request, response);
        }
    }
}
