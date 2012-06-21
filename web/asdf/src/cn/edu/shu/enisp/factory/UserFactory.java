package cn.edu.shu.enisp.factory;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.User;

public class UserFactory extends Factory {
	
	@Override
	public User getObjectFromRequest(HttpServletRequest request) {
		User user = new User();
		user.setPassword(getFormatedRequestParameter(request, User.PASSWORD));
		user.setUsername(getFormatedRequestParameter(request, User.USERNAME));
		user.setEnterpriseid(getFormatedRequestParameter(request, User.ENTERPRISEID));
		return user;
	}
}
