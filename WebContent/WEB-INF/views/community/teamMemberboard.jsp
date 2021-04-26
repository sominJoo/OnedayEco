<%@page import="member.model.vo.Member"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/containerBar.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/MemberBoard.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Community.css">
<%
List<Memberboard> list = (List<Memberboard>) request.getAttribute("list");

%>
	<div class="container">
	<button class="btn" type="button" onclick="teamMemberboardEnroll()">정보공유 글쓰기</button>
	</div>
 		<% if (list == null || list.isEmpty()) {%>
 			<div class="noBoard">
			조회된 게시글이 없습니다.		
 			</div>
		<%-- 반복문을 통해 member객체를 하나씩 꺼내서, tr태그에 하나씩 넣기
			 option값들에 대한 null처리 --%>
		<% } else {  %>
		<div class="wrapper2">
		<% 	for(Memberboard mb : list){
	%>
 	 	<div class="board-container" onclick="memberboardView()">
 		<div class="status">
 			<p>모집중</p>
 		</div>
 		<div class="img-container">
 			<img src="../image/랭킹/1위.jpg"/>
 		</div>
 		<div class="content-container">
 		<p>
 		<span class="th">작 성 자</span>
 		<span class="td"><%= mb.getMemberId() %></span>
 		</p>
 		<p>
 		<span class="th th-title">챌린지명</span>
 		<span class="td td-title"><%= mb.getaTitle() %></span>
 		</p>
		<p>
 		<span class="th">모집인원</span>
 		<span class="td">신청자 : 4 / 정원 : <%= mb.getsTeamCount() %></span>
 		</p>
		<p>
 		<span class="th">게 시 일</span>
 		<span class="td"><%= mb.getaRegDate() %></span>
 		</p>
 		</div>
 	</div>
 	<% 		} %>
	<% 		} %>
		</div>
</body>
 	<div class="page-bar">
 	<%= request.getAttribute("pageBar") %>
 	</div>
</html>
<script>
function teamMemberboardEnroll(){
	<% if(loginMember != null) { %>
	location.href = "<%=request.getContextPath() %>/community/teamMemberboardForm";
	<% } else {%>
	alert("로그인 후 작성하실 수 있습니다.");
	<% }%>
}
function memberboardView(){
	location.href = "<%=request.getContextPath() %>/Community/";
}
</script>
