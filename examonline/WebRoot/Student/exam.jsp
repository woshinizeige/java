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
<title>开始考试</title>
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
	var usemin=0;
	var usesec=0;
	function usetime(){
		usesec+=1;
		if(usesec==60){
			usemin+=1;
			usesec=0;
			if(usemin==50){
				alert("考试时间还剩余10分钟，请抓紧时间答题！")
			}
			if(usemin==60){
				alert("考试时间已到，即将自动交卷！")
			}
		}
		document.getElementById("showusetime").innerHTML="考试时间已用："+usemin+"分"+usesec+"秒，剩余"+(60-1-usemin)+"分"+(60-usesec)+"秒";
	}
	setInterval("usetime()",1000);
	function sub(){
		document.examform.submit();
	}
	setTimeout("sub()", 60*60*1000);
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
				<form action="submitpage" method="post" name="examform">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="10">
						<tr>
							<td><div align="center" class="STYLE3">考试时间：60 分钟</div></td>
							<td><div align="center" class="STYLE3">考生：${stu.name}</div></td>
							<td><div align="center" class="STYLE3">总分 ：100 分</div></td>
						</tr>
						<tr>
							<td align="center" colspan="3" class="STYLE3" id="showusetime"></td>	
						</tr>
						<tr>
							<td colspan="3" bgcolor="#999999" class="STYLE4">选择题(每小题5分，共20个)</td>
						</tr>
						<!--题目开始-->

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
								选择答案： 
								<input type="radio" name="answer${sta.index}" value="A" checked="checked"/> A 
								<input type="radio" name="answer${sta.index}" value="B" /> B
								<input type="radio" name="answer${sta.index}" value="C" /> C 
								<input type="radio" name="answer${sta.index}" value="D" /> D
								</td>
							</tr>
						</c:forEach>
						<!--题目结束-->
						<tr>
							<td colspan="3"><div align="center">
									<input type="submit" value=" 提交答卷 "/>
							</div></td>
						</tr>
					</table>
				</form>
			</td>
			<td width="113">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
