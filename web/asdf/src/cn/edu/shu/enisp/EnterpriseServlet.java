package cn.edu.shu.enisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EnterpriseServlet extends BaseServlet {

    // actions
    public static final String CHANGEPARTNER = "change-partner";
    public static final String UPLOADLOGO = "upload-logo";

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
        goJSP("/changePartner.jsp", request, response);
    }

    private void processUploadLogoAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        goJSP("/uploadLogo.jsp", request, response);
    }
}
