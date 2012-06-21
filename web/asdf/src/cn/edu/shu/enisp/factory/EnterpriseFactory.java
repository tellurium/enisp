package cn.edu.shu.enisp.factory;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shu.enisp.model.Enterprise;

public class EnterpriseFactory extends Factory {

	@Override
	public Enterprise getObjectFromRequest(HttpServletRequest request) {
		Enterprise enterprise = new Enterprise();
		enterprise.setAddress(getFormatedRequestParameter(request, Enterprise.ADDRESS));
		enterprise.setEmail(getFormatedRequestParameter(request, Enterprise.EMAIL));
		enterprise.setEnterprisename(getFormatedRequestParameter(request, Enterprise.ENTERPRISENAME));
		enterprise.setEstablishmenttime(getFormatedRequestParameter(request, Enterprise.ESTABLISHMENTTIME));
		enterprise.setFaxnumaber(getFormatedRequestParameter(request, Enterprise.FAXNUMABER));
		enterprise.setLogopic(getFormatedRequestParameter(request, Enterprise.LOGOPIC));
		enterprise.setOfficalwebsite(getFormatedRequestParameter(request, Enterprise.OFFICALWEBSITE));
		enterprise.setTelephonenumber(getFormatedRequestParameter(request, Enterprise.TELEPHONENUMBER));
		return enterprise;
	}

}
