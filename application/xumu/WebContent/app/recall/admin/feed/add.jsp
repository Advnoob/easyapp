<%@page import="com.saturn.app.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<%@ include file="/app/includes/header.jsp"%>
<script type="text/javascript">
	
	function add() {
		/* var number = $('#number').val();
		if (number == "") {
			alert("编号不能为空!");
			return;
		}
		var title = $('#title').val();
		if (title == "") {
			alert("标签不能为空!");
			return;
		} 
		$('#addForm').submit();*/
		alert("添加成功！");
		window.location.href='<%=request.getContextPath() %>/app/recall/admin/feed/show.jsp';
	}
	
	$(function() {
		$('#number').focus();
	});
</script>
</head>
<body>
	<div id="panel" class="easyui-panel" title="添加"
		icon="icon-add-form" collapsible="true" style="padding: 10px;">
		<form id="addForm"
			action="<%=request.getContextPath()%>/app/medication/addMedication.action"
			method="post">
			<input type="hidden" id="createTime" name="createTime" value="<%=DateUtils.getSystemTime() %>" />
			<table class="table-form">
				<tr>
					<td style="text-align:right">开始使用时间:</td>
					<td><input id="number" name="number" type="text" class="easyui-datebox"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">停止使用时间:</td>
					<td><input id="title" name="title" type="text" class="easyui-datebox"></input></td>
					<td><div id="titleTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">投入产品名称:</td>
					<td><input id="title" name="title" type="text"></input></td>
					<td><div id="titleTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">生产厂家:</td>
					<td><input id="number" name="number" type="text"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">批号/加工日期:</td>
					<td><input id="title" name="title" type="text"></input></td>
					<td><div id="titleTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">用量:</td>
					<td><input id="number" name="number" type="text"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				
				<tr>
					<td style="text-align:right">备注:</td>
					<td><input id="number" name="number" type="text"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td colspan="3">
						<div style="padding: 30px;margin-left:80px;">
							<a href="#" onclick="add()" class="easyui-linkbutton"
								iconCls="icon-add">添加</a> <a href="javascript:history.back(-1)"
								class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
						</div></td>
						<td></td>
				</tr>
			</table>
			<input id="datetime" type="hidden" name="datetime" type="text" value="<%=DateUtils.getSystemTime()%>"></input>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$.formValidator.initConfig({
			formid : "addForm"
		});
		$("#number").formValidator({
			onfocus : "请填写编号"
		}).inputValidator({
			min : 1,
			onerror : "不能为空字符"
		});
		
		$("#title").formValidator({
			onfocus : "请填写标签"
		}).inputValidator({
			min : 1,
			onerror : "不能为空字符"
		});
		
	});
</script>
</html>