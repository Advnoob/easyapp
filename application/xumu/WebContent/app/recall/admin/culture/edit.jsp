<%@page import="com.saturn.app.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/app/includes/header.jsp"%>
<script type="text/javascript">
	function edit() {
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
		$('#editForm').submit(); */
		alert("保存成功！");
		window.location.href='<%=request.getContextPath() %>/app/recall/admin/culture/show.jsp';
	}
	
	$(function() {
		$('#license').focus();
	});
</script>
</head>
<body>
	<div id="panel" class="easyui-panel" title="修改"
		icon="icon-edit-form" collapsible="true" style="padding: 10px;">
		<form id="editForm"
			action="<%=request.getContextPath()%>/app/medication/updateMedication.action"
			method="post">
			<input id="id" name="id" type="hidden" value="${medication.id}"></input>
			<input type="hidden" id="createTime" name="createTime" value="<%=DateUtils.getSystemTime() %>" />
			<table class="table-form">
				<tr>
					<td style="text-align:right">畜禽养殖档案:</td>
					<td><input id="number" name="number" type="text" value="畜禽养殖档案"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">动物防疫合格证编号:</td>
					<td><input id="title" name="title" type="text" value="100002122"></input></td>
					<td><div id="titleTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">畜禽种类:</td>
					<td><input id="number" name="number" type="text" value="种猪"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td style="text-align:right">圈舍号:</td>
					<td><input id="number" name="number" type="text" value="111111"></input></td>
					<td><div id="numberTip"></div></td>
				</tr>
				<tr>
					<td colspan="3">
						<div style="padding: 10px;margin-left:80px;">
							<a href="#" onclick="edit()" class="easyui-linkbutton"
								iconCls="icon-add">保存</a> <a href="javascript:history.back(-1)"
								class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
						</div></td>
				</tr>
			</table>
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