<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    <table border="1">
    <tr>
    <th>试题管理</th>
    </tr>
    <tr>
    <td><a href="Manager/add.jsp">录入试题</a></td>
    </tr>
     <tr>
     
    <td><a href="editgo">管理试题</a></td>
    </tr>
     <tr>
    <td><a href="Manager/find.jsp">查询试题</a></td>
    </tr>
     <tr>
    <td><a href="Manager/Student.jsp">学生管理</a></td>
    </tr>
    </table>
    <table>
    <tr>
    <th>欢迎界面</th>
    </tr>
    <tr>
    <td>欢迎使用在线考试系统</td>
    </tr>
    <tr>
    <td>各位老师好：<br>
    您现在使用的是XXX公司开发的考试系统，通过该系统，您可以在线登陆考试<br>
    题目，更新考试系统以及查询考试题目；同时还可以对学生信息，还有学生成<br>
    绩信息进行维护。
    </td>
    </tr>
    </table>
  </body>
</html>
