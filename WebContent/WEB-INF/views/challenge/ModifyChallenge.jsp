<%@page import="challenge.model.vo.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">  
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Challenge challenge = (Challenge) request.getAttribute("challenge");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ModifyChallenge.css">

<div id="modifychallengeform-container">
<h2 class="h2">챌린지 수정</h2>
<form
	action="<%= request.getContextPath() %>/challenge/ModifyChallenge"
	name="modifyChallengeFrm"
	method="post">
	
	<input type="hidden" name="crud" value="수정"/>
	<input type="hidden" name="c_id" value="<%= challenge.getChallenge_id()%>"/>
	<table id="tbl-modify-challenge">
	<tr>
		<th>챌린지 이름</th>
		<td>
			<input id="name" type="text" name="title" value="<%= challenge.getChallenge_name() %>" required>
		</td>
	</tr>
	<tr>
		<th>챌린지 강도</th>
		<td>
			<input type="number" name="level" min="1" max="3" value="<%= challenge.getChallenge_level() %>" requried>
			<span>(하:1, 중:2, 상:3)</span>
		</td>
	</tr>
	<tr>
		<td>챌린지 설명</td>
		<td>
			<textarea id="content" name="content" required>
				<%= challenge.getChallenge_info() %>
			</textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input class="submit" type="submit" value="수정">
		</th>
	</tr>
	</table>

</form>
</div>

<script>
$(function(){
	$(document.modifyChallengeFrm).submit(challengeValidate);	
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