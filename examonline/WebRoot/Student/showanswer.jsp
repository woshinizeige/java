<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看解析</title>
<link href="student/images/css2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE3 {
	font-size: 18px;
}
.STYLE4 {
	font-size: 18px;
	font-weight: bold;
}
.STYLE5 {
	color: #FF0000
}
-->
</style>
<script type="text/javascript">
	
</script>
</head>

<body>
	<c:if test="${empty stu}">
		<jsp:forward page="../index.jsp"></jsp:forward>
	</c:if>
	<table width="1003" height="485" border="0" cellpadding="0"
		cellspacing="0" class="centerbg">
		<tr>
			<td width="149" height="485">&nbsp;</td>
			<td width="741" valign="top" class="rightbian">
				<form action="submitpage" method="post">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="10">
						<tr>
							<td><div align="center" class="STYLE3">考生：${stu.name}</div></td>
							<td><div align="center" class="STYLE3">总分 ：100 分</div></td>
							<td><div align="center" class="STYLE3">得分：${total}</div></td>
						</tr>
						<tr>
							<td colspan="3" bgcolor="#999999" class="STYLE4">选择题(每小题5分，共20个)</td>
						</tr>

						<c:forEach items="${testlist}" var="li" varStatus="sta">
							<input type="hidden" name="testid" value="${li.testid}">
							<tr>
								<td colspan="3">第<span class="STYLE5">${sta.index + 1}</span>题&nbsp;${li.title}
								</td>
							</tr>
							<tr>
								<td colspan="3"><strong>A．</strong>${li.optiona}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>B．</strong>${li.optionb}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>C．</strong>${li.optionc}</td>
							</tr>
							<tr>
								<td colspan="3"><strong>D．</strong>${li.optiond}</td>
							</tr>
							<tr>
								<td height="32" colspan="3" bgcolor="#CCCCCC">
								参考答案：${li.answer} 
								</td>
							</tr>
							<tr>
								<td height="32" colspan="3" bgcolor="#CCCCCC">
								答案解析：${li.parse} 
								</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</td>
			<td width="113">&nbsp;</td>
		</tr>
	</table>
	<table width="1003" border="0" cellpadding="0"
		cellspacing="0" class="centerbg">
		<tr><button><a href="stumain.jsp">返回学生首页</a></button></tr>
		</table>
</body>
</html>

