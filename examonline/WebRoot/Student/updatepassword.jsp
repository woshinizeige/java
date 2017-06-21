<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="jquery-3.0.0.min.js"></script>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  </head>
  
  <body>
     <c:if test="${empty stu}">
		<jsp:forward page="../index.jsp"></jsp:forward>
	</c:if>
    <button><a href="Student/stumain.jsp">返回学生首页</a></button>
    <form action="updatepsw" method="post" name="updateform">
    <input name="oldpassword" placeholder="请输入原密码"><br>
    <input name="newpassword1" placeholder="请输入新密码"><br>
    <input name="newpassword2" placeholder="请再次输入新密码"><br>
    <input type="hidden" value="${stu.password}" name="password">
    <input type="hidden" value="${error}" name="error">
    <input type="hidden" value="${statu0}" name="statu0">
    <input type="submit" value="修改密码">
    </form>
  </body>
</html>
<script>
	var statu0=$("[name='statu0']").val();
	if(statu0==1){
		var error=$("[name='error']").val();
		alert(error);
	}
	
	$("[name='oldpassword']").blur(function(){
		var oldp=$(this).val();
		var oldp1=$("[name='password']").val();
		if(oldp==""){
			alert("原密码不能为空！");
		}else if(oldp!=oldp1){
			alert("原密码输入有误！");
		}
	})
	$("[name='newpassword1']").blur(function(){
		var newp1=$(this).val();
		if(""==newp1){
			alert("新密码不能为空！");
		}
	})
	$("[name='newpassword2']").blur(function(){
		var newp1=$("[name='newpassword1']").val();
		var newp2=$(this).val();
		if(""==newp2){
			alert("新密码不能为空！");
		}else if(newp1!=newp2){
			alert("新密码两次输入不一致！");
		}
	})
	$("[value='修改密码']").click(function(){
		var b=confirm("是否确认修改密码");
		if(b){
			updateform.submit();
		}
	})
	


</script>