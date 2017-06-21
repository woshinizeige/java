<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="jquery-3.0.0.min.js"></script>
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form action="login" method="post">
		教师（学生）登陆<br>
		<div>
			<font color="red">${wrong}</font>
		</div>
		账号：<input type="text" name="name" id="a"><br> 密码：<input
			type="password" name="password"><br> 登陆角色:<input
			type="radio" value="1" name="state">管理员 <input type="radio"
			value="0" name="state">学生<br> <input type="submit"
			value="登陆">
	</form>
</body>
</html>