<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List indexes = (List)session.getAttribute("indexes"); 
	String current = (String)request.getAttribute("current");
	
	if (current == null) {
		current = "1";
	} 
	
	int cur = Integer.parseInt(current);
	
	String next = "<img src=\"/ph/app/pep/images/next.jpg\" width=\"47\" height=\"18\">";
	String prev = "<img src=\"/ph/app/pep/images/prev.jpg\" width=\"47\" height=\"18\">";
	
	if (indexes != null) {
		int pageSize = indexes.size();
		
		if (cur > 1) {
			prev = "<a href='" + request.getContextPath() + "/app/pep/view/view.do?current=" + (cur-1) + "'><img src=\"/ph/app/pep/images/prev.jpg\" width=\"47\" height=\"18\"></a>";
		}
		
		if (cur < pageSize) {
			next = "<a href='" + request.getContextPath() + "/app/pep/view/view.do?current=" + (cur+1) + "'><img src=\"/ph/app/pep/images/next.jpg\" width=\"47\" height=\"18\"></a>";
		}
	}
%>
<div id="footer">
	<div class="foot">
		<div class="lanst">&nbsp;<a href="<%=request.getContextPath() %>/app/pep/catalogue.jsp">目录</a></div>
		<div class="lanstk">Produkt Management</div>
		<div class="clear"></div>
    </div>
	<div class="ft">
		<div class="ztu"><img src="/ph/app/pep/images/footerlogo.jpg" /></div>
	    <div class="page">
		    <P><%=prev %>&nbsp;&nbsp;Seite<%=current %>&nbsp;&nbsp;<%=next %></P>
	    </div>
	    <div class="ylogo"><img src="/ph/app/pep/images/yiqilogo.jpg" /></div>
	    <div class="clear"></div>
    </div>
</div>
<div class="clear"></div>