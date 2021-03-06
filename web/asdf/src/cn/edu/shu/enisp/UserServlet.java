package cn.edu.shu.enisp;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.factory.Factory;
import cn.edu.shu.enisp.model.Enterprise;
import cn.edu.shu.enisp.model.User;
import cn.edu.shu.enisp.model.UserInfo;
import cn.edu.shu.enisp.session.EnispSession;
import cn.edu.shu.enisp.util.StringSnippet;

@SuppressWarnings("serial")
public class UserServlet extends BaseServlet {
    // jsp attributes
    public static final String STATUS_INFO = "status_info";
    public static final String STATUS_INFO_FLAG = "status_info_flag";

    public static final String USER_TYPE = "user_type";
    public static final String USER_NOARMAL = "normal";
    public static final String USER_ENTERPRISE = "enterprise";

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";

    public static final String CONFIRM_PASSWORD = "confirm_password";

    // actions
    public static final String LOGIN = "login";
    public static final String REGISTER_NORMAL = "register-normal";
    public static final String REGISTER_ENTERPRISE = "register-enterprise";
    public static final String UPDATE_USER = "update-user";
    public static final String CHANGE_INFO = "change-info";
    public static final String LOGOUT = "logout";

    private String status_info;

    @Override
    public void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        if (action.equals(LOGIN)) {
            processLoginAction(request, response);
        } else if (action.equals(REGISTER_NORMAL)) {
            processRegisterNormalUserAction(request, response);
        } else if (action.equals(REGISTER_ENTERPRISE)) {
            processRegisterEnterpriseUserAction(request, response);
        } else if (action.equals(UPDATE_USER)) {
            processUpdateUserAction(request, response);
        } else if (action.equals(CHANGE_INFO)) {
            processChangeInfoAction(request, response);
        } else if (action.equals(LOGOUT)) {
            processLogoutAction(request, response);
        } else { 
            showError(request, response);
        }
    }

    // 处理用户登录请求
    private void processLoginAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = null;
        String privilege = null;
        String userid = null;
        String enterpriseid = null;
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
                userid = user.getProperty(User.ID);
                isSuccessed = true;   
            } 
            // Step 4: 从UserInfo表中查找是否有该用户
            else {
                UserInfo userinfo = (UserInfo) Factory.getObjectFromRequest(request, new UserInfo());
                userinfo = (UserInfo) mDBOperator.selectObjectFromDB(userinfo);
                if (userinfo != null) {
                    username = userinfo.getProperty(UserInfo.USERNAME);
                    privilege = User.PRIVLIEGE_ENTERPRISE;
                    userid = userinfo.getProperty(UserInfo.ID);
                    enterpriseid = userinfo.getProperty(UserInfo.ENTERPRISEID);
                    isSuccessed = true;
                } 
                // Step 5: 当两张表中都没有该用户
                else {
                    status_info = "用户名或密码不正确,请重新登录或注册";
                }
            }
        }
        
        // Step 6: 登录跳转,分为成功登录和登录失败两种情况
        if (isSuccessed) {
            EnispSession.setUsername(request, username);
            EnispSession.setPrivilege(request, privilege);
            EnispSession.setUserId(request, userid);
            if (privilege.equals(User.PRIVLIEGE_ENTERPRISE)) {
                EnispSession.setEnterpriseId(request, enterpriseid);
            }
            if (privilege.equals(User.PRIVLIEGE_ADMIN)) {
                goJSP("/UserAdmin", request, response);
            } else {
                goJSP("/home.jsp", request, response);
            }
        } 
        else {
            request.setAttribute(STATUS_INFO, status_info);
            goJSP("/index.jsp", request, response);
        }
    }

    private void processLogoutAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        EnispSession.invalidate(request);

        goJSP("/index.jsp", request, response);
    }

    // 处理普通用户注册操作
    private void processRegisterNormalUserAction(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        String password = null;
        String confirm_password = null;
        boolean isSuccessed = false;

        // 普通用户的注册操作相对简单         
        // Step 1: 从请求中获取用户对象 
        User user = (User) Factory.getObjectFromRequest(request, new User());
        username = user.getProperty(User.USERNAME);
        password = user.getProperty(User.PASSWORD);
        confirm_password = request.getParameter(UserServlet.CONFIRM_PASSWORD);

        // Step 2: 检查用户输入
        if (username == null|| password == null || confirm_password == null ||
            username.length() == 0 || password.length() == 0 || confirm_password.length() == 0) {
            status_info = "请输入完整信息";
        } 
        // Step 3: 进行密码校验
        else if (!password.equals(confirm_password)) {
            status_info = "密码校验错误, 请重新输入";
        } 
        // Step 4: 检查用户名是否重复
        else {
            User temp_user = new User(username, "", "", "");
            temp_user = (User) mDBOperator.selectObjectFromDB(temp_user);
            if (temp_user != null) {
                status_info = "该用户已经存在,请更换用户名";
            } else {
                // Step 5: 信息填写成功,在数据库中插入数据
                // 设置普通用户的权限
                user.setProperty(User.PRIVILEGE, User.PRIVILEGE_NORMAL);
                user.setProperty(User.STATUS, User.STATUS_INACTIVE);

                isSuccessed = mDBOperator.insertObjectToDB(user);

                if (!isSuccessed) {
                    status_info = "数据库插入失败";
                }
            }
        }
        
        // Step 6: 注册成功,跳转到登录界面 
        if (isSuccessed) {
            status_info = "注册成功,请登录";
            request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(STATUS_INFO_FLAG, "positive");
            goJSP("/index.jsp", request, response);
        } 
        // 注册失败
        else {
            Factory.pushObjectIntoRequestAttribute(request, user);
            request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(USER_TYPE, USER_NOARMAL);
            goJSP("/register.jsp", request, response);
        }
    }

    // 处理企业用户注册操作
    private void processRegisterEnterpriseUserAction(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        String password = null;
        String confirm_password = null;
        String enterprisename = null;
        String year = null;
        String month = null;
        String day = null;
        String establishtime = null;
        boolean isSuccessed = false;

        // 企业用户的注册操作相对复杂一些         
        // Step 1: 从请求中获取用户对象 
        UserInfo userinfo = (UserInfo) Factory.getObjectFromRequest(request, new UserInfo());
        username = userinfo.getProperty(UserInfo.USERNAME);
        password = userinfo.getProperty(UserInfo.PASSWORD);
        Enterprise enterprise = (Enterprise) Factory.getObjectFromRequest(request, new Enterprise());
        enterprisename = enterprise.getProperty(Enterprise.ENTERPRISENAME);

        confirm_password = request.getParameter(UserServlet.CONFIRM_PASSWORD);
        year = request.getParameter(UserServlet.YEAR);
        month = request.getParameter(UserServlet.MONTH);
        day = request.getParameter(UserServlet.DAY);

        // Step 2: 检查用户输入
        if (username == null|| password == null || confirm_password == null || enterprisename == null ||
            username.length() == 0 || password.length() == 0 || 
            confirm_password.length() == 0 || enterprisename.length() == 0 || 
            Factory.checkIsObjectPropertiesNullExcept(enterprise, 
                Enterprise.ESTABLISHMENTTIME, Enterprise.ID, Enterprise.LOGOPIC)) {
            status_info = "请输入完整信息";
        } 
        // Step 3: 密码校验
        else if (!password.equals(confirm_password)) {
            status_info = "密码校验错误, 请重新输入";
        } 
        // Step 4: Establish Time输入校验
        else if (year == null || month == null || day == null ||
                year.length() == 0 || month.length() == 0 || day.length() == 0) {
            status_info = "请填写公司完整创建时间";
        } else if (!StringSnippet.checkIsDate((establishtime = year + "-" + month + "-" + day))) {
            status_info = "请填写正确的公司创建时间";
        }
        // Step 5: 检查用户是否重名
        else {
            // 插入Establish Time
            enterprise.setProperty(Enterprise.ESTABLISHMENTTIME, establishtime);

            UserInfo temp_userinfo = new UserInfo(username, "", "");
            temp_userinfo = (UserInfo) mDBOperator.selectObjectFromDB(temp_userinfo);
            User temp_user = new User(username, "", "", "");
            temp_user = (User) mDBOperator.selectObjectFromDB(temp_user);

            if (temp_userinfo != null || temp_user != null) {
                status_info = "该用户已经存在,请更换用户名";
            } 
            // Step 6: 检查企业名是否重复
            else {
                Enterprise temp_enterprise = new Enterprise(enterprisename, "", "", "", 
                        "", "", "", "");
                temp_enterprise = (Enterprise) mDBOperator.selectObjectFromDB(temp_enterprise);
                if (temp_enterprise != null) {
                    status_info = "该企业名已经存在,请更换企业名";
                }
                // Step 7: 信息填写正确,向数据库中插入数据
                else {
                    isSuccessed = mDBOperator.insertObjectToDB(enterprise);
                    
                    if (isSuccessed) {
                        userinfo.setProperty(UserInfo.ENTERPRISEID, 
                                enterprise.getProperty(Enterprise.ID));
                        isSuccessed = mDBOperator.insertObjectToDB(userinfo);
                    }

                    if (!isSuccessed) {
                        status_info = "数据库插入失败";
                    }
                }
            }
        }
        
        // Step 8: 注册成功,跳转到登录界面 
        if (isSuccessed) {
            status_info = "注册成功,请登录";
            request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(STATUS_INFO_FLAG, "positive");
            goJSP("/index.jsp", request, response);
        } 
        // 注册失败
        else {
            Factory.pushObjectIntoRequestAttribute(request, enterprise);
            Factory.pushObjectIntoRequestAttribute(request, userinfo);
            request.setAttribute(YEAR, year);
            request.setAttribute(MONTH, month);
            request.setAttribute(DAY, day); request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(USER_TYPE, USER_ENTERPRISE);
            goJSP("/register.jsp", request, response);
        }
    }

    // 处理更新信息操作
    private void processUpdateUserAction(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        // 根据用户类型的不同分流到不同的界面
        String privilege = EnispSession.getPrivilege(request);
        if (privilege.equals(User.PRIVILEGE_NORMAL)) {
            goJSP("/changeNormalInfo.jsp", request, response);
        } else if(privilege.equals(User.PRIVLIEGE_ENTERPRISE)){
            String username = null;
            String password = null;
            String confirm_password = null;
            String enterprisename = null;
            String year = null;
            String month = null;
            String day = null;
            String establishtime = null;
            UserInfo userInfo = new UserInfo();
            Enterprise enterprise = new Enterprise();
            userInfo.setProperty(UserInfo.ID, EnispSession.getUserId(request));
            userInfo = (UserInfo) mDBOperator.selectObjectFromDB(userInfo);
            enterprise.setProperty(Enterprise.ID, userInfo.getProperty(UserInfo.ENTERPRISEID));
            enterprise = (Enterprise) mDBOperator.selectObjectFromDB(enterprise);

            String date = enterprise.getProperty(Enterprise.ESTABLISHMENTTIME);
            if (date != null && date.length() > 0) {
                String[] dates = date.split("-");
                year = dates[0];
                month = dates[1];
                day = dates[2];
            }

            Factory.pushObjectIntoRequestAttribute(request, enterprise);
            Factory.pushObjectIntoRequestAttribute(request, userInfo);
            request.setAttribute(YEAR, year);
            request.setAttribute(MONTH, month);
            request.setAttribute(DAY, day); request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(USER_TYPE, USER_ENTERPRISE);
            goJSP("/changeEnterpriseInfo.jsp", request, response);
        } else {
            goJSP("/error.jsp", request, response);
        }
    }

    // 处理提交更新信息操作
    private void processChangeInfoAction(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        String password = null;
        String confirm_password = null;

        // 修改密码校对(部分已经使用javascript完成)
        password = request.getParameter(User.PASSWORD).trim();
        confirm_password = request.getParameter(UserServlet.CONFIRM_PASSWORD).trim();
        if (password != null && confirm_password != null && password.equals(confirm_password)) {
             
        } else {
            status_info = "密码不相同,请重新输入";
            request.setAttribute(STATUS_INFO, status_info);
            goJSP("/UpdateUser", request, response);
        }

        // 根据用户类型的不同采取不同的操作
        String privilege = EnispSession.getPrivilege(request);

        if (privilege.equals(User.PRIVILEGE_NORMAL)) {
            User user = new User();
            user.setProperty(User.ID, EnispSession.getUserId(request));
            user = (User) mDBOperator.selectObjectFromDB(user);
            user.setProperty(User.PASSWORD, password);
            mDBOperator.updateObjectToDB(user);

            // 清空session, 退出
            EnispSession.invalidate(request);

            status_info = "信息修改成功,请重新登录";
            request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(STATUS_INFO_FLAG, "positive");
            goJSP("/index.jsp", request, response);
        } else if(privilege.equals(User.PRIVLIEGE_ENTERPRISE)){
            UserInfo userInfo = new UserInfo();
            Enterprise enterprise = new Enterprise();
            userInfo.setProperty(UserInfo.ID, EnispSession.getUserId(request));
            userInfo = (UserInfo) mDBOperator.selectObjectFromDB(userInfo);
            userInfo.setProperty(UserInfo.PASSWORD, password);
            userInfo.setProperty(UserInfo.ID, EnispSession.getUserId(request));
            enterprise.setProperty(Enterprise.ID, userInfo.getProperty(UserInfo.ENTERPRISEID));
            enterprise = (Enterprise) mDBOperator.selectObjectFromDB(enterprise);
            enterprise.setProperty(Enterprise.ENTERPRISENAME, request.getParameter(Enterprise.ENTERPRISENAME));
            enterprise.setProperty(Enterprise.ADDRESS, request.getParameter(Enterprise.ADDRESS));
            enterprise.setProperty(Enterprise.TELEPHONENUMBER, request.getParameter(Enterprise.TELEPHONENUMBER));
            enterprise.setProperty(Enterprise.FAXNUMABER, request.getParameter(Enterprise.FAXNUMABER));
            enterprise.setProperty(Enterprise.OFFICALWEBSITE, request.getParameter(Enterprise.OFFICALWEBSITE));
            enterprise.setProperty(Enterprise.EMAIL, request.getParameter(Enterprise.EMAIL));
            
            String year = request.getParameter(UserServlet.YEAR);
            String month = request.getParameter(UserServlet.MONTH);
            String day = request.getParameter(UserServlet.DAY);

            enterprise.setProperty(Enterprise.ESTABLISHMENTTIME, year + "-" + month + "-" + day);

            mDBOperator.updateObjectToDB(userInfo);
            mDBOperator.updateObjectToDB(enterprise);

            EnispSession.invalidate(request);

            status_info = "密码修改成功,请重新登录";
            request.setAttribute(STATUS_INFO, status_info);
            request.setAttribute(STATUS_INFO_FLAG, "positive");
            goJSP("/index.jsp", request, response);
        } else {
            goJSP("/error.jsp", request, response);
        }

    }
}
