<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="menu">
				<span>当前用户：<a href="receiveList">${name}</a></span> <span><a href="userServlet?toname=${mtoname}&fromname=">发送邮件</a></span>
				<span><a href="sendList?name=${name}">已发邮件</a></span> <span><a href="login?type=quit">退出</a></span>
			</div>