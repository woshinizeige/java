<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件收发平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
<div id="loginTitle" class="png"></div>
<div id="loginForm" class="userForm png">
	<form method="post" name="loginform" action="login?type=login" onsubmit = "">		
		<dl>			
			<div id="error">${error1}</div><!--错误登录信息提示 -->
			<dt>用户名：</dt>
			<dd><input type="text" name="username" /></dd>
			<dt>密　码：</dt>
			<dd><input type="password" name="password" /></dd>
		</dl>
		<div class="buttons">
			<input class="btn-login png" type="submit" name="submit" value=" " /> <!--登录 -->
			<input class="btn-reg png" type="button" name="register" value=" " /> <!--注册 -->
		</div>		
	</form>
</div>
</body>
</html>
<script>
	<%--注册页面跳转--%>
	$("[name='register']").click(function(){
		window.location.href="register.jsp";
	})
	
</script>
