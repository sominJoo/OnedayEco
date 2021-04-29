<%@page import="ranking.model.vo.PersonalPoint"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.service.MemberService"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Ranking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<style>
.btn{
	border: none;
	background-color: white;;
	outline: 0;
	padding: 0.3vw;
}
.btn:hover{
	background-color: #00008B;	
	border: 1px soild #00008B;
	color:white;
	outline: 0;
}
</style>
<%
	String one = (String)request.getAttribute("one");
	String two = (String)request.getAttribute("two");
	String three = (String)request.getAttribute("three");
	List<PersonalPoint> list = (List<PersonalPoint>) request.getAttribute("list");
%>
<%
	Calendar now = Calendar.getInstance();
	
	String time1 = Integer.toString(now.get(Calendar.DATE));
	String sdf = "01";
	
	
	boolean editable_date = time1.equals(sdf);
	
	boolean editable =false;
	if(loginMember !=null)
		editable= MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole());
%>



<div class="wrapper_1">
	<div class="wrapper_2">
		<div class="ranker">
		<div id="trophy">
			<img src="image/랭킹/trophy.png"/>
		</div>
			<p id="intro_ranker">랭커 3위를 소개합니다</p>
			<div class="ranker_container second">
				<p class="rank">2nd</p>
				<img src="image/랭킹/prize2.png" alt="" />
				<p class="ranker_id"><%= two %></p>
			</div>
			<div class="ranker_container first">
				<p class="rank">1st</p>
				<img src="image/랭킹/prize1.png" alt="" />
				<p class="ranker_id"><%= one %></p>
			</div>
			<div class="ranker_container third">
				<p class="rank">3rd</p>
				<img src="image/랭킹/prize3.png" alt="" />
				<p class="ranker_id"><%= three %></p>
			</div>
		</div>
	</div>
	<div class="wrapper_2">
		<div class="type" id="personal_wrapper">개인랭킹</div>
		<div class="rr">
		<div class="column c1_1">랭킹</div>
		<div class="column c1_2">아이디</div>
		<div class="column c1_3">총포인트</div>
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
			for(PersonalPoint pp : list){
		%>
		<div class="rr r1">
			<div class="row rn_1"><%= pp.getrNum() %></div>
			<div class="row rn_2"><%= pp.getMemberId() %></div>
			<div class="row rn_3"><%= pp.getPoint() %></div>
			
			
		<%if(editable){ %>
			<div class="row rn_4">
				<input type="button" class ="btn" value="뱃지 지급" 
				data-rank = "<%= pp.getrNum() %>" 
				data-user = "<%= pp.getMemberId() %>" 
				onclick="badge(event)"/>
			</div>
			<%} %>
		
		</div>
 	<% 		} %>
	<% 		} %>
	</div>
	<div class="type2">
		<div class="type2-1" onclick="teamRanking()">팀별랭킹</div>
	<div class="type2-1" onclick="teamMemberRanking()">팀원별랭킹</div>
	<% if (loginMember  != null) { %>
	
	<% } %>
 	<div class="page-bar">
 	<%= request.getAttribute("pageBar")%>
 	</div>
	</div>
<% if(loginMember != null)  {%>
<form
	action="<%= request.getContextPath() %>/ranking/TeamMemberRanking"
	name="teamMemberRankingFrm"
	method="POST">
<input type="hidden" name="member_id" value="<%= loginMember.getMemberId()%>"/>	
</form>
<% } %>
</div>
</body>
<footer>
</footer>
</html>
<script>
function teamRanking() {
	location.href="<%=request.getContextPath() %>/ranking/TeamRanking";
}
function teamMemberRanking(){
	<% if (loginMember  != null) { %>
		$(document.teamMemberRankingFrm).submit();		
		<% } else {  %>
		alert("로그인 후 사용하실 수 있습니다.");
		<% }%>
}

function badge(event){
	var $ele = $(event.target);
	var $ele_parent = $ele.parent();
	var $rank = $ele.attr('data-rank');
	var $user = $ele.attr('data-user');
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/badgeRankInsert?rank="+ $rank +"&user="+$user,
		method : "get",
		success : function(data){
			$ele.remove();
			$ele_parent.append('<input class="btn" type="button" value="완료 " style="color:gray;"/>');
		},
		error : function (xhr, status,err) {
			console.log(xhr, status,err);
		}
	});	
}
</script>