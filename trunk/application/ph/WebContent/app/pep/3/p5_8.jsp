<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.saturn.web.Web"%>	
<!DOCTYPE HTML>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<%@ include file="/app/pep/include/header.jsp"%>
		<title><%=title %></title>
		
		<%
		Map form = (Map)request.getAttribute("form");
		
		List<String> KWNo = (List)form.get("fv9KWNo");
		List<String> KarosseriebauNum = (List)form.get("fv9KarosseriebauNum");
		List<String> KonzernNum = (List)form.get("fv9KonzernNum");
		List<String> PrognoseNum = (List)form.get("fv9PrognoseNum");
		
		String fv9KWNo = Web.getNumberListStr(KWNo);
		String fv9KarosseriebauNum = Web.getNumberListStr(KarosseriebauNum);
		String fv9KonzernNum = Web.getNumberListStr(KonzernNum);
		String fv9PrognoseNum = Web.getNumberListStr(PrognoseNum);
		
		List<String> fv9KW = (List)form.get("fv9KW");
		List<String> fv9Name = (List)form.get("fv9Name");
		List<String> fv9Score = (List)form.get("fv9Score");
		List<String> fv9Option = (List)form.get("fv9Option");
		
		List<String> newKarosseriebau = new ArrayList();
		for(int i=0; i<KarosseriebauNum.size(); i++){
			String table = "<table border=\"1\" style=\"background-color: blue;\">";
			for(int k=0; k<fv9KW.size(); k++) {
				if ((!"".equals(fv9KW.get(k))) && (fv9KW.get(k).equals(KWNo.get(i)))) {
					table += "<tr>";
					table += "	<td style=\"width: 180px;height: 20px;\">" + fv9Name.get(k) + "</td>";
					table += "	<td style=\"width: 50px; height: 20px;\">" + fv9Score.get(k) + " Pkt.</td>";
					if ("Yes".equals(fv9Option.get(k))) {
						table += "	<td style=\"width: 28px; height: 20px;\">√</td>";
					}
					if ("No".equals(fv9Option.get(k))) {
						table += "	<td style=\"width: 28px; height: 20px;\">×</td>";
					}
					table += "</tr><br>";
				}
			}
			table += "</table>";
			newKarosseriebau.add(mergeTableAndNum(KarosseriebauNum.get(i), table));
		}
		
		String karosseriebau = Web.getStrListStr(newKarosseriebau);
		karosseriebau = karosseriebau.replaceAll("\"", "");

		%>
		
		<script type="text/javascript">
		var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'chart'
					},
					title: {
						text: ' '
					},
					xAxis: {
						tickLength: 0,
						lineColor:'black',
						lineWidth:2,
						labels: {
							x:-2,
							y:20,
							style: {
								 font: 'normal 12px Verdana, sans-serif',
								 color: 'black'
							}
					},
						categories: <%=fv9KWNo%>
					},
					yAxis: {
						min: 0,
						max: 260,
						gridLineWidth: 0,
						lineWidth: 2,
						lineColor: 'black',
						labels: {
							style: {
								padding: '5px',
								color: 'black'
							}
						},
						title: {
							rotation:0,
							text: 'Punkte',
							x:-15,
							y:-160,
							style: {
								color: 'black',
								fontSize:'14px'
							}
						}
					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -10,
						y: 100,
						borderWidth: 1,
						borderRadius: 0,
						shadow: false
					},
					tooltip: {
						formatter: function() {
							return this.point.table;
						}
					},
					plotOptions: {
						column: {
							stacking: 'normal',
							groupPadding:0.35,
							shadow: false,
							borderColor:'black',
							borderWidth:1,
							dataLabels: {
								enabled: true,
								style : {
									fontWeight: 'bold',
									fontSize:'12px'
								},
								color: 'white',
								rotation: -90,
								x:5
							}
						}
					},
				    series: [{
						type: 'column',
						name: 'Karosseriebau',
						data: <%=karosseriebau%>,
						color: '#00235A'
					}, {
						type: 'spline',
						name: 'Prognose',
						data: <%=fv9PrognoseNum%>,
						color: '#99FF99'
					}, {
						type: 'spline',
						name: 'Konzernziel',
						data: <%=fv9KonzernNum%>,
						color: '#E63110'
					}]
				});
			});
			
			
		</script>
	</head>
	<body>
		<div id="container">
			<div id="nr">
			<div id="top"><h1><%=title %></h1></div>
			<div id="content">
				<div id="chart" style="width: 800px; height: 400px; margin: 0 auto"></div>
			</div>
			<%@ include file="/app/pep/include/foot.jsp"%>
		</div>	
	</body>
</html>