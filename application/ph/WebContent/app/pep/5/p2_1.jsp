<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>	
<%@page import="json.JSONArray"%>
<%

	Map form = (Map)request.getAttribute("form");	

	List<String> fv9LogistikStyle = (List<String>)form.get("fv9LogistikStyle");
	List<String> fv9LogistikStatus = (List<String>)form.get("fv9LogistikStatus");
	List<String> fv9LogistikStCom_GM = (List<String>)form.get("fv9LogistikStCom_GM");
	List<String> fv9LogistikMab_GM = (List<String>)form.get("fv9LogistikMab_GM");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/app/pep/include/header.jsp"%>
</head>

<body>
<div id="container">
	<div id="nr">
	<div id="top"><h1>5.2 Logistikkonzept</h1></div>
    <div id="content">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
		  <tr>
		    <td width="239" class="pg">&nbsp;</td>
		    <td width="311" class="pg"><h2>Status 状态</h2></td>
		    <td width="433" class="pg"><h2>Nächste Schritte下一阶段</h2></td>
		  </tr>
		  <%
		  for(int i=0; i<fv9LogistikStyle.size(); i++){
			  %>
			  <tr>
			    <td valign="top" class="pgpg" style="width:239px;">
				    <table width="220" border="0" cellspacing="0" cellpadding="0" style="margin:5px 5px 5px 0px;">
					  <tr>
			   			 <td valign="top"><h2> <%=fv9LogistikStyle.get(i) %></h2></td>
					   	 <td>
					   		<% if(("绿").equals(fv9LogistikStatus.get(i))){%>
							    	<img src="<%=request.getContextPath()%>/app/pep/images/tud.jpg" width="30" height="70" />
							<%} else if(("黄").equals(fv9LogistikStatus.get(i))){%>
							    	<img src="<%=request.getContextPath()%>/app/pep/images/tu2.png" width="30" height="70" />	
							<%} else if(("红").equals(fv9LogistikStatus.get(i))){%>
								   <img src="<%=request.getContextPath()%>/app/pep/images/r3.png" width="30" height="70" /> 
							<%}%>
					   	 </td>
					  </tr>
					</table>
				</td>
	    		<td valign="top" class="pgpg">
	    			<div class="tbnrr">
	    			<%
	    				if (fv9LogistikStCom_GM.get(i) != null && !"".equals(fv9LogistikStCom_GM.get(i))) {
	    			%>
	    				<%=fv9LogistikStCom_GM.get(i) %>
	    			<%
	    				} else {
   					%>
   	    				&nbsp;
   	    			<%		
	    				}
	    			%>
	    			</div>
			    </td>
	   			<td valign="top" class="pgpg">
	   			 	<div class="tbnrr">
	   			 	<%
	    				if (fv9LogistikMab_GM.get(i) != null && !"".equals(fv9LogistikMab_GM.get(i))) {
	    			%>
	    				<%=fv9LogistikMab_GM.get(i) %>
	    			<%
	    				} else {
   					%>
   	    				&nbsp;
   	    			<%		
	    				}
	    			%>
	   			 	</div>
				</td>
			</tr>
			  <% 
		  }
		  %>
				
	</table>
	</div>
    <%@ include file="/app/pep/include/foot.jsp"%>
</div>
</body>
</html>