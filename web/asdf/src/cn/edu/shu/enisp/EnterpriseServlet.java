package cn.edu.shu.enisp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.db.DBOperator;
import cn.edu.shu.enisp.model.BaseModel;
import cn.edu.shu.enisp.model.Enterprise;
import cn.edu.shu.enisp.session.EnispSession;

@SuppressWarnings("serial")
public class EnterpriseServlet extends BaseServlet {

    // actions
    public static final String CHANGEPARTNER = "change-partner";
    public static final String UPLOADLOGO = "upload-logo";

    public static final String PARTNER_LIST = "enterprise-list";

    @Override
    public void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        if (action.equals(CHANGEPARTNER)) {
            processChangePartnerAction(request, response);            
        } else if (action.equals(UPLOADLOGO)) {
            processUploadLogoAction(request, response);
        } else {
            showError(request, response);
        }
    }

    private void processChangePartnerAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
       
        String enterprise_id = EnispSession.getEnterpriseId(request);
        String sql_str = "select * from enterprisetable where id in " +
                         "(select partnerid from cooperativeenttable where id = " +
                         enterprise_id +
                         ")";
        List<BaseModel> partner_list = mDBOperator.selectObjectListBySQL(sql_str, new Enterprise());
        request.setAttribute(PARTNER_LIST, partner_list);
        goJSP("/changePartner.jsp", request, response);
    }

    private void processUploadLogoAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        goJSP("/uploadLogo.jsp", request, response);
    }
}
