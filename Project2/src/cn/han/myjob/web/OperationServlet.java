package cn.han.myjob.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.han.myjob.service.BusService;
import cn.han.myjob.utils.AlertInfo;

public class OperationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusService dservice = new BusService();
		// requestStatus请求参数状态码,说明需要操作第几步
		// sessionStatus状态码,说明上一步是否完成
		String requestStatus = request.getParameter("status");
		// 执行第一个步骤,擦除city库citylinks表并创建新citylinks表
		if (requestStatus.equals("1s")) {
			String sessionStatusBack = dservice.firstStep(); // 获取操作状态返回值---是否成功
			decideForword(requestStatus, sessionStatusBack, request, response);
		}
		//如果想要执行其他操作,先判断session是否为空,不为空则接下去操作
		else if (request.getSession().getAttribute("sessionStatus") == null) {
			request.setAttribute("success", "未完成任何操作");
			request.getRequestDispatcher("/JSP/success.jsp").forward(request,
					response);
		}
		// 进行到第二个步骤,并导入数据到库line
		else if (requestStatus.equals("2s")
				&& request.getSession().getAttribute("sessionStatus")
						.equals("1e")) {
			String sessionStatusBack = dservice.secondStep(); // 获取操作状态返回值---是否成功
			decideForword(requestStatus, sessionStatusBack, request, response);
		}
		// 进行到第三个步骤,并导入数据到库station
		else if (requestStatus.equals("3s")
				&& request.getSession().getAttribute("sessionStatus")
						.equals("2e")) {
			String sessionStatusBack = dservice.thirdStep(); // 获取操作状态返回值---是否成功
			decideForword(requestStatus, sessionStatusBack, request, response);
		}
		//如果session不为空,那有可能是1e,2e,3e
		else if (request.getSession().getAttribute("sessionStatus") != null) {
			decideForword(requestStatus, (String)request.getSession().getAttribute("sessionStatus"), request, response);
		}
	}

	// 操作完成之后,转发到success界面,并反馈操作进行到那个步骤了.
	public void decideForword(String requestStatus, String sessionStatusBack,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!sessionStatusBack.equals("fail")) {
			request.getSession().setAttribute("sessionStatus",
					sessionStatusBack);
			// 放入请求状态值,session状态值,得到返回信息
			String info = AlertInfo.getAlert(requestStatus, sessionStatusBack);
			request.setAttribute("success", info);
			request.getRequestDispatcher("/JSP/success.jsp").forward(request,
					response);
		} else {
			String info = AlertInfo.getAlert(requestStatus, sessionStatusBack);
			request.setAttribute("success", info);
			request.getRequestDispatcher("/JSP/success.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
