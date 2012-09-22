package cn.edu.shu.enisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AdminServlet extends BaseServlet {

    // actions
    public static final String USERADMIN = "user-admin";

    @Override
    public void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        if (action.equals(USERADMIN)) {
            processUserAdminAction(request, response);
        }
    }

    private void processUserAdminAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        goJSP("/userAdmin.jsp", request, response);
    }
}
