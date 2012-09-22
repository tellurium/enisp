package cn.edu.shu.enisp;

import java.io.IOException;

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
public class CooperativentServlet extends BaseServlet {
    // jsp attributes
    public static final String MESSAGE = "message";

    // actions
    public static final String DELETE_COOPERATIVENT = "delete-coop";
    public static final String ADD_COOPERATIVENT = "add-coop";

    // parameters
    public static final String PARTNER_ID = "id";

    @Override
    public void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        if (action.equals(DELETE_COOPERATIVENT)) {
            processDeleteCooperativentAction(request, response);
        } else if (action.equals(ADD_COOPERATIVENT)) {
            processAddCooperativentAction(request, response);
        } else {
            showError(request, response);
        }
    }

    private void processDeleteCooperativentAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String enterprise_id = EnispSession.getEnterpriseId(request);
        String partner_id = request.getParameter(PARTNER_ID);

        String sql_str = "delete from cooperativeenttable where id=" +
                         enterprise_id + " and partnerid=" + partner_id;

        if (mDBOperator.executeSQL(sql_str)) {
            request.setAttribute(MESSAGE, "删除成功");
            goJSP("/ChangePartner", request, response);
        }
    }

    private void processAddCooperativentAction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String enterprise_id = EnispSession.getEnterpriseId(request);
        String partner_id = request.getParameter(PARTNER_ID);
        
        String sql_str = "insert into cooperativeenttable(id, partnerid) values(" +
                         enterprise_id + ", " + partner_id + 
                         ")";
        
        if (mDBOperator.executeSQL(sql_str)) {
            request.setAttribute(MESSAGE, "添加成功");
            goJSP("/ChangePartner", request, response);
        }
    }
}
