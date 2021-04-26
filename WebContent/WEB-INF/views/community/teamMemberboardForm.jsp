<%@page import="java.util.ArrayList"%>
<%@page import="community.MemberBoard.model.vo.Challenge"%>
<%@page import="java.util.List"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/MemberboardForm.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<script>
$(function(){
	$(document.memberboardEnrollFrm).submit(boardValidate);	
	
});
/**
* boardEnrollFrm 유효성 검사
*/
function boardValidate(){
	var $title = $("[name=title]");
	var $content = $("[name=content]");
	if(/^.+$/.test($title.val()) == false){
		alert("제목을 입력하세요.");
		return false;
	}
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("내용을 입력하세요.");
		return false;
	}
	if ($('[name=upFile]')[0].files.length === 0) {
		confirm("사진을 등록하지 않았습니다. 이대로 제출하시겠습니까?");
	}
	return true;
}

</script>
<div id="table_container">
<section id="board-container">
<form
	name="memberboardEnrollFrm"
	action="<%=request.getContextPath() %>/community/teamMemberboardEnroll" 
	method="post"
	enctype="multipart/form-data"
	>
	<h2>정보공유 게시글 작성</h2>
	<table id="tbl-board-view">
		<tr>
		<th class="column" >
			<div class="td-container1">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</div>
		</th>
		<td>
		<input type="text" name="title" class="td td2" placeholder="제목을 입력해주세요"/>		  
		</td>
		</tr>
	<tr>
		<th>
		<div class="td-container1">작 성 자</div>
		</th>
		<td>
			<input type="text" name="writer" value="<%= loginMember.getMemberId() %>" class="td td2" readonly/>
		</td>
	</tr>
		<th>
		<div class="td-container1 td1-2">첨부파일</div>
		</th>
		<td>
			<div class="file_container">
			<input type="file" name="upFile" id="upFile" accept=".jpg,.jpeg,.png,.gif,.bmp" onchange="showImg();"><br>			
			</div>
			<div id="img_container">			
			<img id="example_img" src="../image/커뮤니티/인증사진2.png" alt="" >
			</div>
		</td>
	</tr>
	<tr>
		<th>
		<div class="td-container1 td1-3">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</div>
		</th>
		<td><textarea name="content" class="td2-1" placeholder="환경보호와 관련된 정보에 대해 소개해주세요"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input class="btn" name="submit" type="submit" value="등록하기">
		</th>
	</tr>
</table>
</form>
</section>
</div>
<script>
function showImg() {
    // 파일리더 생성 
    var showImg = new FileReader();
    showImg.onload = function (e) {
    // img id 값 
    document.getElementById("example_img").src = e.target.result;
	};
// input id 값 
showImg.readAsDataURL(document.getElementById("upFile").files[0]);
};
</script>


