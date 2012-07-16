package cn.edu.shu.enisp.factory;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.BaseModel;

public abstract class Factory {

    // 从一个HttpServletRequest对象中获取对象
    public static BaseModel getObjectFromRequest(HttpServletRequest request, BaseModel model) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            model.setProperty(key, getFormatedRequestParameter(request, key));
        }
        return model;
    }
    
    public static BaseModel getObjectFromRequestAttribute(HttpServletRequest request, BaseModel model) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            model.setProperty(key, getFormatedRequestAttribute(request, key));
        }
        return model;
    }

    public static void pushObjectIntoRequestAttribute(HttpServletRequest request, BaseModel model) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            request.setAttribute(key, model.getProperty(key));
        }
    }

    // 测试除了指定字段外对象的属性是否都为空值
    public static  boolean checkIsObjectPropertiesNullExcept(BaseModel model, 
            String ... exceptionsKeys) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            
            boolean isInExceptions = false;
            for (String exceptionKey: exceptionsKeys ) {
                if (key.equals(exceptionKey)) {
                    isInExceptions = true;
                    break;
                }
            }
            if (isInExceptions) {
                continue;
            }

            String value = (String) entry.getValue();

            if (value == null || value.length() == 0) {
                return true;
            }
        }
        return false;
    }

    // 测试对象的属性是否都为空值
    public static boolean checkIsObjectAllPropertiesNull(BaseModel model) {
        Iterator iterator = model.getIterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String value = (String) entry.getValue();
            if (value == null || value.length() == 0) {
                return true;
            }
        }
        return false;
    }

    private static String getFormatedRequestParameter(HttpServletRequest request, String parameterName) {
		String result = (String) request.getParameter(parameterName);
		if (result == null || result.length() == 0) return "";
		return result;
	}
    
    private static String getFormatedRequestAttribute(HttpServletRequest request, String attributeName) {
		String result = (String) request.getAttribute(attributeName);
		if (result == null || result.length() == 0) return "";
		return result;
	}

}
