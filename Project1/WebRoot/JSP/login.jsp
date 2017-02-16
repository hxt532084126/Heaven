<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/loginServlet"
		method="post">
		该用户主页链接:<input type="text" name="link"
			value="https://www.zhihu.com/people/wan-qian-75-21/answers"
			size="50%" /><br /> <input type="submit" value="查询" />
	</form>
</body>
</html>