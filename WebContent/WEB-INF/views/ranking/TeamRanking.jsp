<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Calendar"%>
<%@page import="ranking.model.vo.TeamPoint"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Ranking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
	int one = (Integer)request.getAttribute("one");
	int two = (Integer)request.getAttribute("two");
	int three = (Integer)request.getAttribute("three");
	List<TeamPoint> list= (List<TeamPoint>) request.getAttribute("list");
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
				<img src="image/랭킹/1위.jpg" alt="" />
				<p class="ranker_id"><%= two %>번팀</p>
			</div>
			<div class="ranker_container first">
				<p class="rank">1st</p>
				<img src="image/랭킹/1위.jpg" alt="" />
				<p class="ranker_id"><%= one %>번팀</p>
			</div>
			<div class="ranker_container third">
				<p class="rank">3rd</p>
				<img src="image/랭킹/1위.jpg" alt="" />
				<p class="ranker_id"><%= three %>번팀</p>
			</div>
		</div>
	</div>

	<div class="wrapper_2">
		<div class="type" id="team_member_wrapper" style="width: 927px;">팀별랭킹</div>
		<div class="rr">
		<div class="column c3_1">랭킹</div>
		<div class="column c3_1">게시판 번호</div>		
		<div class="column c3_2">챌린지 종목</div>
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
			for(TeamPoint tp : list){
		%>
	<div class="team_member_r rr">
			<div class="row rn_1"><%= tp.getrNum() %></div>
			<div class="row rn_1"><%= tp.getaId() %></div>			
			<div class="row rn_2"><%= tp.getaTitle() %></div>
			<div class="row rn_3"><%= tp.getPoint() %></div>
			<!--
			<%if(editable){ %>
				<div class="row rn_4">
					<input type="button" class ="btn" value="뱃지 지급" 
					data-rank = "<%= tp.getrNum() %>" 
					data-user = "<%= tp.get() %>"
					onclick="badge(event)"/>
				</div>
			<%} %>
			-->
	</div>
	 	<% 		} %>
	<% 		} %>
		</div>
	<div class="type2">
		<div class="type2-1" style="width: 457px;" onclick="personalRanking()">개인랭킹</div>
	<div class="type2-1" style="width: 457px;" onclick="teamMemberRanking()">팀원별랭킹</div>	
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
function teamMemberRanking(){
	location.href="<%=request.getContextPath() %>/ranking/TeamMemberRanking";	
}
function badge(event){
	var $ele = $(event.target);
	var $ele_parent = $ele.parent();
	var $rank = $ele.attr('data-rank');
	var $user = $ele.attr('data-user');
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/badgeTeamRankInsert?rank="+ $rank +"&user="+$user,
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