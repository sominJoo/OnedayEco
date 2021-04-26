<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Ranking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	
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
				<p class="ranker_id">kimhaejun</p>
			</div>
			<div class="ranker_container first">
				<p class="rank">1st</p>
				<img src="image/랭킹/1위.jpg" alt="" />
				<p class="ranker_id">kimhaejun</p>
			</div>
			<div class="ranker_container third">
				<p class="rank">3rd</p>
				<img src="image/랭킹/1위.jpg" alt="" />
				<p class="ranker_id">kimhaejun</p>
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
	<div class="team_member_r rr">
			<div class="row rn_1">1</div>
			<div class="row rn_2">honggd</div>
			<div class="row rn_3">150</div>
	</div>
	<div class="team_member_r my rr">
		<div class="row rn_1">8</div>
		<div class="row rn_2">kimhaejun</div>
		<div class="row rn_3">130</div>
	</div>
	</div>	
	<div class="type2">
		<div class="type2-1" onclick="personalRanking()">개인랭킹</div>
	<div class="type2-1" onclick="teamMemberRanking()">팀원별랭킹</div>	
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
</script>