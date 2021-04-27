<%@page import="challenge.model.vo.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Challenge> list = (List<Challenge>)request.getAttribute("list");
	String nowChallenge = (String)request.getAttribute("nowChallenge");
	System.out.println(nowChallenge);
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/LongChallenge.css">
<div class="Container-bar">
	<ul>
		<li><a href="<%=request.getContextPath()%>/challenge/ShortChallenge">하루 챌린지</a></li>
		<li><a href="<%=request.getContextPath()%>/challenge/LongChallenge">기간 챌린지</a></li>
		<li><a href="<%=request.getContextPath()%>/challenge/UpdateChallenge">수정</a>
	</ul>
</div>
<div>
	
	<%-- <% 
		if(nowChallenge.equals("short")){
		if(list != null && !list.isEmpty()) { 
			for(Challenge c : list){
	%>	
			<ul>
				<li><%= c.getChallenge_id() %></li>
				<li><%= c.getChallenge_name() %></li>
			</ul>
	<% 
			}
		
	 	} else { 
	 %>
		
	<% } } else { 
		if(list != null && !list.isEmpty()) { 
			for(Challenge c : list){
	%>
			<div class="flip"><%= c.getChallenge_name() %></div>
			<div class="panel">
				<p><%=c.getChallenge_info() %></p>
				<p><input type="submit" value="도전"></p>
			</div>
	<% } %> --%>
	
	<div id='pageBar'><%= request.getAttribute("pageBar") %></div>
</div>
</body>
</html>