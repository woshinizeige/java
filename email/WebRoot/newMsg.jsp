<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>发邮件</title>	    
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>

 <body>
<input class="str" type="hidden" value="${str}"/>
 <c:if test="${empty name}">
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
 <form action="newMsgServlet?fromname=${name}&type=''" method="post" onsubmit="">
	<div id="main">
		<div class="mainbox">			
			<%@include file="menu.jsp" %>
			<h1>发送邮件</h1>
			<div id="error2" style="color:red"></div>
			<div class="content">
				<div class="message">
						<div class="tmenu">
							<ul class="clearfix">
								<li>
									发送给：
									<select name="toUser">
						  	 					<option value="${toname}">${toname}</option>
						  	 					<c:forEach items="${list}" var="li">
						  	 						<option value="${li.username}">${li.username}</option>
						  	 					</c:forEach>	
						  	 		</select>
								</li>								
								<li>标题：<input type="text" name="title" id="title"/></li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content" id="content"></textarea>
							<div class="send"><input type="submit" name="submit" value=" " /></div>
						</div>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
<script>
$("[name='toUser']").blur(function(){
	var touser=$(this).val();
	$.post("/email/newMsgServlet?type=nam&toUser="+touser,function(result){
		$("#error2").html(result);
	});
})
$("[name='title']").blur(function(){
	var title=$(this).val();
	$.post("/email/newMsgServlet?type=tit&title="+title,function(result){
		$("#error2").html(result);
	});
})
$("[name='content']").blur(function(){
	var content=$(this).val();
	$.post("/email/newMsgServlet?type=con&content="+content,function(result){
		$("#error2").html(result);
	});
})
$(function(){
	var str=$(".str").val();
	if(str=="cg"){
		alert("发送成功");
	}else if(str=="sb"){
		alert("发送失败");
	}
})
</script>