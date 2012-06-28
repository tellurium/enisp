package cn.edu.shu.enisp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.shu.enisp.db.DBHandler.ExecuteType;
import cn.edu.shu.enisp.db.EnterpriseHandler;
import cn.edu.shu.enisp.db.UserInfoHandler;
import cn.edu.shu.enisp.factory.EnterpriseFactory;
import cn.edu.shu.enisp.factory.UserInfoFactory;
import cn.edu.shu.enisp.model.Enterprise;
import cn.edu.shu.enisp.model.UserInfo;

@SuppressWarnings("serial")
public class UserInfoServlet extends BaseServlet {
	private EnterpriseFactory mEnterpriseFactory = new EnterpriseFactory();
	private EnterpriseHandler mEnterpriseHandler = new EnterpriseHandler();
	private Enterprise mEnterprise = new Enterprise();
	private UserInfoFactory mUserInfoFactory = new UserInfoFactory();
	private UserInfoHandler mUserInfoHandler = new UserInfoHandler();
	private UserInfo mUserInfo = new UserInfo();
	private String userid;
	private String enterpriseid;
	
	private String status_info;
	

	@Override
	public void insertAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		mEnterprise = mEnterpriseFactory.getObjectFromRequest(request);
		mUserInfo = mUserInfoFactory.getObjectFromRequest(request);
		// �û������Ҫ��֤
		// Step 1: �Ƿ�������û����д
		if(mUserInfo.getUserInfoname().length() == 0 || mUserInfo.getPassword().length() == 0 || 
				mEnterprise.getAddress().length() == 0 || mEnterprise.getEmail().length() == 0 || 
				mEnterprise.getEnterprisename().length() == 0 || mEnterprise.getFaxnumaber().length() == 0 ||
				mEnterprise.getOfficalwebsite().length() == 0 || mEnterprise.getTelephonenumber().length() == 0 ||
				mEnterprise.getEstablishmenttime().length() == 0) {
			status_info = "�뽫��Ϣ��д����";
		} 
		// Step 2: �������������Ƿ�һ��
		else if(!mUserInfo.getPassword().equals(request.getParameter("confirm_password"))) {
			status_info = "���벻һ�£���������������";
		}
		// Step 3: ������Ϣͨ����֤����ʼ���뵽���ݿ�, �Ȳ��뵽Enterprise����
		else if(!mEnterpriseHandler.executeDBOperation(ExecuteType.INSERT, mEnterprise, null)) {
			status_info = "���ݿ����ʧ��1";
		}
		// Step 4: ������Enterprise�ɹ��ٸ���enterprise��id���뵽UserInfo����
		else {
			List<Object> list = new ArrayList<Object>();
			if(!mEnterpriseHandler.executeDBOperation(ExecuteType.SELECT, mEnterprise, list)) {
				status_info = "���ݿ����ʧ��2";
			} else {
				mEnterprise = (Enterprise)list.get(0);
				mUserInfo.setEnterpriseid(mEnterprise.getId());
				if(!mUserInfoHandler.executeDBOperation(ExecuteType.INSERT, mUserInfo, null)) {
					status_info = "���ݿ����ʧ��3";
				} else {
					status_info = "ע��ɹ������¼";
					request.setAttribute("status_info", status_info);
					goJSP("/index.jsp", request, response);
					return;
				}
			}
		}
		
		request.setAttribute("status_info", status_info);
		request.setAttribute(UserInfo.USERNAME, mUserInfo.getUserInfoname());
		
		request.setAttribute(Enterprise.ADDRESS, mEnterprise.getAddress());
		request.setAttribute(Enterprise.ENTERPRISENAME, mEnterprise.getEnterprisename());
		request.setAttribute(Enterprise.OFFICALWEBSITE, mEnterprise.getOfficalwebsite());
		request.setAttribute(Enterprise.EMAIL, mEnterprise.getEmail());
		request.setAttribute(Enterprise.TELEPHONENUMBER, mEnterprise.getTelephonenumber());
		request.setAttribute(Enterprise.FAXNUMABER, mEnterprise.getFaxnumaber());
		request.setAttribute(Enterprise.ESTABLISHMENTTIME, mEnterprise.getEstablishmenttime());
		goJSP("/register.jsp", request, response);
	}

	@Override
	public void deleteAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void updateAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		mEnterprise = mEnterpriseFactory.getObjectFromRequest(request);
		mUserInfo = mUserInfoFactory.getObjectFromRequest(request);
		
		HttpSession session = request.getSession(true);
		userid = (String) session.getAttribute("userid");
		enterpriseid = (String) session.getAttribute("enterpriseid");
		
		mEnterprise.setId(enterpriseid);
		mUserInfo.setId(userid);
		// �û������Ҫ��֤
		// Step 1: �Ƿ�������û����д
		if(mUserInfo.getUserInfoname().length() == 0 || mUserInfo.getPassword().length() == 0 || 
				mEnterprise.getAddress().length() == 0 || mEnterprise.getEmail().length() == 0 || 
				mEnterprise.getEnterprisename().length() == 0 || mEnterprise.getFaxnumaber().length() == 0 ||
				mEnterprise.getOfficalwebsite().length() == 0 || mEnterprise.getTelephonenumber().length() == 0 ||
				mEnterprise.getEstablishmenttime().length() == 0) {
			status_info = "�뽫��Ϣ��д����";
		} 
		// Step 2: �������������Ƿ�һ��
		else if(!mUserInfo.getPassword().equals(request.getParameter("confirm_password"))) {
			status_info = "���벻һ�£���������������";
		}
		else if(!mEnterpriseHandler.executeDBOperation(ExecuteType.UPDATE, mEnterprise, null)) {
			status_info = "���ݿ����ʧ��1";
		}
		else if(!mUserInfoHandler.executeDBOperation(ExecuteType.UPDATE, mUserInfo, null)) {
			status_info = "���ݿ����ʧ��2";
		}
		else {
			status_info = "��Ϣ���³ɹ������¼";
			request.setAttribute("status_info", status_info);
			goJSP("/index.jsp", request, response);
			return;
		}
		
		request.setAttribute("status_info", status_info);
		request.setAttribute(UserInfo.USERNAME, mUserInfo.getUserInfoname());
		
		request.setAttribute(Enterprise.ADDRESS, mEnterprise.getAddress());
		request.setAttribute(Enterprise.ENTERPRISENAME, mEnterprise.getEnterprisename());
		request.setAttribute(Enterprise.OFFICALWEBSITE, mEnterprise.getOfficalwebsite());
		request.setAttribute(Enterprise.EMAIL, mEnterprise.getEmail());
		request.setAttribute(Enterprise.TELEPHONENUMBER, mEnterprise.getTelephonenumber());
		request.setAttribute(Enterprise.FAXNUMABER, mEnterprise.getFaxnumaber());
		request.setAttribute(Enterprise.ESTABLISHMENTTIME, mEnterprise.getEstablishmenttime());
		goJSP("/changeinfo.jsp", request, response);
	}

	@Override
	public void selectAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		userid = (String) session.getAttribute("userid");
		enterpriseid = (String) session.getAttribute("enterpriseid");
		
		if(userid == null || userid.length() == 0 || enterpriseid == null || enterpriseid.length() == 0) {
			status_info = "����δ��¼...";
		} else {
			mUserInfo.setId(userid);
			mEnterprise.setId(enterpriseid);
			
			List<Object> userList = new ArrayList<Object>();
			if(!mUserInfoHandler.executeDBOperation(ExecuteType.SELECT, mUserInfo, userList)) {
				status_info = "���ݿ�ʧ��1";
			} else {
				mUserInfo = (UserInfo) userList.get(0);
				List<Object> enterpriseList = new ArrayList<Object>();
				if(!mEnterpriseHandler.executeDBOperation(ExecuteType.QUERY, mEnterprise, enterpriseList)) {
					status_info = "���ݿ�ʧ��2";
				} else {
					mEnterprise = (Enterprise) enterpriseList.get(0);
				}
			}
		}
		request.setAttribute("status_info", status_info);
		
		request.setAttribute(UserInfo.USERNAME, mUserInfo.getUserInfoname());
		
		request.setAttribute(Enterprise.ADDRESS, mEnterprise.getAddress());
		request.setAttribute(Enterprise.ENTERPRISENAME, mEnterprise.getEnterprisename());
		request.setAttribute(Enterprise.OFFICALWEBSITE, mEnterprise.getOfficalwebsite());
		request.setAttribute(Enterprise.EMAIL, mEnterprise.getEmail());
		request.setAttribute(Enterprise.TELEPHONENUMBER, mEnterprise.getTelephonenumber());
		request.setAttribute(Enterprise.FAXNUMABER, mEnterprise.getFaxnumaber());
		request.setAttribute(Enterprise.ESTABLISHMENTTIME, mEnterprise.getEstablishmenttime());
		goJSP("/changeinfo.jsp", request, response);
	}

	@Override
	public void otherAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
