package cn.edu.shu.enisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.db.DBOperator;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
	public static final String ACTION = "action";
    private String mAction;

    // main db operator
    protected DBOperator mDBOperator;

    public BaseServlet() {
        super();
        mDBOperator = new DBOperator();
    }

    public abstract void processAction(String action, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException;
        
    protected void showError(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        out(response, "Something wrong.");
        out(response, "From " + this.getClass());
    }
	
	protected void out(HttpServletResponse response, String outString) 
			throws ServletException, IOException {
		response.getWriter().println(outString);
	}
	
	protected void goJSP(String jspName, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		getServletContext().getRequestDispatcher(jspName).forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		mAction = getServletConfig().getInitParameter(ACTION);
        processAction(mAction, request, response);
	}
	
	@Override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		doPost(request, response);
	}
}
