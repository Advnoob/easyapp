<%@page import="com.teamcenter.soa.client.model.strong.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<%@ include file="/app/includes/header.jsp"%>
</head>
<body class="easyui-layout">
	<%
		User user = (User) session.getAttribute("TC_USER");
	%>
	<div region="north" split="false"
		style="height: 100px; overflow: hidden;">
		<div class="top_img">
	    <div class="user-info">用户名:<%=user.get_user_name()%> [<a href="<%=request.getContextPath()%>/app/tc/logout.do">退出</a>] </div>
		<div class="menu-info"></div>	
		</div> 
	</div>
	<div region="center" style="overflow: hidden;">
		<iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/app/tc/detail.jsp" style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>