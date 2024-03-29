<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="edu.ccut.saturn.component.SaturnData"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ include file="/crm/coreModule/web/common.jsp"%>
<%@ include file="/crm/coreModule/web/saturnCalendar.jsp"%>

<style>
.dataDiv tr td{vertical-align: middle;}
.zongji{width:100px; line-height: 18px; text-align: center;}
.popname{width:70px; line-height: 18px; text-align: center;}
</style>

<title>售后前台--招揽预约</title>
</head>
<script type="text/javascript">
		function findExcel(){
			$('#exportForm')[0].action = "<%=request.getContextPath()%>/crm/reportModule/web/report2Excel.jsp";
			$('#exportForm')[0].submit();
		}
		
		
</script> 
<body>
<div id="man_zone">
	<form id="crmform" class="queryFrom" action="${pageContext.request.contextPath}/crm::/reportModule/action/Report1.action">
		<fieldset>
		  <legend>售后前台--招揽预约</legend>
		
              <table cellspacing="0" cellpadding="0">
                   <tr>                    
				  	<td class="colName">统计日期:</td>
                     <td class="detailColVal" colspan="10">
                     	<input type="hidden" name="typez" id="typez" value="yy">
                     	<input type="text" value="${datetime}" name="datetime" id="datetime" onfocus="dayCalender(this)" style="width:120px;"/>
                     </td>
                   </tr>
                   <tr>
                      <td colspan="8" style="text-align:center;">
						<input id="sub" type="submit" value="查询" />
                      </td>
                   </tr>
             </table> 
		</fieldset>           
	</form>
	<c:if test="${report1==null}">
		<table>
			<tr><td colspan="30">
				<div class="clb-nodata">
					没有符合条件的数据！
				</div>	
				</td>
			</tr>
		</table>	
	</c:if>
	<%
	Object obj = (Object)request.getAttribute("report1");
	if (obj != null){
		String[][] report1 = (String[][])obj;
		session.removeAttribute("report2");
		session.setAttribute("report2",report1);
	%>
    <table class="optDiv"  style=''>
		<tr>
			<td><label class="rsTitle" style=''>${datetime}售后台前业务分析</label></td>
			<td><div id="paginate"><a href="javascript:findExcel()">导出</a></div></td>
		</tr>
	</table>

    <div class="tbDiv">
		<table style='width:1500px;' cellspacing='0' cellpadding='0'>
		<tbody>
			<tr>
				<td colspan="3"></td>
				<td>总计</td>
				<%
				for(int i=1;i<report1[1].length;i++){
					%>
					<td><%=report1[1][i] %></td>
					<%
				}
				%>	
           	</tr>
           	<tr>
           		<td rowspan="4">招揽预约</td>
				<td rowspan="2">预约</td>
				<td>当日</td>
				<%
				for(int i=0;i<report1[2].length;i++){
					%>
					<td><%=report1[2][i] %></td>
					<%
				}
				%>
           	</tr>
           	<tr>
           		<td>月累</td>
           		<%
				for(int i=0;i<report1[3].length;i++){
					%>
					<td><%=report1[3][i] %></td>
					<%
				}
				%>
           	</tr>
           	<tr>
				<td rowspan="2">成功预约</td>
				<td>当日</td>
				<%
				for(int i=0;i<report1[4].length;i++){
					%>
					<td><%=report1[4][i] %></td>
					<%
				}
				%>
           	</tr>
           	<tr>
           		<td>月累</td>
           		<%
				for(int i=0;i<report1[5].length;i++){
					%>
					<td><%=report1[5][i] %></td>
					<%
				}
				%>
           	</tr>
		</tbody>
		</table>
		<table style="border-bottom-style: none;height: 10px;width: 1500px; margin-top: 10px;">&nbsp;</table>
    </div>
 	<%
		}
	%>
</div>
<div style="display:none;">
	<form id="exportForm" name="exportForm" method="post">
		<input type="hidden" name="datetime" id="datetime" value="${datetime}">
		
	</form>
</div>

</body>
</html>