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
.dataDiv tr td {
	vertical-align: middle;
}

.zongji {
	width: 100px;
	line-height: 18px;
	text-align: center;
}

.popname {
	width: 70px;
	line-height: 18px;
	text-align: center;
}
</style>
<script type="text/javascript">
function doSelect(){
	var value = document.getElementById("statisticdate").value;
	//var value = $('#statisticdate :input').val();
	if(value==''||value ==null){
		$('#sub').attr("disabled","disabled");
	}else{
		$('#sub').removeAttr("disabled");
	}
}

function findExcel(){
			$('#crmform')[0].action = "${pageContext.request.contextPath}/crm::/reportModule/action/ProfitdiffusionAreaExcel.action";
			$('#crmform')[0].submit();
		}	
</script>
<%
	class FlowStatic {
		public String type;
		
		//0：正常, 1:保险
		public int[][] dayNumStatic = new int[2][7]; //CIVIC, CR-V, SPIRIOR, 广本, 其他, 总计
		public float[][] dayMoneyStatic = new float[2][7]; //CIVIC, CR-V, SPIRIOR, 广本, 其他, 总计
		public int[][] monthNumStatic = new int[2][7]; //CIVIC, CR-V, SPIRIOR, 广本, 其他, 总计
		public float[][] monthMoneyStatic = new float[2][7]; //CIVIC, CR-V, SPIRIOR, 广本, 其他, 总计
		
		//车型集合
		List civic = new ArrayList();
		List crv = new ArrayList();
		List spirior = new ArrayList();
		List dfhonda = new ArrayList();
		List gzhonda = new ArrayList();
		
		
		public FlowStatic(String type) {
			this.type = type;
			
			civic.add("105006");
			crv.add("105007");
			spirior.add("105008");
			dfhonda.add("105014");
			dfhonda.add("105018");
			dfhonda.add("105020");
			dfhonda.add("106073");
			gzhonda.add("105002");
			gzhonda.add("105003");
			gzhonda.add("105004");
			gzhonda.add("105019");
		}
		
		public void add(Map vo, String currentDay) {
			String level = (String)vo.get("carCardLevel");
			String series = (String)vo.get("carSeries");
			String servInTime = (String)vo.get("servInTime");
			String servOutTime = (String)vo.get("servOutTime");
			String servType = (String)vo.get("servType");
			float smoney = ((Float)vo.get("servrepairexp")).floatValue();
			//float servRepairExp = ((Float)vo.get("servRepairExp")).floatValue();
			//float servInsureExp = ((Float)vo.get("servInsureExp")).floatValue();
			
			int type = 0;//0：正常, 1:保险
			if ("112003".equals(servType)) {
				type = 1;
				smoney = ((Float)vo.get("servinsureexp")).floatValue();
			}
			
			if (servOutTime.startsWith(currentDay)) {//日统计
				if (series != null && series.length() == 6) {
					if (civic.contains(series)) {	
						dayNumStatic[type][0]++;
						dayMoneyStatic[type][0] += smoney;
					} else if (crv.contains(series)) {
						dayNumStatic[type][1]++;
						dayMoneyStatic[type][1] += smoney;
					} else if (spirior.contains(series)) {
						dayNumStatic[type][2]++;
						dayMoneyStatic[type][2] += smoney;
					} else if (dfhonda.contains(series)) {
						dayNumStatic[type][3]++;
						dayMoneyStatic[type][3] += smoney;
					} else if (gzhonda.contains(series)) {
						dayNumStatic[type][4]++;
						dayMoneyStatic[type][4] += smoney;
					} else {
						dayNumStatic[type][5]++;
						dayMoneyStatic[type][5] += smoney;
					}
					//
					dayNumStatic[0][6]++;
					dayMoneyStatic[0][6] += smoney;
				}
			}
			//月统计
			if (series != null && series.length() == 6) {
				if (civic.contains(series)) {	
					monthNumStatic[type][0]++;
					monthMoneyStatic[type][0] += smoney;
				} else if (crv.contains(series)) {
					monthNumStatic[type][1]++;
					monthMoneyStatic[type][1] += smoney;
				} else if (spirior.contains(series)) {
					monthNumStatic[type][2]++;
					monthMoneyStatic[type][2] += smoney;
				} else if (dfhonda.contains(series)) {
					monthNumStatic[type][3]++;
					monthMoneyStatic[type][3] += smoney;
				} else if (gzhonda.contains(series)) {
					monthNumStatic[type][4]++;
					monthMoneyStatic[type][4] += smoney;
				} else {
					monthNumStatic[type][5]++;
					monthMoneyStatic[type][5] += smoney;
				}
				monthNumStatic[0][6]++;
				monthMoneyStatic[0][6] += smoney;
			}
			
		}
		
		public int getDaySum(int type) {
			int sum = 0;
			for (int i = 0; i < dayNumStatic.length; ++i) {
				sum += dayNumStatic[type][i];
			}
			
			return sum;
		}
		
		public int getMonthSum(int type) {
			int sum = 0;
			for (int i = 0; i < monthNumStatic.length; ++i) {
				sum += monthNumStatic[type][i];
			}
			
			return sum;
		}
		
		public String toTdStr() {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("<td>" + this.dayNumStatic[0][6]+ "</td>");
			buffer.append("<td>" + this.dayMoneyStatic[0][6]+ "</td>");
			buffer.append("<td>" + this.monthNumStatic[0][6]+ "</td>");
			buffer.append("<td>" + this.monthMoneyStatic[0][6]+ "</td>");
			
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 6; ++j) {
					buffer.append("<td>" + this.dayNumStatic[i][j]+ "</td>");
					buffer.append("<td>" + this.dayMoneyStatic[i][j]+ "</td>");
					buffer.append("<td>" + this.monthNumStatic[i][j]+ "</td>");
					buffer.append("<td>" + this.monthMoneyStatic[i][j]+ "</td>");
				}
			}
			
			return buffer.toString();
		}
 	}
	
	FlowStatic sum = new FlowStatic("总计");

	Map sourceMap = new HashMap();
	sourceMap.put("103001", new FlowStatic("103001"));//自店
	sourceMap.put("103002", new FlowStatic("103002"));//它店
	
	Map areaMap = new HashMap();
	areaMap.put("111001", new FlowStatic( "长春"));
	areaMap.put("111013", new FlowStatic( "松原"));
	areaMap.put("111009", new FlowStatic( "通化"));
	areaMap.put("111007", new FlowStatic( "四平"));
	areaMap.put("111011", new FlowStatic( "白城"));
	areaMap.put("111008", new FlowStatic( "辽源"));
	areaMap.put("111006", new FlowStatic( "吉林"));
	areaMap.put("111010", new FlowStatic( "白山"));
	areaMap.put("111012", new FlowStatic( "延吉"));
	areaMap.put("111014", new FlowStatic( "内蒙"));
	areaMap.put("111015", new FlowStatic( "辽宁"));
	areaMap.put("111016", new FlowStatic( "黑龙江"));
	areaMap.put("其他", new FlowStatic("其他"));
	
	List result = (List)request.getAttribute("result");
	List filter = new ArrayList();
	String statisticdate = (String)request.getAttribute("statisticdate");
	
	if (result != null) {
		String currentVin = null;
		String currentInTime = null;
		String currentTime = null;
		for (int i = 0; i < result.size(); ++i) {
			Map vo = (Map) result.get(i);
			if ("112003".equals((String)vo.get("servType"))) {
				continue;
			}
			String carVin = (String)vo.get("carVin");
			String servInTime = ((String)vo.get("servInTime")).substring(0, 10);//2010-11-11
			String servOutTime = ((String)vo.get("servOutTime")).substring(0, 10);//2010-11-11
			
			if (currentVin == null || !currentVin.equals(carVin) || (currentVin.equals(carVin)&&(!currentInTime.equals(servInTime)||!currentTime.equals(servOutTime)))) {
				Float voSmoney = (Float)vo.get("servrepairexp"); 
				Float voSmoney_tick = new Float(0);
				if(vo.get("servticketexp")!=null&&!"".equals(vo.get("servticketexp"))){
					voSmoney_tick = (Float)vo.get("servticketexp"); 
					vo.put("servrepairexp",new Float(voSmoney.floatValue() - voSmoney_tick.floatValue()));
				}
				filter.add(vo);
				currentInTime = servInTime;
				currentTime = servOutTime;
				currentVin = carVin;
			} else {
				Map last = (Map)filter.get(filter.size()-1);
				if (last != null && vo != null) {
					//Float smoney = (Float)last.get("servAmountExp"); 
					//Float voSmoney = (Float)vo.get("servAmountExp"); 
					//last.put("servAmountExp", new Float(smoney.floatValue() + voSmoney.floatValue()));
					Float smoney = (Float)last.get("servrepairexp"); 
					Float voSmoney = (Float)vo.get("servrepairexp"); 
					Float voSmoney_tick = new Float(0);
					if(vo.get("servticketexp")!=null&&!"".equals(vo.get("servticketexp"))){
						voSmoney_tick = (Float)vo.get("servticketexp"); 
					}
					
					last.put("servrepairexp", new Float(smoney.floatValue() + voSmoney.floatValue() - voSmoney_tick.floatValue()));
					
					//Float smoney1 = (Float)last.get("servinsureexp"); 
					//Float voSmoney1 = (Float)vo.get("servinsureexp"); 
					//last.put("servinsureexp", new Float(smoney1.floatValue() + voSmoney1.floatValue()));
					
				}
			}
		}
		for (int i = 0; i < result.size(); ++i) {
			Map vo = (Map) result.get(i);
			if (!"112003".equals((String)vo.get("servType"))) {
				continue;
			}
			String carVin = (String)vo.get("carVin");
			String servInTime = ((String)vo.get("servInTime")).substring(0, 10);//2010-11-11
			String servOutTime = ((String)vo.get("servOutTime")).substring(0, 10);//2010-11-11
			
			if (currentVin == null || !currentVin.equals(carVin) || (currentVin.equals(carVin)&&(!currentInTime.equals(servInTime)||!currentTime.equals(servOutTime)))) {
				Float voSmoney = (Float)vo.get("servinsureexp"); 
				Float voSmoney_tick = new Float(0);
				if(vo.get("servticketexp")!=null&&!"".equals(vo.get("servticketexp"))){
					voSmoney_tick = (Float)vo.get("servticketexp"); 
					vo.put("servinsureexp",new Float(voSmoney.floatValue() - voSmoney_tick.floatValue()));
				}
				filter.add(vo);
				currentInTime = servInTime;
				currentTime = servOutTime;
				currentVin = carVin;
			} else {
				Map last = (Map)filter.get(filter.size()-1);
				if (last != null && vo != null) {
					//Float smoney = (Float)last.get("servAmountExp"); 
					//Float voSmoney = (Float)vo.get("servAmountExp"); 
					//last.put("servAmountExp", new Float(smoney.floatValue() + voSmoney.floatValue()));
					//Float smoney = (Float)last.get("servrepairexp"); 
					//Float voSmoney = (Float)vo.get("servrepairexp"); 
					//last.put("servrepairexp", new Float(smoney.floatValue() + voSmoney.floatValue()));
					
					Float smoney1 = (Float)last.get("servinsureexp"); 
					Float voSmoney1 = (Float)vo.get("servinsureexp"); 
					
					Float voSmoney_tick = new Float(0);
					if(vo.get("servticketexp")!=null&&!"".equals(vo.get("servticketexp"))){
						voSmoney_tick = (Float)vo.get("servticketexp"); 
					}
					last.put("servinsureexp", new Float(smoney1.floatValue() + voSmoney1.floatValue() - voSmoney_tick.floatValue()));
					
				}
			}
		}
		for (int i = 0; i < filter.size(); ++i) {
			Map vo = (Map) filter.get(i);
			
			sum.add(vo, statisticdate);///------
			
			String source = (String)vo.get("carSource");
			String area = (String)vo.get("carArea");
			
			FlowStatic sourceStatic = (FlowStatic)sourceMap.get(source);
			if (sourceStatic != null) {
				sourceStatic.add(vo, statisticdate);
			}
			
			FlowStatic areaStatic = (FlowStatic)areaMap.get(area);
			if (areaStatic != null) {
				areaStatic.add(vo, statisticdate);
			} else {
				((FlowStatic)areaMap.get("其他")).add(vo, statisticdate);
			}
			
		}
	}
	
	List areaList = new ArrayList();
	areaList.add(areaMap.get("111001"));
	areaList.add(areaMap.get("111013"));
	areaList.add(areaMap.get("111009"));
	areaList.add(areaMap.get("111007"));
	areaList.add(areaMap.get("111011"));
	areaList.add(areaMap.get("111008"));
	areaList.add(areaMap.get("111006"));
	areaList.add(areaMap.get("111010"));
	areaList.add(areaMap.get("111012"));
	areaList.add(areaMap.get("111014"));
	areaList.add(areaMap.get("111015"));
	areaList.add(areaMap.get("111016"));
	areaList.add(areaMap.get("其他"));
%>
<title>售后效益统计--地区分布</title>
</head>
<body>
<div id="man_zone">
<form id="crmform" class="queryFrom"
	action="${pageContext.request.contextPath}/crm::/reportModule/action/ProfitdiffusionArea.action">
<fieldset><legend>售后效益统计--地区分布</legend>

<table cellspacing="0" cellpadding="1">
	<tr>
		<td class="colName">统计日期:</td>
		<td class="detailColVal" colspan="10"><input type="text"
			value="${statisticdate}" name="statisticdate" id="statisticdate"
			onchange="javascript:doSelect(this);" onfocus="dayCalender(this)"
			style="width: 120px;" /></td>
	</tr>
	<tr>
		<td colspan="8" style="text-align: center;"><input id="sub"
			type="submit" value="查询" disabled="disabled" /></td>
	</tr>
</table>
</fieldset>
</form>
<c:if test="${empty result}">
	<table>
		<tr>
			<td colspan="30">
			<div class="clb-nodata">没有符合条件的数据！</div>
			</td>
		</tr>
	</table>
</c:if> 
<%
	if (result != null) {
%>
<table class="optDiv" style=''>
	<tr>
		<td><label class="rsTitle" style=''>${statisticdate}售后台前业务分析</label></td>
		<td>
		<div id="paginate"><a href="javascript:findExcel();">导出</a></div>
		</td>
	</tr>
</table>

<div class="tbDiv">
<table style='width: 1500px;' cellspacing='0' cellpadding='0'>
	<tbody>
		<tr>
			<td rowspan="4" colspan='2' style="width: 80px;"></td>
			<td rowspan='2' colspan='4' style="width: 160px;">小计</td>
			<td colspan='24'
				style="line-height: 18px; text-align: center; width: 800px;">正常维修保养</td>
			<td colspan='24'
				style="line-height: 18px; text-align: center; width: 800px;">保险车辆</td>
			<!--  <td colspan='4'
				style="line-height: 18px; text-align: center; width: 160px;">其他</td>-->
		</tr>
		<tr>
			<td colspan='4' class="">CIVIC</td>
			<td colspan='4' class="">CRV</td>
			<td colspan='4' class="">SPIRIOR</td>
			<td colspan='4' class="">东本其他</td>
			<td colspan='4' class="">广本</td>
			<td colspan='4' class="">其他</td>
			<td colspan='4' class="">CIVIC</td>
			<td colspan='4' class="">CRV</td>
			<td colspan='4' class="">SPIRIOR</td>
			<td colspan='4' class="">东本其他</td>
			<td colspan='4' class="">广本</td>
			<td colspan='4' class="">其他</td>
			<td colspan='4' class="">其他</td>
		</tr>
		<tr>
			<%
			for (int i = 0; i < 14; i++) {
			%>
			<td colspan='2' class="">当日</td>
			<td colspan='2' class="">月累计</td>
			<%
			}
			%>
		</tr>
		<tr>
			<%
			for (int i = 0; i < 28; i++) {
			%>
			<td style="width: 30px; height: 18px;">台次</td>
			<td style="width: 50px; height: 18px;">金额</td>
			<%
			}
			%>
		</tr>
		<tr>
			<td rowspan="14">地区分布</td>
				<td>长春</td>
				<%
					FlowStatic changchun = (FlowStatic)areaList.get(0);
					out.println(changchun.toTdStr());
				%>
			</tr>
		<%
			for (int i = 1; i < areaList.size(); ++i) {
				out.println("<tr>");
				FlowStatic sub = (FlowStatic)areaList.get(i);
				out.println("<td>" + sub.type + "</td>");
				out.println(sub.toTdStr());
				out.println("</tr>");
			}
		
			out.println("<td>" + sum.type + "</td>");
			out.println(sum.toTdStr());
		%>
	</tbody>
</table>
<table
	style="border-bottom-style: none; height: 10px; width: 1500px; margin-top: 10px;">
	&nbsp;
</table>
<%
}
%>
</div>
</div>
</body>
</html>