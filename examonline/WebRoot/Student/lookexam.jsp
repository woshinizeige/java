<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考试记录</title>
    
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
  	<c:if test="${empty stu}">
		<jsp:forward page="../index.jsp"></jsp:forward>
	</c:if>
    <table border="1">
    	<tr><th>序号</th><th>成绩</th><th>考试时间</th></tr>
    	<c:forEach items="${examlist}" var="li" varStatus="sta">
    		<tr><td>${sta.index + 1}</td><td>${li.point}</td><td>${li.examtime}</td></tr>
    	</c:forEach>
    	<tr><button><a href="Student/stumain.jsp">返回学生首页</a></button></tr>
    </table>
  </body>
</html>
