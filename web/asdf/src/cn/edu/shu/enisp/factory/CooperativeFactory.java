package cn.edu.shu.enisp.factory;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.Cooperative;

public class CooperativeFactory extends Factory {

	@Override
	public Cooperative getObjectFromRequest(HttpServletRequest request) {
		Cooperative cooperative = new Cooperative();
		cooperative.setSourceid(request.getParameter(Cooperative.SOURCEID));
		cooperative.setTargetid(request.getParameter(Cooperative.TARGETID));
		return cooperative;
	}

}
