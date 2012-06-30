package cn.edu.shu.enisp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

import cn.edu.shu.enisp.db.DBOperator;
import cn.edu.shu.enisp.model.Test;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Test Start");

        // Initialization...
        DBOperator operator = new DBOperator();
        boolean isSuccessed = false;

        // 
        out.println("\n--- Model Test ---");
        Test test = new Test("text value", "des value", "time value");
        out.println(test.TABLENAME);
        out.println(test.getProperty(Test.DES));
		
        // ORM 插入功能
        out.println("\n--- Insert Test ---");
        isSuccessed = false;
        isSuccessed = operator.insertObjectToDB(test); 
        if (isSuccessed) {
            out.println("Insert successed");
        } else {
            out.println("Insert failed");
        }

        // ORM 更新功能
        out.println("\n--- Update Test ---");
        isSuccessed = false;
        isSuccessed = operator.updateObjectToDB(test); 
        if (isSuccessed) {
            out.println("Update successed");
        } else {
            out.println("Update failed");
        }

        // ORM 查询功能
        out.println("\n--- Select Test ---");
        out.println("\n1.Select Test");
        ResultSet result1 = operator.selectObjectFromDB(test); 
        if (result1 != null) {
            out.println("Select successed");
        } else {
            out.println("Select failed");
        }
        
        out.println("\n2.Select Test");
        Test t = new Test("1", "", "", "");
        ResultSet result2 = operator.selectObjectFromDB(t); 
        if (result2 != null) {
            out.println("Select successed");
        } else {
            out.println("Select failed");
        }

        // ORM 删除功能
        out.println("\n--- Delete Test ---");
        isSuccessed = false;
        isSuccessed = operator.deleteObjectFromDB(test); 
        if (isSuccessed) {
            out.println("Delete successed");
        } else {
            out.println("Delete failed");
        }

        out.println("\nTest End");
	}
}
