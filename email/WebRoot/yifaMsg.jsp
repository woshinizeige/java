<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件收发平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
<c:if test="${empty name}">
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<%@include file="menu.jsp" %>
			<div id="error">${error}</div>
			<h1>已发邮件</h1>
			<div class="content messageList">
				<div class="emaildo">全选 <input type="checkbox" id="all">  <button id="delete1">删除勾选</button></div>
				<ul>
					
					<c:if test="${!empty sp.list}">
						<c:forEach items="${sp.list}" var="li">
							<li <c:if test="${li.state==0}">class="unReaded"</c:if> > <input type="checkbox" name="cb" value="${li.id}"/> 
								<a href="ReadMsg?id=${li.id}&type=s&state=1">
								<b>${li.title}</b>
								<p>
								<c:if test="${fn:length(li.content) > 8}">
									<c:out value="${fn:substring(li.content,0,7)}"/>....
								</c:if>
								<c:if test="${fn:length(li.content) <= 8}">
	 								<c:out value="${li.content}"/>
								</c:if> 
								</p>
								<em>${li.createdate}</em>
								</a></li>
						</c:forEach>
					</c:if>
				</ul>
				<p style="text-align:right;">
				<b>[${sp.pageIndex}/${sp.pageIndexA}]</b>
						<c:choose>
								<c:when test="${sp.pageIndex>1}">
									<a href="sendList?pageindex=1&name=${name}">首页</a>
									<a href="sendList?pageindex=${sp.pageIndex-1}&name=${name}">上一页</a>
								</c:when>
								<c:otherwise>
									首页   上一页
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${sp.pageIndex<sp.pageIndexA}">
									<a href="sendList?pageindex=${sp.pageIndex+1}&name=${name}">下一页</a>
									<a href="sendList?pageindex=${sp.pageIndexA}&name=${name}">尾页</a>
								</c:when>
								<c:otherwise>
									下一页  尾页
								</c:otherwise>
							</c:choose>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
<script>
$("#all").click(function(){
	var names=document.getElementsByName("cb");
	var al=document.getElementById("all");
	for(var i=0;i<names.length;i++){
		names[i].checked=al.checked;
	}
})
$("#delete1").click(function(){
	var con=confirm("是否确认删除");
	if(con){
		var names=document.getElementsByName("cb");
		for(var i=0;i<names.length;i++){
			if(names[i].checked){
				$.post("/email/deleteAllServlet?type=s&id="+names[i].value,function(result){
				});
			}
		}
	}
	window.location.href="sendList";
})
</script>
