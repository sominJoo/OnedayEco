<%@page import="community.TeamMemberBoard.model.vo.TeamMemberboard"%>
<%@page import="community.MemberBoard.model.vo.MemberboardExt"%>
<%@page import="member.model.vo.Member"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/containerBar.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/TeamMemberBoard.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Community.css">
<%
List<TeamMemberboard> list = (List<TeamMemberboard>) request.getAttribute("list");%>
	<div class="container">
	<button class="btn" type="button" onclick="teamMemberboardEnroll()">정보공유 글쓰기</button>
	</div>
 		<% if (list == null || list.isEmpty()) {%>
 			<div class="noBoard">
			조회된 게시글이 없습니다.		
 			</div>
		<% } else {  %>
		<div class="wrapper2">
		<% 
			for(TeamMemberboard tmb : list){ 		
		%>	
 	 	<div class="board-container" onclick="location.href='<%= request.getContextPath()%>/community/detailedTeamMemberBoardView?no=<%= tmb.getTeamAId() %>';">
 		<div class="status"><p><%= tmb.getMemberId() %></p></div>
 		<% if (tmb.getaTeamAttachment() != null){ %>
 		<div class="img-container">
			<img src="<%=request.getContextPath() %>/upload/teammemberboard/<%= tmb.getaTeamAttachment().getRenamedFilename() %>" alt="" />
 		</div>
 		<% } %>
 		<div class="content-container">
 		<p>
 		<span class="th">제 &nbsp;&nbsp;&nbsp;목</span>
 		<span class="td"><%= tmb.getaTitle() %></span>
 		</p>
		<p>
 		<span class="th">게 시 일</span>
 		<span class="td"><%= tmb.getaRegDate() %></span>
 		</p>
 		<p>
 		<span class="td content"><%= tmb.getaContent() %></span>
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
	<% } %>
}
</script>
