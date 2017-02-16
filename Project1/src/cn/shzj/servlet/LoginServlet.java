package cn.shzj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmlparser.util.ParserException;

import cn.shzj.domain.User;
import cn.shzj.service.Service;
import cn.shzj.utils.TrimString;

public class LoginServlet extends HttpServlet {
	//获取用户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取链接,并裁剪
		String link = request.getParameter("link");
		String userlink = TrimString.getTrimString(link);
		User user = null;
		//将裁剪好的链接段传给service处理,获取user对象
		try {
			user = new Service().getUserInfo(userlink);
		} catch (ParserException e) {
			request.getRequestDispatcher("/JSP/fail.jsp").forward(request, response);
		}
		//判断user是否为空,非空,则跳转到success界面
		if(user != null){
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/JSP/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/JSP/fail.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
