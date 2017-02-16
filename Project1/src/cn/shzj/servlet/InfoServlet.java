package cn.shzj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.htmlparser.util.ParserException;

import cn.shzj.service.Service;

public class InfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String str = request.getParameter("requestInfo");
		String name = request.getParameter("userlink");
		Service service = new Service();
		ArrayList<String> li = null;
		try {
			li = service.getDetailInfo(str,name);
		} catch (ParserException e) {
			response.getWriter().write("ParserException");
		}
		//数据写出
		String finalstr = JSONArray.fromObject(li).toString();
		PrintWriter pw = response.getWriter();
		pw.print(finalstr);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
