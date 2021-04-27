<%@page import="member.model.service.MemberService"%>
<%@page import="community.MemberBoard.model.vo.Challenge"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/MemberboardForm.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
	Memberboard memberboard = (Memberboard)request.getAttribute("memberboard");

boolean editable =
		loginMember != null &&
		(
		 	loginMember.getMemberId().equals(memberboard.getMemberId())
		 	|| MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
		);
%>

<div id="table_container">
<section id="board-container">
<form
	name="memberboardEnrollFrm"
	action="<%=request.getContextPath() %>/community/MemberboardEnroll" 
	method="post"
	enctype="multipart/form-data"
	>
	<h2>팀원모집 게시글 보기</h2>
	<table id="tbl-board-view">
		<tr>
		<th class="column" >
			<div class="td-container1">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</div>
		</th>
		<td class="td td2" style="text-align:center;" ><%= memberboard.getaTitle() %></td>
		</tr>
	<tr>
		<th>
		<div class="td-container1">작 성 자</div>
		</th>
		<td>
			<input type="text" name="writer" value="<%= loginMember.getMemberId() %>" class="td td2" readonly/>
		</td>
	</tr>
		<tr>
		<th>
		<div class="td-container1">모집인원</div>
		</th>
		<td>
			<div class="number-container" style="text-align:center;">
			최소 <input type="text" value="4" class="td" style="width:130px" readonly/> 명 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			최대<input type="text" value="<%= memberboard.getsTeamCount() %>" class="td" style="width:130px" readonly/>명			
			</div>
		</td>
	</tr>
	<tr>
		<th>
		<div class="td-container1 td1-2">첨부파일</div>
		</th>
		<td style="text-align:center;">
		<p> 파일 다운로드 : 
		<a href="<%= request.getContextPath()%>/community/memberboardFileDownload?no=<%= memberboard.getaId() %>">
				<%= memberboard.getMemberboardAttachment().getOriginalFilename() %></a>
		</p>
			<div id="img_container">			
				<img id="example_img"
					src="<%=request.getContextPath() %>/upload/memberboard/<%= memberboard.getMemberboardAttachment().getRenamedFilename() %>" alt="" >
			</div>
		</td>
	</tr>
	<tr>
		<th>
		<div class="td-container1 td1-3">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</div>
		</th>
		<td><textarea name="content" class="td2-1" readonly><%= memberboard.getaContent() %></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<% if(editable) { %>
			<input class="btn2 " type="button" value="목록보기" onclick="viewMemberboardList()">
			<input class="btn2" type="button" value="수정하기" onclick="updateMemberboard()">
			<input class="btn2" type="button" value="삭제하기" onclick="deleteMemberboard()">
			<% } else {%>
			<input class="btn " type="button" value="목록보기" onclick="viewMemberboardList()">
			<% } %>
		</th>
	</tr>
</table>
</form>
</section>
</div>
<!-- 삭제하기를 처리할 form -->
<form
	action="<%= request.getContextPath()%>/community/memberboardDelete"
	name="memberboardDelFrm"
	method="POST">
<input type="hidden" name="no" value="<%= memberboard.getaId() %>" />
</form>
<script>
function viewMemberboardList(){
	location.href = "<%=request.getContextPath() %>/community/memberboardList";
}
function updateMemberboard(){
	location.href = "<%=request.getContextPath() %>/community/memberboardUpdate?no=<%= memberboard.getaId()%>";
}
function deleteMemberboard(){
	if(confirm("게시글을 정말 삭제하시겠습니까?")){
		$(document.memberboardDelFrm).submit();
	}
}
</script>
