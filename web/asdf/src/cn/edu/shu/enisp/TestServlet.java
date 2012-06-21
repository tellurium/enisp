package cn.edu.shu.enisp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.db.DBHandler.ExecuteType;
import cn.edu.shu.enisp.db.EnterpriseHandler;
import cn.edu.shu.enisp.model.Enterprise;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	// private UserFactory userFactory = new UserFactory();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		User user = new User();
//		user.setId("1");
//		user.setUsername("hello");
//		user.setPassword("123456");
//		user.setEnterpriseid("15");
//		DBHandler userHandler = new UserHandler();
//		
//		PrintWriter out = response.getWriter();
//		
//		List<Object> list = new ArrayList<Object>();
//		if (userHandler.executeDBOperation(ExecuteType.SELECT, user, list)) {
//			user = (User)list.get(0);
//			out.println("User is: ");
//			out.println(user.getId());
//			out.println(user.getUsername());
//			out.println(user.getPassword());
//			out.println(user.getEnterpriseid());
//		} else {
//			out.println("fail");
//		}
//		if (userHandler.executeDBOperation(ExecuteType.INSERT, user, null)) {
//			user.setUsername("updated user name");
//			if (userHandler.executeDBOperation(ExecuteType.UPDATE, user, null)) {
//				out.println("success");
//			} else {
//				out.println("fail");
//			}
//		} else {
//			out.println("fail");
//		}
		
		Enterprise enterprise = new Enterprise();
		EnterpriseHandler enterpriseHandler = new EnterpriseHandler();
		enterprise.setAddress("updated address");
		enterprise.setEmail("test mail");
		enterprise.setEnterprisename("test enterprisename");
		enterprise.setFaxnumaber("99888");
		enterprise.setOfficalwebsite("wwww.baidu.com");
		enterprise.setTelephonenumber("9998");
		enterprise.setEstablishmenttime("2012-06-12");
		
		PrintWriter out = response.getWriter();
		
		List<Object> list = new ArrayList<Object>();
		if (enterpriseHandler.executeDBOperation(ExecuteType.SELECT, enterprise, list)) {
			enterprise = (Enterprise)list.get(0);
			out.println("Enterprise is: ");
			out.println(enterprise.getId());
			out.println(enterprise.getAddress());
			out.println(enterprise.getTelephonenumber());
			out.println(enterprise.getEmail());
		} else {
			out.println("fail");
		}
		
		
//		Enterprise enterprise = new Enterprise();
//		EnterpriseHandler enterpriseHandler = new EnterpriseHandler();
//		enterprise.setId("26");
//		enterprise.setAddress("updated address");
//		enterprise.setEmail("test mail");
//		enterprise.setEnterprisename("test enterprisename");
//		enterprise.setFaxnumaber("99888");
//		enterprise.setOfficalwebsite("wwww.baidu.com");
//		enterprise.setLogopic("logopic");
//		enterprise.setTelephonenumber("9998");
//		enterprise.setEstablishmenttime("2012-06-12");
//		
//		PrintWriter out = response.getWriter();
//		
//		if(enterpriseHandler.executeDBOperation(ExecuteType.DELETE, enterprise, null)) {
//			out.println("success");
//		} else {
//			out.println("fail");
//		}
		
	}
}
