package cn.edu.shu.enisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
	public static final String ACTION = "action";
	
	public static final String INSERT = "insert";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String SELECT = "select";
	
	private String mAction;
	
	public abstract void insertAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	public abstract void deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	public abstract void updateAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	public abstract void selectAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	public abstract void otherAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	
	public void out(HttpServletResponse response, String outString) 
			throws ServletException, IOException {
		response.getWriter().println(outString);
	}
	
	public void goJSP(String jspName, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		getServletContext().getRequestDispatcher(jspName).forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		mAction = getServletConfig().getInitParameter(ACTION);
		if(mAction.equals(INSERT)) {
			insertAction(request, response);
		} else if(mAction.equals(DELETE)) {
			deleteAction(request, response);
		} else if(mAction.equals(UPDATE)) {
			updateAction(request, response);
		} else if(mAction.equals(SELECT)) {
			selectAction(request, response);
		} else {
			otherAction(request, response);
		}
	}
	
	@Override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		doPost(request, response);
	}
}
