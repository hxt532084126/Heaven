<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>完成以下步骤方可查询</h1>

	<a href= "${pageContext.request.contextPath}/operationServlet?status=1s"><font color = "red">启动</font></a><br />
	第一步 : 爬取数据导入数据库city<br />
	<font size="2">备注:将城市信息导入</font>
	<br /><br />
	
	<a href= "${pageContext.request.contextPath}/operationServlet?status=2s"><font color = "red">启动</font></a><br />
	第二步 :爬取数据导入数据库line<br />
	<font size="2">备注:将城市所有线路导入</font>
	<br /><br />
	
	<a href= "${pageContext.request.contextPath}/operationServlet?status=3s"><font color = "red">启动</font></a><br />
	第三步 :爬取数据导入数据库station<br />
	<font size="2">备注:将线路所有站点导入</font>
	<br /><br />
</body>
</html>