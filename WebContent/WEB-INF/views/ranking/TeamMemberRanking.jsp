
<%@page import="ranking.model.vo.TeamMemberPoint"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/TeamMemberRanking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
	String one = (String)request.getAttribute("one");
	String two = (String)request.getAttribute("two");
	String three = (String)request.getAttribute("three");
	List<TeamMemberPoint> list = (List<TeamMemberPoint>) request.getAttribute("list");
%>
<div class="wrapper_1">
	<div class="wrapper_2">
		<div class="ranker">
		<div id="trophy">
			<img src="/onedayeco/image/랭킹/trophy.png"/>
		</div>
			<p id="intro_ranker">랭커 3위를 소개합니다</p>
			<div class="ranker_container second">
				<p class="rank">2nd</p>
				<img src="/onedayeco/image/랭킹/prize2.png" alt="" />
			<%
 				if (list == null || list.isEmpty()) {
 			%>
 				<p class="ranker_id"></p>
 				<br />
 			<%
				} else {
			%>
				<p class="ranker_id"><%= two %></p>
 			<%
				}
			%>
			</div>
			<div class="ranker_container first">
				<p class="rank">1st</p>
				<img src="/onedayeco/image/랭킹/prize1.png" alt="" />
			<%
 				if (list == null || list.isEmpty()) {
 			%>
 			 				<p class="ranker_id"></p>
 				<br />
 			<%
				} else {
			%>
				<p class="ranker_id"><%= one %></p>
 			<%
				}
			%>
			</div>
			<div class="ranker_container third">
				<p class="rank">3rd</p>
				<img src="/onedayeco/image/랭킹/prize3.png" alt="" />
			<%
 				if (list == null || list.isEmpty()) {
 			%>
 			 				<p class="ranker_id"></p>
 				<br />
 			<%
				} else {
			%>
				<p class="ranker_id"><%= three %></p>
 			<%
				}
			%>
			</div>
		</div>
	</div>

	<div class="wrapper_2">
		<div class="type" id="team_member_wrapper">팀원별랭킹</div>
		<div class="rr">
		<div class="column c3_1">랭킹</div>
		<div class="column c3_2">아이디</div>
		<div class="column c3_3">누적 포인트</div>
		</div>
		<%
 			if (list == null || list.isEmpty()) {
 		%>
 		 	<div class="noBoard">
			조회된 랭킹이 없습니다.		
 			</div>
 		<%
			} else {
		%>
		<%
			for(TeamMemberPoint tmp : list){
		%>
		<div class="team_member_r rr ">
			<div class="row rn_1"><%= tmp.getrNum() %></div>
			<div class="row rn_2"><%= tmp.getMemberId() %></div>
			<div class="row rn_3"><%= tmp.getSum() %></div>
	</div>
	 	<% 		} %>
	<% 		} %>
	</div>	
	<div class="type2">
		<div class="type2-1" onclick="personalRanking()">개인랭킹</div>
	<div class="type2-1" onclick="teamRanking()">팀별랭킹</div>	
	 	<div class="page-bar">
 	<%= request.getAttribute("pageBar")%>
 	</div>
	</div>
</div>
</body>
<footer>
</footer>
</html>
<script>
function personalRanking() {
	location.href="<%=request.getContextPath() %>/ranking";
}
function teamRanking() {
	location.href="<%=request.getContextPath() %>/ranking/TeamRanking";
}
</script>