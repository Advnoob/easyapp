<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<style type="text/css">
* {
	margin:0;
	padding:0;
	list-style:none;
	color:#999;
}
input {
	width:200px;
	height:100px;
	font-size: 30px;
	border-width:0px;
	cursor: pointer;
}
</style>
</head>
<body>
<center>
<form id="" name="" action="" method="post">
	<%-- <div style="font-size:30px;">
		查询提示:请输入产品追随标签上全部21位产品追溯码，<br>点击"查询"获取详细的产销履历信息<br><br>
		产品追溯码：<input id="num" name="num" value="" type="text" style="border-width:1px;font-size:20px;line-height: 50px;height:35px;"/>
		&nbsp;&nbsp;<input type="button" onclick="javascript:location.href='<%=request.getContextPath() %>/app/recall/back/index.jsp'" value="查询" style="border-width:0px;font-size:20px;line-height: 30px;width:120px;height:45px;cursor: pointer;"/>
	</div> --%>
	<div style="font-size:50px;height: 150px;
	width: 800px;margin-right: auto;margin-top:200px;
	margin-left: auto;">
		产品追溯码：<input name="textfield" type="text" value="" style="border-width:2px;font-size:30px;line-height: 38px;width:200px;height:34px;cursor: pointer;"/>
		&nbsp;&nbsp;&nbsp;<input type="button" name="Submit" value="查询" onclick="javascript:location.href='<%=request.getContextPath() %>/app/recall/back/message/show.jsp'" style="border-width:0px;font-size:26px;line-height: 48px;width:120px;height:40px"/><br><br>
		<label style="font-size:25px"><span style="color:red;">提示</span>:请输入产品追随标签上全部21位产品追溯码，<br>点击"查询"获取详细的产销追溯信息</label>
	</div>
</form>
</body>
</html>