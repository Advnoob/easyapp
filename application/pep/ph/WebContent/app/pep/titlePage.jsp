<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/app/pep/include/header.jsp"%>
<%
	String ppttitle = "VG311 Pilothallengespraeche";
	String subtitle = "Entwurf Unterlage fuer Meilenstein PVS <br>Status 05.04.2012";
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Pilothallengespraeche</title>
</head>

<body>
	<div id="container">
		<div id="nr">
			<div style="float: left; width: 990px; height: 670px; margin: 10px 10px auto; ">
				<div style="width: 970px; height: 450px; float: left; margin: 10px 10px auto;">
					<img src="<%=request.getContextPath() %>/app/pep/images/index.jpg" width="970" height="450"/>
				</div>
				<div style="float: left; width: 900px; height: 150px; margin: 10px 10px auto; font-size: 28px; 
					font-family: Arial; margin-top: 35px;">
					<b><%=Web.replaceSpecial(ppttitle) %></b>
					<br><br>
					<%=Web.replaceSpecial(subtitle) %>
				</div>
			</div>
			<div style="float: left; width: 1000px; height: auto; border-bottom: #000099 2px solid;"></div>
			<%@ include file="/app/pep/include/foot.jsp"%>
		</div>	
	</div>
</body>
</html>
