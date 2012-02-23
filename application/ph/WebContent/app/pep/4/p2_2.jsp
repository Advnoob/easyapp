<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.saturn.web.Web"%>	
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<%@ include file="/app/pep/include/header.jsp"%>
		<title><%=title %></title>
		<% 
		int fv9TotalNum	= 0;//	总数
		int fv9CKDCOPNum = 0;//CKD/COP
		
		if(form.get("fv9TotalNum")!= null && !"".equals(form.get("fv9TotalNum")) ){
			fv9TotalNum = Integer.parseInt((String)form.get("fv9TotalNum"));
		}
		if(form.get("fv9CKDCOPNum")!= null && !"".equals(form.get("fv9CKDCOPNum")) ){
			fv9CKDCOPNum = Integer.parseInt((String)form.get("fv9CKDCOPNum"));
		}
		
		List<String> teilestName = (List<String>)form.get("fv9TeilestName");
		int size = 0;
		if(teilestName != null ){
			teilestName.add("CKD/COP<br \\>Direkt-bezug");
			size = teilestName.size();
		}
		int CKD_low = fv9TotalNum - fv9CKDCOPNum;
		
		String fv9TeilestName = Web.getStrListStr(teilestName);
		String fv9TeileAusSerien = Web.getNumberListStr(form.get("fv9TeileAusSerien"));
		String fv9TeileFehlend = Web.getNumberListStr(form.get("fv9TeileFehlend"));
		String fv9TeileNote3 = Web.getNumberListStr(form.get("fv9TeileNote3"));
		String fv9TeileNote1 = Web.getNumberListStr(form.get("fv9TeileNote1"));
		String fv9TeileNote6 = Web.getNumberListStr(form.get("fv9TeileNote6"));
		
		List<String> fv9TopKrisUmf = (List<String>)form.get("fv9TopKrisUmf");
		List<String> fv9TopEinNum = (List<String>)form.get("fv9TopEinNum");
		List<String> fv9TopVSISWZ = (List<String>)form.get("fv9TopVSISWZ");
		List<String> fv9TopVSIN3 = (List<String>)form.get("fv9TopVSIN3");
		
	%>
		<style type="text/css">
			/* #subtitle {
				width: 400px; height: 100px; margin: 0 auto; float: left; 
			} */
			#subtitle h1{
				font-family:Arial, Helvetica, sans-serif;
				font-size:16px; margin:15px auto;
			}
			#left {
				width: 400px; height: 600px; margin: 0 auto; float: left;
			}
			#left chart{
				width: 400px; height: 400px; margin: 0 auto; float: left;
			}
			#left time{
				width: 400px; height: 200px; margin: 0 auto; float: left;
			}
			#time table tr td{
				text-align: center;
			}
			#right {
				width: 350px; margin: 20px auto; float: left; padding: 10px;
			}
			.div {
				width: 350px;
				border-top-width: 1px;
				border-top-style: solid;
				border-top-color: #000000;
				margin-bottom: 10px;
			}
			.div div {
				height: 42px;
				border-bottom-width: 1px;
				border-bottom-style: solid;
				border-bottom-color: #000000;
			}
			.div table td {
				border-bottom-width: 1px;
				border-bottom-style: solid;
				border-bottom-color: #000000;
				font-family: "宋体";
				font-size: 12px;
				line-height: 35px;
				color: #000000;
				text-align: center;
			}
		</style>
		<script type="text/javascript">
			var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'chart',
						defaultSeriesType: 'column'
					},
					title: {
						text: ' '
					},
					xAxis: {
						lineColor:'black',
						lineWidth:2,
						tickWidth:0,
						labels:{
							y:20,
							style:{
								color:'black'
							}
						},
						categories: <%=fv9TeilestName%>
					},
					yAxis: {
						min: 0,
						gridLineWidth:0,
						title: {
							text: ' '
						},
						labels:{
							enabled:false
						},
						stackLabels: {
							enabled: false,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							}
						}
					},
					legend: {
						layout: 'vertical',
						enabled: false,
						align: 'right',
						x: 0,
						verticalAlign: 'top',
						y: 0,
						shadow: false,
						borderWidth:0
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y +'<br/>'+
								 'Total: '+ this.point.stackTotal;
						}
					},
					plotOptions: {
						column: {
							stacking: 'normal',
							groupPadding:0.1,
							pointPadding:0.1,
							shadow: false,
							borderWidth:1,
							borderColor:'black',
							dataLabels: {
								enabled: true,
								style : {
									fontWeight: 'bold',
									fontSize:'12px'
								},
								color: 'black'
							}
						}
					},
					
				    series: [
						{
							name: 'PT-Teile/Alternativteile',
							data: <%=fv9TeileFehlend%>,
							color: '#FFFFFF'
						}, {
							name: 'Note 6',
							data: <%=fv9TeileNote6%>,
							color: '#FF0000'
						},{
							name: 'Teile aus Serienwerkzeug',
							data: <%=fv9TeileAusSerien%>,
							color: '#CCFFCC'
						},{
							name: 'Note 3',
							data: <%=fv9TeileNote3%>,
							color: '#00FF00',
							dataLabels: {
								enabled: true,
								style : {
									fontWeight: 'bold',
									fontSize:'12px'
								},
								color: 'white'
							}
						},{
							name: 'Note 1',
							data: <%=fv9TeileNote1%>,
							color: '#003300',
							dataLabels: {
								enabled: true,
								style : {
									fontWeight: 'bold',
									fontSize:'12px'
								},
								color: 'white'
							}
						},{
							name: 'CKDCOP',
							data: [
							<%
								for(int m=0; m<size-1; m++){
							%>
								{ 
									y: 0, 
									low: 0
								},
							<% 
								}
							%>
								{ 
									y: <%=fv9CKDCOPNum%>, 
									low: <%=CKD_low%>
								}
							],
							color: '#003300'
						}
				    ]
				});
			});
				
		</script>
	</head>
	<body>
		<div id="container">
			<div id="nr">
				<div id="top"><h1><%=title %></h1></div>
				<div id="content" style="margin:0 80px;height:600px;">
					<div id="subtitle">
						<h1>Anzahl Teile nach TEVON</h1>
					</div>
					<div id="left">
						<div id="chart"></div>
						<div id="time">
							<table style="font-size: 10px;">
								<tr>
									<td style="width: 12px; ">KW</td>
									<td style="width: 48px; ">52</td>
									<td style="width: 48px; ">6</td>
									<td style="width: 48px; ">8</td>
									<td style="width: 48px; ">15</td>
									<td style="width: 48px; ">35</td>
									<td style="width: 48px; ">43</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td colspan="6" style="border-bottom: 1px solid; height: 1px;">&nbsp;</td>
								</tr>
								<tr>
									<td style="width: 12px;">&nbsp;</td>
									<td style="width: 48px;">
										<img src="<%=request.getContextPath() %>/app/pep/images/VFF-TBT.png" width="48" height="35">
									</td>
									<td colspan="2">
										<img src="<%=request.getContextPath() %>/app/pep/images/VFF-T1.png" width="96" height="35">
									</td>
									<td>
										<img src="<%=request.getContextPath() %>/app/pep/images/PVS-T1.png" width="48" height="35">
									</td>
									<td>
										<img src="<%=request.getContextPath() %>/app/pep/images/0S-T1.png" width="48" height="35">
									</td>
									<td>
										<img src="<%=request.getContextPath() %>/app/pep/images/SOP-T1.png" width="48" height="35">
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div id="right">
						<div style="margin:3px 10px;font-weight: bold;">Top</div>
						<div class="div"><table width="350" cellspacing="0">
								<tr style="font-weight: bold;">
									<td>Kritische Umfänge</td>
									<td>Einzelteile</td>
									<td>VSI SWZ</td>
									<td>VSI N3</td>
								</tr>
								<% 
								if (fv9TopKrisUmf != null && fv9TopKrisUmf.size() > 0) {
									for(int i=0; i<fv9TopKrisUmf.size(); i++){
								%>
								<tr>
									<td style="text-indent: 15px;text-align: left;"><%=fv9TopKrisUmf.get(i)%></td>
									<td><%=fv9TopEinNum.get(i)%></td>
									<td><%=fv9TopVSIN3.get(i)%></td>
									<td><%=fv9TopVSISWZ.get(i)%></td>
								</tr>
								<%
									}
								}
									
								if (fv9TopKrisUmf == null || fv9TopKrisUmf.size() < 10) {
									for (int k=0; k<10-fv9TopKrisUmf.size(); k++) {
								%>
								<tr>
									<td style="text-indent: 15px;text-align: left;">&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%		
									}
								}
								%>
								</table>
						</div>
					</div>
				</div>
				
			</div>
			<%@ include file="/app/pep/include/foot.jsp"%>
		</div>	
	</body>
</html>
