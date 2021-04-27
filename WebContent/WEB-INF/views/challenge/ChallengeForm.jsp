<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">   
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ChallengeForm.css">

<div id="challengeform-container">
<form
	action="<%= request.getContextPath() %>/challenge/AdminChallenge"
	name="insertChallengeFrm"
	method="post"
	>
	<input type="hidden" name="crud" value="추가"/>
	<table id="tbl-insert-challenge">
	<tr>
		<th>챌린지 이름</th>
		<td><input id="name" type="text" name="title" required></td>
	</tr>
	<tr>
		<th>챌린지 강도</th>
		<td>
			<input type="number" name="level" min="1" max="3" requried>
			<span>(하:1, 중:2, 상:3)</span>
		</td>
	</tr>
	<tr>
		<td>챌린지 설명</td>
		<td><textarea id="content" name="content" required></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input   class ="submit"type="submit" value="등록">
		</th>
	</tr>
	</table>

</form>
</div>

<script>
$(function(){
	$(document.insertChallengeFrm).submit(challengeValidate);	
});

//유효성 검사
function challengeValidate(){
	var $title = $("[name=title]");
	var $content = $("[name=content]");
	var $level = $("[name=level]");
	//제목 작성하지 않은 경우 제출 불가
	if(/^.+$/.test($title.val()) == false){
		alert("챌린지 이름을 입력하세요.");
		return false;
	}
	
	//내용 작성하지 않은 경우 제출 불가
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("챌린지 설명을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>
</body>
</html>