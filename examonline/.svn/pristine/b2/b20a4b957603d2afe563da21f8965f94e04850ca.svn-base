<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看分数</title>
    
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
    <table	width="500px" align="center" border="1">
    	<tr align="center"><th colspan="2">考试成绩表</th></tr>
    	<tr align="center"><td width="50%">姓名</td><td>${stu.name}</td></tr>
    	<tr align="center"><td width="50%">成绩</td><td>${total}</td></tr>
    	<tr align="center"><td width="50%">考试时间</td><td>${testtime}</td></tr>
    	<tr align="center"><td width="50%"><a href="Student/showanswer.jsp">查看答案解析</a> <a href="test">再考一次</a></td>
    	<td><button><a href="Student/stumain.jsp">返回学生首页</a></button></td>
    	</tr>
    </table>
  </body>
</html>
