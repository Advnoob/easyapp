<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.saturn.tc.utils.DatasetUtils"%>
<!DOCTYPE HTML>
<%@ include file="/app/pep/include/header.jsp"%>
<%
	String uid = (String)request.getAttribute("uid");
	String src = DatasetUtils.getDatasetByUid(uid, request);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title><%=title %> </title>
	</head>
	<body>
		<div id="container">
			<div id="nr">
			<div id="top">
				<div class="fl"><%=status_left %></div>
				<div class="fr"><%=status_right %></div>
				<h1><%=title %></h1>
			</div>
			<div id="content">
				<div class="box">
						<img src = "<%=src%>" />
				</div>
			</div>
			<%@ include file="/app/pep/include/foot.jsp"%>
		</div>	
		</div>
	</body>
</html>