<%@page import="community.MemberBoard.model.vo.MemberboardExt"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="community.MemberBoard.model.vo.Challenge"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/DetailedMemberboardForm.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Community.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ContainerBar1.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
int clickLike;
if (session.getAttribute("clickLike") != null) {
	clickLike = (Integer)session.getAttribute("clickLike");
} else {
	clickLike = 0;
}
Memberboard memberboard = (Memberboard)request.getAttribute("memberboard");

boolean editable =
		loginMember != null &&
		(
		 	loginMember.getMemberId().equals(memberboard.getMemberId())
		 	|| MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
		);
%>
<script>
</script>

<section id="board-container">
<form>
	<h2>팀원모집 게시글 보기</h2>
	<table id="tbl-board-view">
		<tr>
		<th class="column" >
			<div class="td-container1">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</div>
		</th>
		<td colspan="3"class="td td2" style="text-align:center;" ><%= memberboard.getaTitle() %></td>
		</tr>
	<tr>
		<th class="column">
		<div class="td-container1">작 성 자</div>
		</th>
		<td colspan="1" style="width : 300px">
			<input type="text" style="width:238px" name="writer" value="<%= memberboard.getMemberId() %>" class="td td2" readonly/>
		</td>
		<th  style="width:130px;" >
			<div class="td-container2">조회수</div>
		</th>
			<td colspan="1" style="width:271px;" >
			<input type="text" name="writer" value="<%= memberboard.getaReadCount() %>" class="td td2" readonly/>
		</td>
	</tr>
		<tr>
		<th class="column">
		<div class="td-container1">모집인원</div>
		</th>
		<td colspan="3">
			<div class="number-container" style="text-align:center;">
			최소 <input type="text" value="4" class="td" style="width:130px" readonly/> 명 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			최대 <input type="text" value="<%= memberboard.getsTeamCount() %>" class="td" style="width:130px" readonly/> 명			
			</div>
		</td>
	</tr>
	<tr>
		<th class="column">
		<div class="td-container1 td1-2">첨부파일</div>
		</th>
		<td colspan="3" style="text-align:center;">
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
		<th class="column">
		<div class="td-container1 td1-3">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</div>
		</th>
		<td colspan="3"><textarea name="content" class="td2-1" readonly><%= memberboard.getaContent() %></textarea></td>
	</tr>
</table>
</form>
</section>
<div class="comment-wrapper" >
<div class="container">
<button class="btn" type="button" name="application" onclick="application()">지금바로 참가신청</button>
</div>
<div id="like">
	  <input type="checkbox" name="like-checkbox" id="like1">
		<label for="like1"><span id="like-cnt"><%= memberboard.getaLike() %></span>명이 좋아합니다.</label>
		<input type="hidden" id="like-cnt-hidden" value="<%= memberboard.getaLike() %>"/>
</div>
<div class="menu">
			<% if(editable) { %>
			<input class="btn2 " type="button" value="목록보기" onclick="viewMemberboardList()">
			<input class="btn2" type="button" value="수정하기" onclick="updateMemberboard()">
			<input class="btn2" type="button" value="삭제하기" onclick="deleteMemberboard()">
			<% } else {%>
			<input class="btn " type="button" value="목록보기" onclick="viewMemberboardList()">
			<% } %>
</div>
</div>
<!-- 삭제하기를 처리할 form -->
<form
	action="<%= request.getContextPath()%>/community/memberboardDelete"
	name="teamMemberboardDelFrm"
	method="POST">
<input type="hidden" name="no" value="<%= memberboard.getaId() %>" />
</form>
<form
	action="<%= request.getContextPath()%>/community/requestTeam" 
	name="requestTeamFrm"
	method="POST">
<% if(loginMember != null ){%>
<input type="hidden" name="no" value="<%= memberboard.getaId()%>" />
<input type="hidden" name="id" value="<%= loginMember.getMemberId()%>" />
<input type="hidden" name="teamCnt" value="<%= memberboard.getsTeamCount() %>" />
<% } %>
</form>
<!-- 좋아요를 처리할 폼 -->
<% if(loginMember != null ){%>
<form
	action="<%= request.getContextPath()%>/community/memberboardLike" 
	name="memberboardLikeFrm"
	method="POST">
<input type="hidden" name="no" value="<%= memberboard.getaId()%>" />
<input type="hidden" name="id" value="<%= loginMember.getMemberId() %>" />
</form>
<% } %>
<form
	action="<%= request.getContextPath()%>/community/memberboardLikeCancel" 
	name="memberboardLikeCancelFrm"
	method="POST">
<input type="hidden" name="no" value="<%= memberboard.getaId()%>" />
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
		$(document.teamMemberboardDelFrm).submit();
	}
}
function application(){
	<% if(loginMember != null ){%>
	var writer = $("[name=writer]").val();
	var memberId = $("[name=id]").val();
	if(writer == memberId){
		alert("게시글 작성자는 자동으로 챌린지에 참가됩니다.");	
		return false;
	} else {
	$(document.requestTeamFrm).submit();		
	}
	<% } else {%>
		alert("로그인 후 이용하실 수 있습니다.");
		return false;
	<% } %>
}
$(document).ready(function(){
	if(<%= clickLike %> != null & <%= clickLike %> == <%= memberboard.getaId()%>){
		$('label').css({"background-image":"url(../image/커뮤니티/like2.png)"});
	} else if (<%= clickLike %> != null & <%= clickLike %> == 0){
		$('label').css({"background-image":"url(../image/커뮤니티/like1.png)"});
	}
});
$(document).ready(function(){
    $("#like1").change(function(){
    	<% if(loginMember != null ){%>
    	if(<%= clickLike %> != null && <%= clickLike %> == <%= memberboard.getaId()%>){
       	 if($("#like1").is(":checked")){
        		$(document.memberboardLikeCancelFrm).submit();
				$('label').css({"background-image":"url(../image/커뮤니티/like1.png)"});
       	 }
    	} else {
       		 if($("#like1").is(":checked")){
 			   $(document.memberboardLikeFrm).submit();
       		  }else {
         		/* $("#like-cnt").html(likeCnt); */
         		$(document.memberboardLikeCancelFrm).submit();
        	 }
    	}
       		<% } else {%>
    		alert("로그인 후 이용하실 수 있습니다.");
    		<% } %>
    });
});
</script>
