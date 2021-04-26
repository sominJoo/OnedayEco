<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.model.vo.MypageChallenge"%>
<%@page import="onedayeco.confirm.model.service.ConfirmService"%>
<%@page import="onedayeco.confirm.model.vo.ConfirmComment"%>
<%@page import="onedayeco.confirm.model.vo.Confirm"%>
<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>인증게시판 상세보기</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ConfirmView.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>

<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>
<%
	Confirm confirm= (Confirm)request.getAttribute("confirm");
	SimpleDateFormat format1 = new SimpleDateFormat("yy/MM/dd");
	Date time = new Date();
	String time1 = format1.format(time);
	System.out.println("현재날짜"+time1);
	 boolean editable = loginMember != null &&loginMember.getMemberId().equals(confirm.getUser_id());
	 boolean editable_admin =  MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole());
	List<ConfirmComment> commentList = (List<ConfirmComment>)request.getAttribute("commentList");
	List<MypageChallenge> mChallengeList = (List<MypageChallenge>)request.getAttribute("mChallengeList");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ConfirmView.css" />

<section id="board-container">

	<table id="tbl-board-view">
		<tr>
			<th>No.</th>
			<td><%=confirm.getA_id() %></td>
			<th>제 목</th>
			<td><%=confirm.getA_title() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=confirm.getUser_id() %></td>
			<th>작성일</th>
			<td><%=confirm.getA_reg_date() %></td>
		</tr>
		<tr>
			<th>좋아요</th>
			<td><img src="<%=request.getContextPath() %>/image/heart.png" width="16px" /><%=confirm.getA_like() %></td>
			<th>조회수</th>
			<td><%=confirm.getA_read_count() %></td>
		</tr>
		<tr>
			<th>챌린지</th>
			<td colspan="4">
				<%=confirm.getChallengeName() == null ? "" :  confirm.getChallengeName()%>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td colspan="4"><%=confirm.getA_content() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="4">
				<!-- 아래 구문이 없다면 nullpointexception떨어짐  -->
				<% 	if(confirm.getAttach() != null){ %>
			<!-- 	첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 -->
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%=request.getContextPath()%>/community/CfileDownload?no=<%= confirm.getAttach().getA_id() %>"><%= confirm.getAttach().getOriginal_filename()%></a>
				<% } %>
			</td>
		</tr>
		<tr colspan="4" style="border-bottom: none">
 		<% if(editable){%>
		<!-- 	작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 -->
			<th colspan="4">
				<input id="delbtn" type="button" value="삭제하기" onclick="deleteBoard()">
				<input class="changebtn" type="button" value="수정하기" onclick="updateBoard()">
			</th>
		<%} %> 
		</tr>
		<% if(editable_admin){%>
		<!-- 관리자만 마지막행 삭제/인증버튼이 보일수 있게 할 것 -->
		<th colspan="4">
			<input id="delbtn" type="button" value="삭제하기" onclick="deleteBoard()">
			<% if(ConfirmService.POINT_CHECK_N.equals(confirm.getPoint_check())) { %>
			<input id="cofirm_do" class="changebtn" type="button" value="인증 확인 " data-board="<%=confirm.getA_id()%>" data-user="<%=confirm.getUser_id()%>" onclick="finishChallenge(event)" style="color:red;"/>
			<%} else {%>
			<input class="changebtn" type="button" value="완료 " style="color:gray;"/>
			<%} %>		
			<% 
				SimpleDateFormat dateFormat=new SimpleDateFormat("yy/MM/dd");
				for(MypageChallenge m : mChallengeList){
					String sdf = new SimpleDateFormat("yy/MM/dd").format(m.getEndDate());
					Date endDate= dateFormat.parse(sdf);
					Date nowDate= dateFormat.parse(time1);
					int compare = nowDate.compareTo(endDate);
					System.out.println(".getChallengeName() : "+m.getChallengeName());
					System.out.println("confirm.getChallengeName() : "+confirm.getChallengeName());
			%>
				<% if(compare<=0 && m.getChallengeName().equals(confirm.getChallengeName())) { %>
					<input id="badgebtn" class="changebtn" type="button" value="뱃지 지급 " 
						data-board="<%=confirm.getA_id()%>" 
						data-user="<%=confirm.getUser_id()%>" 
						data-type ="<%=confirm.getConfirm_type()%>"
						onclick="badge(event)"  
						style="color:blue;"/>
				<%}%>	
			<%}%>	
		</th>
		<%} %> 
		</tr>
	</table>
	
	<hr style="margin-top:30px;" />	
	
	<div class="comment-container">
        <div class="comment-editor">
            <form action="<%=request.getContextPath()%>/community/confirmCommentInsert" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%=confirm.getA_id() %>" />
                <input type="hidden" name="writer" value="<%=loginMember != null? loginMember.getMemberId() : "" %>" /> 
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-insert">등록</button>
            </form>
        </div>
		<!--table#tbl-comment-->
		<%if(commentList != null && !commentList.isEmpty()) {%>
		
		<table id="tbl-comment">
		 <%
			for(ConfirmComment cc : commentList){
				boolean removable =
						loginMember != null &&
						(loginMember.getMemberId().equals(cc.getUser_id())
						 || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
						 );
				if(cc.getComment_level()== 1){
					//댓글
	%>				 
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= cc.getUser_id() %></sub>
						<sub class="comment-date"><%=cc.getComment_reg_date()%></sub>
						<br />
						<%= cc.getComment_content()%>
					</td>
					<td>
						<button class="btn-reply" value="<%=cc.getComment_id() %>"> 답글 </button>
						<%if(removable){%> 
						<button class="btn-delete" value="<%=cc.getComment_id() %>"> 삭제 </button>
						<%} %>
					</td>
				</tr>
		<% 		}else{
					//대댓글
		%>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%=cc.getUser_id() %></sub>
						<sub class="comment-date"><%=cc.getComment_reg_date() %></sub>
						<br />
						<%= cc.getComment_content() %>
					</td>
					<td>
						<%if(removable){%> 
						<button class="btn-delete" value="<%=cc.getComment_id() %>"> 삭제 </button>
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
<form action="<%=request.getContextPath() %>/community/confirmDelete" name="boardDelFrm" method="POST">
	<input type="hidden" name="no" value="<%= confirm.getA_id() %>" />
</form>
<script>
function updateBoard(){
	location.href ="<%=request.getContextPath()%>/community/confirmUpdate?no=<%=confirm.getA_id() %>";
}
function deleteBoard(){
	if(confirm("게시글을 정말 삭제하시겠습니까?")){
		$(document.boardDelFrm).submit();
	}
}
</script>
 <%} %> 

<form action="<%=request.getContextPath()%>/community/confirmCommentDelete" name="boardCommentDelFrm"　method="POST">
	<input type="hidden" name="comno" />
	<input type="hidden" name="BoardNo" value="<%=confirm.getA_id()%>" />
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
	html += '<form action="<%=request.getContextPath()%>/community/confirmCommentInsert" method="post" name="boardCommentFrm">';
	html += '<input type="hidden" name="boardNo" value="<%=confirm.getA_id() %>" />';
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

//인증 완료
function finishChallenge(event){
	var $ele = $(event.target);
	var $boardNum = $ele.attr('data-board');
	var $user = $ele.attr('data-user');
	var $type = $ele.attr('data-type');
	
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/confirmPointServlet?boardNo="+$boardNum +"&user="+$user+"&type="+$type,
		method : "get",
		success : function(data){
			//포인트 지급 완료 시 속성,컬러 변경
			$ele.remove();
			$ele_parent.append('<span style="color: gray">완료</span>');
		},
		error : function (xhr, status,err) {
			
			console.log(xhr, status,err);
		}
	});	
}

//뱃지 지급 인증 완료
function badge(event){
	var $ele = $(event.target);
	var $ele_parent = $ele.parent();
	var $boardNum = $ele.attr('data-board');
	var $user = $ele.attr('data-user');
	if ( $("#cofirm_do").length > 0 ) { 
		alert("인증 먼저 해주세요");
		return false;
	}
	<%if(confirm.getConfirm_type().equals("하루")){%>
	return false;
	<%}%>
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/badgeInsert?boardNo="+ $boardNum +"&user="+$user,
		method : "get",
		success : function(data){
			//뱃지 지급 완료 시 속성,컬러 변경dksl
			$ele.remove();
			$ele_parent.append('<input id="changebtn" type="button" value="완료 " style="color:gray;"/>');
		},
		error : function (xhr, status,err) {
			console.log(xhr, status,err);
		}
	});	
}
</script>

</body>

</html>