<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'edit.jsp' starting page</title>

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
<form action="update" method="post">
内容：<textarea rows="3" cols="50" name="title">${Text.title}</textarea><br>
选项A:<input value="${Text.optiona}" name="optiona"><br>
选项B:<input value="${Text.optionb}" name="optionb"><br>
选项C:<input value="${Text.optionc}" name="optionc"><br>
选项D:<input value="${Text.optiond}" name="optiond"><br>

正确答案:<select name="answer">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
</select><br>
<input type="hidden" value="${Text.testid}" name="testid">
解析:<textarea rows="3" cols="50" name="parse">${Text.parse}</textarea><br>
<input type="submit" value="提交">
</form>
<a href="Manager/login.jsp">返回</a>
</body>
</html>
