<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/JSP/xmlhttpjs.js"></script>
<script type="text/javascript">
	function operate(str) {
		//创建一个ajax用来实现异步操作
		//1.得到XMLHttpRequest对象
		var xmlhttp = getXmlHttpRequest();
		//2.注册回调函数,通过ajax反馈数据给客户端浏览器
		xmlhttp.onreadystatechange = function() {
			//如果请求状态码是200,且readyState是4,表示页面接收数据
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("Div").innerHTML = "";
				//接收回传的json字符串
				var json = eval("(" + xmlhttp.responseText + ")");
				var message = document.getElementById("Div");
				message = "<table border = '1' width = '80%'>";
				//如果str等于topics,answers,questions中的某一项,那么就用这个方法
				if (str === 'topics' || str === 'answers'
						|| str === 'questions') {
					for ( var i = 0; i < json.length; i++) {
						message += "<tr><td>" + json[i] + "</td></tr>";
					}
				} else {
					for ( var i = 0; i < json.length/3.0; i = i + 3) {
						message += "<tr><td width='10%'>" + json[i] + "</td><td width='80%'>"
								+ json[i + 2] + "</td><td width='10%'>" + json[i + 1]
								+ "</td></tr>";
					}
				}
				message += "</table>";
				document.getElementById("Div").innerHTML = message;
			}
		};
		//3.使用open,用来设置请求方式及路径
		//4.send用于发送请求的,如果是post请求还可以传递参数
		xmlhttp.open("post",
				"${pageContext.request.contextPath}/infoServlet?requestInfo="
						+ str + "&userlink=${user.userlink}");
		xmlhttp.send(null);
	};
</script>
</head>
<body>
	用户名:${user.username}
	<br /> 关注了:${user.follower}人
	<br /> 被关注了:${user.followeder}人
	<br />
	<button onclick="operate('topics')">关注的话题</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="operate('answers')">回答</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="operate('questions')">提问</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="operate('activities')">动态</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<br />
	<div id="Div"></div>
	<div id="activityDiv"></div>
</body>
</html>