<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="onedayeco.article.model.vo.Article"%>
<%@page import="onedayeco.article.model.vo.ArticleComment"%>
<%@page import="java.util.List"%>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>커뮤니티게시판 상세보기</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/CommunityBoardView.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>

<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>
<%
Article article= (Article)request.getAttribute("article");

 boolean editable =
		loginMember != null &&
		(loginMember.getMemberId().equals(article.getUser_id())
		 || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
		 );

	List<ArticleComment> commentList = (List<ArticleComment>)request.getAttribute("commentList");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CommunityBoardView.css" />

<section id="board-container">

	<table id="tbl-board-view">
		<tr>
			<th>No.</th>
			<td><%=article.getArticle_id() %></td>
			<th>제 목</th>
			<td><%=article.getArticle_title() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=article.getUser_id() %></td>
			<th>작성일</th>
			<td><%=article.getArticle_reg_date() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=article.getArticle_read_count() %></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td colspan="4">
			<br />
			<% 	if(article.getAttach() != null){ %>
			<img src="<%=request.getContextPath() %>/upload/board/<%=article.getAttach().getRenamed_filename() %>" alt=""  style="width:200px"/>
			<% } %> <br />
			<%=article.getArticle_content() %></td>
		</tr>
		<tr>
				<th>첨부파일</th>
			<td colspan="4">
				<!-- 아래 구문이 없다면 nullpointexception떨어짐  -->
				<% 	if(article.getAttach() != null){ %>
			<!-- 	첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 -->
				<img src="<%=request.getContextPath() %>/image/file-storage.png" width=16px>
				<a href="<%=request.getContextPath()%>/community/fileDownload?no=<%= article.getAttach().getArticle_id() %>"><%= article.getAttach().getOriginal_filename()%></a>
				
				<% } %>
			</td>
		</tr>
		<tr colspan="4" style="border-bottom: none">
 	<% if(editable){%>
		<!-- 	작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 -->
			<th colspan="4">
				<input id="delbtn" type="button" value="삭제하기" onclick="deleteBoard()">
				<input id="changebtn" type="button" value="수정하기" onclick="updateBoard()">
			</th>
		</tr>
		<%} %> 
	</table>

	<hr style="margin-top:30px;" />	
	<br />
	<div class="comment-container">
        <div class="comment-editor">
            <form action="<%=request.getContextPath()%>/community/communityBoardCommentInsert" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%=article.getArticle_id() %>" />
                <input type="hidden" name="writer" value="<%=loginMember != null? loginMember.getMemberId() : "" %>" /> 
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
                <br>
				<textarea name="content" cols="100" rows="3" resize="none"></textarea>
                <button type="submit" id="btn-insert">등록</button>
            </form>
        </div>
		<!--table#tbl-comment-->
		<%if(commentList != null && !commentList.isEmpty()) {%>
		
		<table id="tbl-comment">
		 <%
			for(ArticleComment ac : commentList){
				boolean removable =
						loginMember != null &&
						(loginMember.getMemberId().equals(ac.getUser_id())
						 || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
						 );
				if(ac.getComment_level()== 1){
					//댓글
	%>				 
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= ac.getUser_id() %></sub>
						<sub class="comment-date"><%=ac.getComment_reg_date()%></sub>
						<br />
						<%= ac.getComment_content()%>
					</td>
					<td>
						<button class="btn-reply" value="<%= ac.getComment_id() %>"> 답글 </button>
						<%if(removable){%> 
						<button class="btn-delete" value="<%=ac.getComment_id() %>"> 삭제 </button>
						<%} %>
					</td>
				</tr>
		<% 		}else{
					//대댓글
		%>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%=ac.getUser_id() %></sub>
						<sub class="comment-date"><%=ac.getComment_reg_date() %></sub>
						<br />
						<%= ac.getComment_content() %>
					</td>
					<td>
						<%if(removable){%> 
						<button class="btn-delete" value="<%=ac.getComment_id() %>"> 삭제 </button>
						<%} %>
					</td>
		
		
		
		<% 		}
			}
		%>
		
		</table>
		<% } %>
	</div>
	
	
</section>

<%if(editable){ %> 
<form action="<%=request.getContextPath() %>/community/communitytBoardDelete" name="boardDelFrm" method="POST">
	<input type="hidden" name="no" value="<%= article.getArticle_id() %>" />
</form>
<script>
function updateBoard(){
	location.href ="<%=request.getContextPath()%>/community/communityBoardUpdate?no=<%=article.getArticle_id() %>";
}
function deleteBoard(){
	if(confirm("게시글을 정말 삭제하시겠습니까?")){
		$(document.boardDelFrm).submit();
	}
}
</script>
 <%} %> 

<form action="<%=request.getContextPath()%>/community/communityBoardCommentDelete" name="boardCommentDelFrm"　method="POST">
	<input type="hidden" name="comno">
	<input type="hidden" name="BoardNo" value="<%=article.getArticle_id()%>" />
</form>

<script>
$(".btn-delete").click(function(){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.boardCommentDelFrm)
		var boardCommentNo = $(this).val();
		console.log(boardCommentNo);
		$frm.find("[name=comno]").val(boardCommentNo);
		$frm.submit();
	}
});


$(".btn-reply").click(function(){
	<%if(loginMember == null){ %>
	loginAlert();
	<% }%>
	
	//대댓글 작성폼 동적 생성
	var html ="<tr>";
	html +="<td colspan='2' style='display:none; text-align:left;'>";
	html += '<form action="<%=request.getContextPath()%>/community/communityBoardCommentInsert" method="post" name="boardCommentFrm">';
	html += '<input type="hidden" name="boardNo" value="<%=article.getArticle_id() %>" />';
	html += '<input type="hidden" name="writer" value="<%=loginMember != null? loginMember.getMemberId() : "" %>" />';
	html += '<input type="hidden" name="commentLevel" value="2" />'; //댓글번호를 가져온다.
	html += '<input type="hidden" name="commentRef" value="'+$(this).val()+'" />';    
	html += '<textarea name="content" cols="60" rows="2"></textarea>';
	html += '<button type="submit" id="btn-insert">등록</button>';
	html += '</form>';
	html += "</td>";
	html += "<tr>";

	var $trOfBtn = $(this).parent().parent();
	$(html).insertAfter($trOfBtn)
		 .children("td")
		 .slideDown(800);
	//버튼 1회용 처리
	$(this).off("click");
})

$("[name=content]").focus(function(){
	//로그인 여부 검사
	<%if(loginMember == null){ %>
	loginAlert();
	<% }%>
});
$(document).on('submit','[name=boardCommentFrm]', function(e){
//$(document.boardCommentFrm).submit(function(){
	//로그인 여부 검사
	<%if(loginMember == null){ %>
	loginAlert();
	return false;
	<% }%>
							//[name=boardCommentFrm] 밑에 있는 [name=content] 찾아라
	//댓글내용					//context(e.target = 이벤트 발생객체)하위에서 name=content를 찾게됌
	var $content = $("[name=content]",e.target);
	if(/^(.|\n)+$/.test($content.val())==false){
		alert("댓글 내용을 작성하세요");
		$content.focus();
		return false;
	}
})

function loginAlert(){
	alert("로그인 이후 이용하실수 있습니다.");
	$("#memberId").focus();
}
</script>

</body>

</html>