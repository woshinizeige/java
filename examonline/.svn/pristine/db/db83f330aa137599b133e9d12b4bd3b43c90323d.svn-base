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
	<table>
		<tr>
			<th>内容</th>
			<th>选项A</th>
			<th>选项B</th>
			<th>选项C</th>
			<th>选项D</th>
			<th>正确答案</th>
			<th>注解</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="a">
			<tr>
				<td>${a.title}</td>
				<td>${a.optiona}</td>
				<td>${a.optionb}</td>
				<td>${a.optionc}</td>
				<td>${a.optiond}</td>
				<td>${a.answer}</td>
				<td>${a.parse}</td>
				<td><a href="findid?testid=${a.testid}">修改</a><a href="deleteid?testid=${a.testid}">删除</a></td>
			</tr>
		</c:forEach> 
	</table>
	<c:choose>
	<c:when test="${pagesize>1}">
	<a href="editgo?pagesize=1">首页</a>
	</c:when>
	<c:otherwise>
	首页
	</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${pagesize>1}">
	<a href="editgo?pagesize=${pagesize-1}">上一页</a>
	</c:when>
	<c:otherwise>
	上一页
	</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${pagesize<num}">
	<a href="editgo?pagesize=${pagesize+1}">下一页</a>
	</c:when>
	<c:otherwise>
	下一页
	</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${pagesize<num }">
	<a href="editgo?pagesize=${num}">尾页</a>
	</c:when>
	<c:otherwise>
	尾页
	</c:otherwise>
	</c:choose>
	<a href="Manager/login.jsp">返回</a>
</body>
</html>
