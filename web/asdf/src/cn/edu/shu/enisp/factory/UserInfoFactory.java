package cn.edu.shu.enisp.factory;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.UserInfo;

public class UserInfoFactory extends Factory {
	
	@Override
	public UserInfo getObjectFromRequest(HttpServletRequest request) {
		UserInfo user = new UserInfo();
		user.setPassword(getFormatedRequestParameter(request, UserInfo.PASSWORD));
		user.setUserInfoname(getFormatedRequestParameter(request, UserInfo.USERNAME));
		user.setEnterpriseid(getFormatedRequestParameter(request, UserInfo.ENTERPRISEID));
		return user;
	}
}
