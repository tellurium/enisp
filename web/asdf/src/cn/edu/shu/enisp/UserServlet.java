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
import cn.edu.shu.enisp.db.UserHandler;
import cn.edu.shu.enisp.factory.EnterpriseFactory;
import cn.edu.shu.enisp.factory.UserFactory;
import cn.edu.shu.enisp.model.Enterprise;
import cn.edu.shu.enisp.model.User;

@SuppressWarnings("serial")
public class UserServlet extends BaseServlet {
	private EnterpriseFactory mEnterpriseFactory = new EnterpriseFactory();
	private EnterpriseHandler mEnterpriseHandler = new EnterpriseHandler();
	private Enterprise mEnterprise = new Enterprise();
	private UserFactory mUserFactory = new UserFactory();
	private UserHandler mUserHandler = new UserHandler();
	private User mUser = new User();
	private String userid;
	private String enterpriseid;
	
	private String status_info;
	

	@Override
	public void insertAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		mEnterprise = mEnterpriseFactory.getObjectFromRequest(request);
		mUser = mUserFactory.getObjectFromRequest(request);
		// �û������Ҫ��֤
		// Step 1: �Ƿ�������û����д
		if(mUser.getUsername().length() == 0 || mUser.getPassword().length() == 0 || 
				mEnterprise.getAddress().length() == 0 || mEnterprise.getEmail().length() == 0 || 
				mEnterprise.getEnterprisename().length() == 0 || mEnterprise.getFaxnumaber().length() == 0 ||
				mEnterprise.getOfficalwebsite().length() == 0 || mEnterprise.getTelephonenumber().length() == 0 ||
				mEnterprise.getEstablishmenttime().length() == 0) {
			status_info = "�뽫��Ϣ��д����";
		} 
		// Step 2: �������������Ƿ�һ��
		else if(!mUser.getPassword().equals(request.getParameter("confirm_password"))) {
			status_info = "���벻һ�£���������������";
		}
		// Step 3: ������Ϣͨ����֤����ʼ���뵽���ݿ�, �Ȳ��뵽Enterprise����
		else if(!mEnterpriseHandler.executeDBOperation(ExecuteType.INSERT, mEnterprise, null)) {
			status_info = "���ݿ����ʧ��1";
		}
		// Step 4: ������Enterprise�ɹ��ٸ���enterprise��id���뵽User����
		else {
			List<Object> list = new ArrayList<Object>();
			if(!mEnterpriseHandler.executeDBOperation(ExecuteType.SELECT, mEnterprise, list)) {
				status_info = "���ݿ����ʧ��2";
			} else {
				mEnterprise = (Enterprise)list.get(0);
				mUser.setEnterpriseid(mEnterprise.getId());
				if(!mUserHandler.executeDBOperation(ExecuteType.INSERT, mUser, null)) {
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
		request.setAttribute(User.USERNAME, mUser.getUsername());
		
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
		mUser = mUserFactory.getObjectFromRequest(request);
		
		HttpSession session = request.getSession(true);
		userid = (String) session.getAttribute("userid");
		enterpriseid = (String) session.getAttribute("enterpriseid");
		
		mEnterprise.setId(enterpriseid);
		mUser.setId(userid);
		// �û������Ҫ��֤
		// Step 1: �Ƿ�������û����д
		if(mUser.getUsername().length() == 0 || mUser.getPassword().length() == 0 || 
				mEnterprise.getAddress().length() == 0 || mEnterprise.getEmail().length() == 0 || 
				mEnterprise.getEnterprisename().length() == 0 || mEnterprise.getFaxnumaber().length() == 0 ||
				mEnterprise.getOfficalwebsite().length() == 0 || mEnterprise.getTelephonenumber().length() == 0 ||
				mEnterprise.getEstablishmenttime().length() == 0) {
			status_info = "�뽫��Ϣ��д����";
		} 
		// Step 2: �������������Ƿ�һ��
		else if(!mUser.getPassword().equals(request.getParameter("confirm_password"))) {
			status_info = "���벻һ�£���������������";
		}
		else if(!mEnterpriseHandler.executeDBOperation(ExecuteType.UPDATE, mEnterprise, null)) {
			status_info = "���ݿ����ʧ��1";
		}
		else if(!mUserHandler.executeDBOperation(ExecuteType.UPDATE, mUser, null)) {
			status_info = "���ݿ����ʧ��2";
		}
		else {
			status_info = "��Ϣ���³ɹ������¼";
			request.setAttribute("status_info", status_info);
			goJSP("/index.jsp", request, response);
			return;
		}
		
		request.setAttribute("status_info", status_info);
		request.setAttribute(User.USERNAME, mUser.getUsername());
		
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
			mUser.setId(userid);
			mEnterprise.setId(enterpriseid);
			
			List<Object> userList = new ArrayList<Object>();
			if(!mUserHandler.executeDBOperation(ExecuteType.SELECT, mUser, userList)) {
				status_info = "���ݿ�ʧ��1";
			} else {
				mUser = (User) userList.get(0);
				List<Object> enterpriseList = new ArrayList<Object>();
				if(!mEnterpriseHandler.executeDBOperation(ExecuteType.QUERY, mEnterprise, enterpriseList)) {
					status_info = "���ݿ�ʧ��2";
				} else {
					mEnterprise = (Enterprise) enterpriseList.get(0);
				}
			}
		}
		request.setAttribute("status_info", status_info);
		
		request.setAttribute(User.USERNAME, mUser.getUsername());
		
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
