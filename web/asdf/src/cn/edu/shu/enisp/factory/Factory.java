package cn.edu.shu.enisp.factory;

import javax.servlet.http.HttpServletRequest;

public abstract class Factory {
	public abstract Object getObjectFromRequest(HttpServletRequest request);
	
	public String getFormatedRequestParameter(HttpServletRequest request, String parameterName) {
		String result = request.getParameter(parameterName);
		if (result == null || result.length() == 0) return "";
		return result;
	}
}
