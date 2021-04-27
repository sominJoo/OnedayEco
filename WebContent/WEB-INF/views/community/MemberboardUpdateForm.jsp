<%@page import="java.util.ArrayList"%>
<%@page import="community.MemberBoard.model.vo.Challenge"%>
<%@page import="java.util.List"%>
<%@page import="community.MemberBoard.model.vo.Memberboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/MemberboardForm.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<%
	Memberboard memberboard = (Memberboard)request.getAttribute("memberboard");
	List<Memberboard> list = (List<Memberboard>)request.getAttribute("list");
%>
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
	return true;
}

</script>
<div id="table_container">
<section id="board-container">
<form
	name="memberboardEnrollFrm"
	action="<%=request.getContextPath()%>/community/memberboardUpdate" 
	method="post"
	enctype="multipart/form-data"
	>
	<input type="hidden" name="a_id" value="<%=memberboard.getaId()%>"/>
	<h2>팀원모집 게시글 수정</h2>
	<table id="tbl-board-view">
		<tr>
		<th class="column" >
			<div class="td-container1">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</div>
		</th>
		<td>
		
		  <select name="challenge_id" class="td td2" required>
		<%
			for(Memberboard mb : list){ 
				if(mb.getChallenge().getChallengeName().equals(memberboard.getChallenge().getChallengeName())){
		%>
			<option value="<%= mb.getChallenge().getChallengeId() %>" selected><%= mb.getChallenge().getChallengeName() %></option>
		<%
		}
		else if("L".equals(mb.getChallenge().getChallengeTermType())){
		%>
			<option value="<%= mb.getChallenge().getChallengeId() %>"><%= mb.getChallenge().getChallengeName() %></option>
		<% }} %>
		</select>
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
		<tr>
		<th>
		<div class="td-container1">모집인원</div>
		</th>
		<td>
			<div class="number-container" style="text-align:center;">
			최소 <input type="text" value="4" class="td" style="width:130px" readonly/> 명 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			최대 <input type="number" name="s_team_count" min="4" max="10" class="td" placeholder="<%= memberboard.getsTeamCount() %>" style="width:130px"/> 명			
			</div>
		</td>
	</tr>
	<tr>
		<th>
		<div class="td-container1 td1-2">첨부파일</div>
		</th>
		<td>
			<div class="file_container">
				<p style="margin:5px 12px;">본파일 : <%= memberboard.getMemberboardAttachment().getOriginalFilename() %>
				<!-- 삭제 체크박스
					체크했다면 value로 attachment의 고유번호 -->
				<input type="checkbox" name="delFile" id="delFile" value="<%= memberboard.getMemberboardAttachment().getAttachmentId()%>"/> 
				<label for="delFile">삭제</label>
			</p>	
			<input type="file" name="upFile" id="upFile" accept=".jpg,.jpeg,.png,.gif,.bmp" onchange="showImg();"><br>		
			</div>
			<div id="img_container">			
				<img id="example_img"
					src="<%=request.getContextPath() %>/upload/memberboard/<%= memberboard.getMemberboardAttachment().getRenamedFilename() %>" alt="" >
			</div>
 			<input type="hidden" name="delFile" id="delFile" value="<%= memberboard.getMemberboardAttachment().getAttachmentId() %>"/>
		</td>
	</tr>
	<tr>
		<th>
		<div class="td-container1 td1-3">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</div>
		</th>
		<td><textarea name="content" class="td2-1"><%= memberboard.getaContent() %></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input class="btn" name="submit" type="submit" value="수정완료">
		</th>
	</tr>
</table>
</form>
</section>
</div>
<script>
function showImg() {
	if ($('[name=upFile]')[0].files.length === 0) {
		document.getElementById("example_img").src = "<%=request.getContextPath() %>/upload/memberboard/<%= memberboard.getMemberboardAttachment().getRenamedFilename() %>";
	}
    // 파일리더 생성 
    var showImg = new FileReader();
    showImg.onload = function (e) {
    // img id 값 
    document.getElementById("example_img").src = e.target.result;
	};
// input id 값 
showImg.readAsDataURL(document.getElementById("upFile").files[0]);
};

/* 파일 선택 변경시 나타나는 이벤트 */
$("[name=upFile]").change(function(){
	console.log($(this).val()); // 파일 선택 - 파일명, 선택 x - 빈문자열
	if($(this).val() != ""){
		// 파일 변경 -> 체크
		$('#delFile')
			.prop("checked", true)
			// 파일이 변경된다면, check박스를 누르지 못하도록
			// 클릭했을 때 함수로 return false를 걸어주면 클릭이 작동하지 않게 비활성화
			.on('click', function(){
				return false;
			});
	} else {
		// 파일을 변경하려다가 다시 파일 선택 취소 -> 체크 해제
		$('#delFile').prop("checked", false)
			// 취소하면 다시 정상적으로 작동할 수 있도록 click했을 때의 이벤트핸들러 제거
			// cf. on (이벤트 핸들러를 추가) <-> off (이벤트 핸들러 제거)
			.off('click');
	}
});
$(document.boardUpdateFrm).submit(function (){

	var $content = $("[name=content]");
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("내용을 입력하세요");
		return false;
	}
	return true;
});
</script>
