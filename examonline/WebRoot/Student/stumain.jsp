<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  </head>
  
  <body>
  	 <c:if test="${empty stu}">
		<jsp:forward page="../index.jsp"></jsp:forward>
	</c:if>
    <button><a href="test">参加考试</a></button><br>
    <button><a href="lookexam">查看考试记录</a></button><br>
    <button><a href="Student/updatepassword.jsp">修改密码</a></button><br>
    <button><a href="stuexit">退出登录</a></button><br>
  </body>
</html>
