package cn.edu.shu.enisp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.enisp.db.mapper.TestMapper;
import cn.edu.shu.enisp.model.Test;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Test Start");
		
        // ORM 插入功能
        out.println("--- Insert Test ---");
        Test test = new Test();
        out.println(TestMapper.TABLENAME); 

        

        // ORM 删除功能
        out.println("--- Delete Test ---");

        // ORM 更新功能
        out.println("--- Update Test ---");

        // ORM 查询功能
        out.println("--- Select Test ---");

        out.println("Test End");
	}
}
