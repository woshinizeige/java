<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'find.jsp' starting page</title>
    
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
  
  	<form action="findtitle" method="post">
  	
   	根据内容查询<input type="text" name="title"><br>
   	
   	<input type="submit" value="提交">
   	
   	</form>
   	
   	<form action="findanswer" method="post">
  	
   	根据正确答案查询<select name="answer">
   		<option value="A">A</option>
   		<option value="B">B</option>
   		<option value="C">C</option>
   		<option value="D">D</option>
   			</select><br>
   	
   	<input type="submit" value="提交">
   	
   	</form>
   	<a href="Manager/login.jsp">返回</a>
  </body>
</html>
