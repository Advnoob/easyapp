<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.saturn.warning.date.farmer.FarmerPig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卖出</title>
<%@ include file="/app/includes/header.jsp"%>
<style type="text/css">
* {
	margin:0;
	padding:0;
	list-style:none;
	color:#000;
}
input {
	width:180px;
	height:80px;
	font-size: 40px;
	line-height: 80px;
	/* direction:rtl; */
	border-width:0px;
	/* cursor: pointer; */
	text-align:center;
}
</style>
<script type="text/javascript">
	function add() {
		$('#addForm').submit();
	}
</script>
</head>
<%
	Map pigs = (Map)session.getAttribute(FarmerPig.SESSION_SALE);
	
	if (pigs == null) {
		pigs = new HashMap();
		pigs.put("仔猪", new FarmerPig(null, null, "卖出", "仔猪", "", "", "", null, null));
		pigs.put("育肥猪", new FarmerPig(null, null, "卖出", "育肥猪", "", "", "", null, null));
		pigs.put("能繁母猪", new FarmerPig(null, null, "卖出", "能繁母猪", "", "", "", null, null));
		pigs.put("后备母猪", new FarmerPig(null, null, "卖出", "后备母猪", "", "", "", null, null));
		session.setAttribute(FarmerPig.SESSION_SALE, pigs);
	}
	
	FarmerPig p1 = (FarmerPig)pigs.get("仔猪");
	FarmerPig p2 = (FarmerPig)pigs.get("育肥猪");
	FarmerPig p3 = (FarmerPig)pigs.get("能繁母猪");
	FarmerPig p4 = (FarmerPig)pigs.get("后备母猪");
%>
<body>
<center>
<div id="main">
<form id="addForm" name="addForm"
			action="<%=request.getContextPath()%>/app/warning/farmer/sale.do"
			method="post">
<table cellpadding="0px" cellspacing="1px" bgcolor="#000000" width="800px" style="font-weight:bold">
<tr bgcolor="#ffffff"><td style="font-size:35px;line-height:80px;" colspan=7>生猪卖出</td></tr>
<tr bgcolor="#ffffff" height="100px">
<td style="font-size:25px;">卖出类别</td>
<td colspan="2" style="font-size:25px;">数量（头）</td>
<td colspan="2" style="font-size:25px;">单价（元/斤）</td>
<td colspan="2" style="font-size:25px;">本次卖出共收入（元）</td></tr>
<tr bgcolor="#ffffff" height="80px">
<td style="font-size:25px;">仔猪</td>
<td width="150px;"><input id="num1" name="num1" type="text" value="<%=p1.getNum()%>"/></td><td width="50px;" style="font-size:30px;">头</td>
<td width="120px;"><input id="price1" name="price1" type="text" style="width:120px;" value="<%=p1.getPrice()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
<td width="200px;"><input id="total1" name="total1" type="text" style="width:200px;" value="<%=p1.getTotal()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
</tr>
<tr bgcolor="#ffffff">
<td style="font-size:25px;">育肥猪</td>
<td width="150px;"><input id="num2" name="num2" type="text" value="<%=p2.getNum()%>"/></td><td width="50px;" style="font-size:30px;">头</td>
<td width="120px;"><input id="price2" name="price2" type="text" style="width:120px;" value="<%=p2.getPrice()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
<td width="200px;"><input id="total2" name="total2" type="text" style="width:200px;" value="<%=p2.getTotal()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
</tr>
<tr bgcolor="#ffffff">
<td style="font-size:25px;">能繁母猪</td>
<td width="150px;"><input id="num3" name="num3" type="text" value="<%=p3.getNum()%>"/></td><td width="50px;" style="font-size:30px;">头</td>
<td width="120px;"><input id="price3" name="price3" type="text" style="width:120px;" value="<%=p3.getPrice()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
<td width="200px;"><input id="total3" name="total3" type="text" style="width:200px;" value="<%=p3.getTotal()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
</tr>
<tr bgcolor="#ffffff">
<td style="font-size:25px;">后备母猪</td>
<td width="150px;"><input id="num4" name="num4" type="text" value="<%=p4.getNum()%>"/></td><td width="50px;" style="font-size:30px;">头</td>
<td width="120px;"><input id="price4" name="price4" type="text" style="width:120px;" value="<%=p4.getPrice()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
<td width="200px;"><input id="total4" name="total4" type="text" style="width:200px;" value="<%=p4.getTotal()%>"/></td><td width="50px;" style="font-size:30px;">元</td>
</tr>
</table>
<table width="800px">
<tr>
<td align="left" width="50%">
<input type="button" value="保存" onclick="add()" src="" alt="保存" style="font-size:20px;line-height: 50px;width:150px;height:50px;cursor: pointer;" />
</td>
<td align="right" width="50%">
<input type="button" value="返回" onclick="javascript:location.href='<%=request.getContextPath() %>/app/warning/farmer/main.jsp'" src="" alt="返回" style="font-size:20px;line-height: 50px;width:150px;height:50px;cursor: pointer;" />
</td>
</tr>
</table>
</form>
</div>
</center>
</body>
</html>