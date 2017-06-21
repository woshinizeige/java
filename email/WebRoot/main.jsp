<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件收发平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script src="js/jquery-3.0.0.min.js" type="text/javascript" ></script>
</head>
<body>
<c:if test="${empty name}">
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<%@include file="menu.jsp" %>            <%--页面顶端包含的信息 --%>
			<div><span style="font-size:24px; font-weight: bold;">查看邮件</span> <input class="searchinp"  placeholder="可通过发送人、标题、内容搜索"/> <button id="select1" value="${name}">搜索</button></div>
			<div class="content messageList" style="clear: both;">
				<div class="emaildo">全选 <input type="checkbox" id="all"> <button id="read">标记为已读</button>  <button id="delete1">删除勾选</button></div>
				<ul>
					<c:if test="${!empty rp.list}">
						<c:forEach items="${rp.list}" var="li">
							<li <c:if test="${li.state==0}">class="unReaded"</c:if> id="email"> <input type="checkbox" name="cb" value="${li.id}"/> 
								<a href="ReadMsg?id=${li.id}&type=r">
								<b>${li.title}</b>
								<p>
								<c:if test="${fn:length(li.content) > 8}">
									<c:out value="${fn:substring(li.content,0,7)}"/>....
								</c:if>
								<c:if test="${fn:length(li.content) <= 8}">
	 								<c:out value="${li.content}"/>
								</c:if> 
								</p> </a>
								<em><a href="userServlet?toname=${li.toname}&fromname=${li.fromname}">回信</a></em> <em ><a
									href="deleteServlet?id=${li.id}" id="delete">删除</a></em><span style="float:right">&nbsp;&nbsp;&nbsp;${li.createdate}</span></li>
						</c:forEach>
					</c:if>
				</ul>
				<br>
				<p style="text-align:right;"> 
						<b>[${rp.pageIndex}/${rp.pageIndexA}]</b>
						<c:choose>
								<c:when test="${rp.pageIndex>1}">
									<a href="selectBServlet?pageindex=1&name=${name}&str=${ty}">首页</a>
									<a href="selectBServlet?pageindex=${rp.pageIndex-1}&name=${name}&str=${ty}">上一页</a>
								</c:when>
								<c:otherwise>
									首页   上一页
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${rp.pageIndex<rp.pageIndexA}">
									<a href="selectBServlet?pageindex=${rp.pageIndex+1}&name=${name}&str=${ty}">下一页</a>
									<a href="selectBServlet?pageindex=${rp.pageIndexA}&name=${name}&str=${ty}">尾页</a>
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
$("#delete").click(function(){
	confirm("是否确认删除");
})
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
				$.post("/email/deleteAllServlet?id="+names[i].value+"&type=r",function(result){
				});
			}
		}
	}
	window.location.href="receiveList";
})
$("#read").click(function(){
	var con=confirm("是否全部标记为已读");
	if(con){
		var names=document.getElementsByName("cb");
		for(var i=0;i<names.length;i++){
			if(names[i].checked){
				$.post("/email/updateAllServlet?type=r&id="+names[i].value,function(result){
				});
			}
		}
	}
	window.location.href="receiveList";
})
$("#select1").click(function(){
	var str=$(this).prev().val();
	var name=$(this).val();
	window.location.href="selectBServlet?str="+str+"&name="+name;
})
</script>
