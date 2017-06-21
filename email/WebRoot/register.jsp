<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
<input class="zc" type="hidden" value="${zc}"/>
<div id="regTitle" class="png"></div>
<div id="regForm" class="userForm png">
	<%--表单提交路径和方式 --%>
	<form action="submit?type=''" onsubmit = "" method="post">
		<dl id="con">
		    <div id="error" style="color:red"></div>          <!--注册错误信息提示 -->
			<dt>用 户 名：</dt>
			<dd><input type="text" name="username" value="${name}"/></dd>
			<dt>密　　码：</dt>
			<dd><input type="password" name="password" value="${password}"/></dd>
			<dt>确认密码：</dt>
			<dd><input type="password" name="affirm" value="${affirm}"/></dd>
			<dt>邮　　箱：</dt>
			<dd><input type="email" name="email" value="${email}"/></dd>
		</dl>
		<div class="buttons">
			<input class="btn-reg png" type="submit" name="register" value=" " /><input class="btn-reset png" type="reset" name="reset" value=" " />
		</div>
		<div class="goback"><a href="index.jsp" class="png">返回登录页</a></div>
	</form>
</div>
</body>
</html>
<script>
$("[name='username']").blur(function(){
	var username=$(this).val();
	$.post("/email/submit?type=nam&username="+username,function(result){
		$("#error").html(result);
	});
})
$("[name='password']").blur(function(){
	var password=$(this).val();
	$.post("/email/submit?type=pas&password="+password,function(result){
		$("#error").html(result);
	});
})
$("[name='affirm']").blur(function(){
	var affirm=$(this).val();
	var password=$("[name='password']").val();
	$.post("/email/submit?type=aff&affirm="+affirm+"&password="+password,function(result){
		$("#error").html(result);
	});
})
$("[name='email']").blur(function(){
	var email=$(this).val();
	$.post("/email/submit?type=ema&email="+email,function(result){
		$("#error").html(result);
	});
})
$(function(){
	var str=$(".zc").val();
	if(str=="cg"){
		alert("注册成功，跳回首页登录");
		window.location.href="index.jsp";
	}else if(str=="yhk"){
		alert("用户不能为空");
	}else if(str=="yhc"){
		alert("用户已存在");
	}else if(str=="mmk"){
		alert("密码不能为空");
	}else if(str=="mmd"){
		alert("密码小于6位");
	}else if(str=="mmb"){
		alert("两次密码不一致");
	}else if(str=="yxk"){
		alert("邮箱不能为空");
	}else if(str=="sb"){
		alert("注册失败");
	}
})

</script>

