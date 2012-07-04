package cn.edu.shu.enisp.factory;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.BaseModel;

public abstract class Factory {

    public static BaseModel getObjectFromRequest(HttpServletRequest request, BaseModel model) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            model.setProperty(key, getFormatedRequestParameter(request, key));
        }
        return model;
    }

    private static String getFormatedRequestParameter(HttpServletRequest request, String parameterName) {
		String result = request.getParameter(parameterName);
		if (result == null || result.length() == 0) return "";
		return result;
	}
}
