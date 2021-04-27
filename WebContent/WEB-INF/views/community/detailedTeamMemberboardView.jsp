<%@page import="community.TeamMemberBoard.model.vo.TeamComment"%>
<%@page import="java.util.List"%>
<%@page import="community.TeamMemberBoard.model.vo.TeamMemberboard"%>
<%@page import="community.MemberBoard.model.vo.MemberboardExt"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="community.MemberBoard.model.vo.Challenge"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/containerBar.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/DetailedTeamMemberboardForm.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Community.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
int clickLike;
if (session.getAttribute("clickLike") != null) {
	clickLike = (Integer) session.getAttribute("clickLike");
} else {
	clickLike = 0;
}
TeamMemberboard teamMemberboard = (TeamMemberboard) request.getAttribute("teamMemberboard");
List<TeamComment> commentList = (List<TeamComment>) request.getAttribute("commentList");

boolean editable = loginMember != null && (loginMember.getMemberId().equals(teamMemberboard.getMemberId())
		|| MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()));
%>
<script>
<%if (msg != null) {%>
alert("<%=msg%>"); 
<%}%>
</script>

<section id="board-container">
	<form>
		<h2>우리팀 게시글 보기</h2>
		<table id="tbl-board-view">
			<tr>
				<th class="column">
					<div class="td-container1">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</div>
				</th>
				<td colspan="3" class="td td2" style="text-align: center;"><%=teamMemberboard.getaTitle()%></td>
			</tr>
			<tr>
				<th class="column">
					<div class="td-container1">작 성 자</div>
				</th>
				<td colspan="1" style="width: 300px"><input type="text"
					style="width: 238px" name="writer"
					value="<%=teamMemberboard.getMemberId()%>" class="td td2" readonly /></td>
				<th style="width: 130px;">
					<div class="td-container2">조회수</div>
				</th>
				<td colspan="1" style="width: 271px;"><input type="text"
					name="writer" value="<%=teamMemberboard.getaReadCount()%>"
					class="td td2" readonly /></td>
			</tr>
			<tr>
				<th class="column">
					<div class="td-container1 td1-2">첨부파일</div>
				</th>
				<td colspan="3" style="text-align: center;">
					<p>
						파일 다운로드 : <a
							href="<%=request.getContextPath()%>/community/memberboardFileDownload?no=<%=teamMemberboard.getTeamAId()%>">
							<%=teamMemberboard.getaTeamAttachment().getOriginalFilename()%></a>
					</p>
					<div id="img_container">
						<img id="example_img"
							src="<%=request.getContextPath()%>/upload/teammemberboard/<%=teamMemberboard.getaTeamAttachment().getRenamedFilename()%>"
							alt="">
					</div>
				</td>
			</tr>
			<tr>
				<th class="column">
					<div class="td-container1 td1-3">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</div>
				</th>
				<td colspan="3"><textarea name="a-content" class="td2-1" readonly><%=teamMemberboard.getaContent()%></textarea></td>
			</tr>
			<tr>
				<th colspan="4">
					<div class="menu">
						<%
							if (editable) {
						%>
						<input class="btn2 " type="button" value="목록보기"
							onclick="viewTeamMemberboardList()"> <input class="btn2"
							type="button" value="수정하기" onclick="updateTeamMemberboard()">
						<input class="btn2" type="button" value="삭제하기"
							onclick="deleteTeamMemberboard()">
						<%
							} else {
						%>
						<input class="btn " type="button" value="목록보기"
							onclick="viewMemberboardList()">
						<%
							}
						%>
					</div>
				</th>
			</tr>
		</table>
	</form>
</section>
<!-- 댓글등록 테이블 -->
<div class="comment-wrapper">
	<div class="container">
		<div id="like">
			<input type="checkbox" name="like-checkbox" id="like1"> <label
				for="like1"><%=teamMemberboard.getaLike()%>명이 좋아합니다.</label>
		</div>
		<div class="comment-table1">
			<form
				action="<%=request.getContextPath()%>/community/teamMemberboardCommentInsert"
				method="post" name="teamMemberboardCommentFrm">
				<input type="hidden" name="boardNo"
					value="<%=teamMemberboard.getTeamAId()%>" />
					<input type="hidden" name="writer"
					value="<%=loginMember != null ? loginMember.getMemberId() : ""%>" />
				<!-- boardView페이지는 로그인하지 않고도 볼 수 있음
                	  로그인 안하고 loginMember.getMemberId하면 null point exception
                	 우선 로그인하지 않은 경우는 공란으로 메꿔줌-->
				<input type="hidden" name="commentLevel" value="1" />
				<!-- 댓글이니까 commentLevel은 1로 고정 -->
				<input type="hidden" name="commentRef" value="0" />
				<!-- commentRef는 대댓글에만 해당 -> 0으로 세팅 -->
				<textarea name="comment" cols="41" rows="3"></textarea>
				<input type="submit" class="comment-enroll-btn" value="등록" />
				</form>
		</div>
				<!--table#tbl-comment -->
		<% if(commentList != null && !commentList.isEmpty()){ %>	
		<table id="tbl-comment">
		<% for(TeamComment tc : commentList){ 
			boolean removable =
					loginMember != null &&
					(
				 		loginMember.getMemberId().equals(tc.getMemberId())
				 		|| MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
					);
			if(tc.getCommentLevel() == 1){
		%>	
			<!-- 댓글 : commentLevel = 1 -->
			<tr class="level1">
				<td>
			<div class="comment-div">
					<!-- sub태그 : 글씨를 첨자처럼 작게 나타내주는 태그 -->
					<sub class="comment-writer"><%= tc.getMemberId() %></sub>&nbsp;&nbsp;&nbsp;
					<sub class="comment-date"><%= tc.getCommentRegDate() %></sub>
					<br />
					<span style="word-break: break-all;">
					<%= tc.getCommentContent() %>
					</span>
				</td>
			</div>
				<td>
					<!-- 답글 버튼 
						 value속성으로는 몇번 댓글에 대한 답글인지 - 댓글 번호-->
					<button class="comment-btn btn-reply" value="<%= tc.getCommentId() %>">답글</button>
					<% if(removable) { %>
					<button class="comment-btn btn-delete" value="<%= tc.getCommentId() %>">삭제</button>
					<% } %>
				</td>
			</tr>	
		<% 	} 
			else {%>
				<!-- 대댓글-->
				<tr class="level2">
				<td>
					<div class="comment-div2">
					<!-- sub태그 : 글씨를 첨자처럼 작게 나타내주는 태그 -->
					<sub class="comment-writer"><%= tc.getMemberId() %></sub> 
					<sub class="comment-date"><%= tc.getCommentRegDate() %></sub>
					<br />
					<span><%= tc.getCommentContent() %></span>
					</div>
				</td>
				<!-- 대댓글에는 더이상 답글 버튼을 제공하지 않음 -->
				<td>
					<% if(removable) { %>
					<button class="comment-btn2 btn-delete" value="<%= tc.getCommentId() %>">삭제</button>
					<% } %>
				</td>
			</tr>	
		<% 		}
			} %>
		</table>
		<% } %>			
	</div>
</div>

<!-- 삭제하기를 처리할 form -->
<form
	action="<%=request.getContextPath()%>/community/teamMemberboardDelete"
	name="teamMemberboardDelFrm" method="POST">
	<input type="hidden" name="no" value="<%=teamMemberboard.getTeamAId()%>" />
</form>
<!-- 좋아요를 처리할 폼 -->
<form
	action="<%=request.getContextPath()%>/community/teamMemberboardLike"
	name="teamMemberboardLikeFrm" method="POST">
	<input type="hidden" name="no"
		value="<%=teamMemberboard.getTeamAId()%>" />
</form>
<form
	action="<%=request.getContextPath()%>/community/teamMemberboardLikeCancel"
	name="teamMemberboardLikeCancelFrm" method="POST">
	<input type="hidden" name="no"
		value="<%=teamMemberboard.getTeamAId()%>" />
</form>
<!-- 댓글,답글 삭제하기를 처리할 숨겨진 폼 -->
<form
	action="<%= request.getContextPath()%>/community/teamMemberboardCommentDelete"
	name="teamMemberboardCommentDelFrm"
	method="POST">
<!-- name값으로 boardComment의 no -->
<input type="hidden" name="no" />
<!-- 삭제 후 현재페이지로 돌아옴 - 돌아올 게시글 번호도 필요함
	 무엇을 삭제하든 게시물번호는 동일하므로 value로 고정해줌 -->
<input type="hidden" name="boardNo" value="<%= teamMemberboard.getTeamAId() %>" />
</form>
<script>
function viewTeamMemberboardList(){
	location.href = "<%=request.getContextPath()%>/community/teamMemberboard";
}
function updateTeamMemberboard(){
	location.href = "<%=request.getContextPath()%>/community/teamMemberboardUpdate?no=<%=teamMemberboard.getTeamAId()%>";
	}
function deleteTeamMemberboard() {
	if (confirm("게시글을 정말 삭제하시겠습니까?")) {
		$(document.teamMemberboardDelFrm).submit();
	}
}

$(document).ready(function(){
	if(<%= clickLike %> != null & <%= clickLike %> == <%= teamMemberboard.getTeamAId() %>){
		$('label').css({"background-image":"url(../image/커뮤니티/like2.png)"});
	} else if (<%= clickLike %> != null & <%= clickLike %> == 0){
		$('label').css({"background-image":"url(../image/커뮤니티/like1.png)"});
	}
});
$(document).ready(function(){
    $("#like1").change(function(){
    	<% if(loginMember != null ){%>
    	if(<%= clickLike %> != null && <%= clickLike %> == <%= teamMemberboard.getTeamAId() %>){
       	 if($("#like1").is(":checked")){
        		$(document.teamMemberboardLikeCancelFrm).submit();
				$('label').css({"background-image":"url(../image/커뮤니티/like1.png)"});
       	 }
    	} else {
       		 if($("#like1").is(":checked")){
 			   $(document.teamMemberboardLikeFrm).submit();
       		  }else {
         		/* $("#like-cnt").html(likeCnt); */
         		$(document.teamMemberboardLikeCancelFrm).submit();
        	 }
    	}
       		<% } else {%>
    		alert("로그인 후 이용하실 수 있습니다.");
    		<% } %>
    });

$("[name=comment]").focus(function() {
/* 로그인 여부 검사 */
<%if (loginMember == null) {%>
	/* 로그인을 하지 않았다면, loginAlert()함수 호출 */
	loginAlert();
<%}%>
	});
	/* 버튼을 먼저 누를경우, 유효성 검사 */
	// document객체(상위)에서 submit이벤트에서 핸들링하는데, boardCommentFrm이 요청했을 때
	// form위의 태그들, 그 위의 태그들까지 모두 document객체에 의해 관리됨, 최상위부모태그인 document객체에 적용
	// document객체가 아니더라도 두개의 form을 아우를 수 있는 부모태그면 ok
	// name=boardCommentFrm인 이벤트타겟에 submit 이벤트가 document에 전달이 되면 해당 function을 실행해라
$(document).on('submit', '[name=teamMemberboardCommentFrm]', function(e) {
		// $(document.boardCommentFrm).submit(function(){
		/* 로그인 여부 검사 */
<%if (loginMember == null) {%>
	/* 로그인을 하지 않았다면, loginAlert()함수 호출 */
		loginAlert();
		/* 제출되지 않도록 처리 */
		return false;
<%}%>
	// 댓글내용
		// 두번째 인자로 context 지정 (이 하위에서 이 선택자를 찾음)
		var $content = $("[name=comment]", e.target);
		/* 아무문자나 개행문자까지도 하나라도 있는지 검사 */
		if (/^(.|\n)+$/.test($content.val()) == false) {
			/* 알림창 띄우기 */
			alert("댓글내용을 작성하세요.");
			/* content부분에 커서 포커스 처리 */
			$content.focus();
			/* 제출되지 않도록 처리 */
			return false;
	}
});
		
		
function loginAlert(){
		/* 경고창을 띄우고 */
		alert("로그인 이후 이용할 수 있습니다.");
	}
	
/* 답글 버튼 클릭시 폼 제공 */
$(".btn-reply").click(function(){
	// 로그인 안했을 경우, 대댓글 못쓰도록 경고창 띄우고 밑에것이 처리안되도록 return처리
	<% if(loginMember == null){ %>
		loginAlert();
		return;
	<% } %>
	// 대댓글 작성폼 동적으로 생성
	// 바깥과 안쪽의 쌍따음표|홑따음표가 겹치지 않도록 주의
	var html = "<tr>";
	html += "<td  colspan = '2' style='display : none; text-align : left'>";
	// form 시작
    html += '<form action="<%=request.getContextPath()%>/community/teamMemberboardCommentInsert" method="post" name="teamMemberboardCommentFrm">';
    html += '<input type="hidden" name="boardNo" value="<%= teamMemberboard.getTeamAId() %>" />';
    html += '<input type="hidden" name="writer" value="<%= teamMemberboard != null ? loginMember.getMemberId() : ""%>"/>';
    html += '<input type="hidden" name="commentLevel" value="2" />'; // commentLevel은 2로 설정
    html += '<input type="hidden" name="commentRef" value="' + $(this).val() + '" />';  // 대댓글 -> commentRef값이 있음
    html += '<textarea name="comment" cols="41" rows="2"></textarea>';
    html += '<button type="submit" class="comment-enroll-btn btn-insert-reply">등록</button>'; // 여러개일수 있으므로 id값이 아닌 class값으로 처리
	html += '</form>';
	html += "</td";
	html += "</tr>";
	
	// 버튼의 tr태그 찾기
	var $trOfBtn = $(this).parent().parent(); // 버튼의 parent(td), 그 td의 parent(tr)
	// 위에서 만든 html을 jQuery객체로 만든 후 insertAfter
	$(html)
		.insertAfter($trOfBtn) // 버튼 태그가 속한 tr태그 다음요소로 html을 추가함
		.children("td") // tr태그의 자식태그(td)를 찾아서
		.slideDown(800); // slideDown효과를 주기
		
	// 버튼은 1회용으로 처리
	// 한번만 버튼이 생성되고 그 후로는 답글 버튼을 눌러도 답글폼이 만들어지지 않도록
	$(this).off("click"); // click 이벤트 핸들러를 막기
});
/* 삭제버튼 클릭시 이벤트 핸들러 */
$(".btn-delete").click(function(){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.teamMemberboardCommentDelFrm);
		var boardCommentNo = $(this).val(); // 삭제 button의 val를 가져옴
		// 어떤 댓글/답글에 대한 삭제버튼인지 가져와야 함
		// no의 value값을 buttonCommentNo로 세팅
		$frm.find("[name=no]").val(boardCommentNo);
		$frm.submit();
		}
	});
</script>