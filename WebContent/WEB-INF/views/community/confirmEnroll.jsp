<%@page import="mypage.model.vo.MypageChallenge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	List<MypageChallenge> mChallengeList =  (List<MypageChallenge>)request.getAttribute("mChallengeList");
	for(MypageChallenge m : mChallengeList)
		System.out.println("mChallengeList@confimEnroll : "+m);
	
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>인증 게시판 작성하기</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ConfirmEnroll.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>
<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>

<section id="board-container">
<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/community/confirmEnroll" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">

	<tr>
		<th>제 목</th>
		<td><input type="text" id="title" name="title" required></td>
		<select name="type" id="type">
			<option value="">타입 선택</option>
			<option value="하루">하루</option>
			<option value="기간">기간</option>
			<option value="팀">팀</option>
		</select>
		
		<select name="challengeName" id="challengeName" class="selector">
    		<option value="">인증 챌린지 선택</option>
    		<% if(mChallengeList != null) {%>
    			<%for(MypageChallenge mc : mChallengeList){ %>
				<option value="<%= mc.getChallengeName()%>"><%= mc.getChallengeName()%></option>
    			<% }%>
   			<% } else{%>
				<option value="">-----</option>
   			<% }%>
		</select>
		
	</tr>
	<tr>
		<th>작성자</th>
	 	<td>
			<input type="text" name="writer" value="<%=loginMember.getMemberId() %>" readonly style="border:none"/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>			
			<input type="file" name="upFile"> 
			<input type="checkbox" id="checkFile" style="display:none;"/>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" id="submit" value="등록하기">
		</th>
	</tr>
</table>
</form>
</section>

<script>
$("[name=upFile]").change(function(){
	console.log($(this).val());
	if($(this).val() != ""){
		//파일 선택
		$("#checkFile")
			.prop("checked", true)
			.on('click', function(){
				return false;
			});
					
	}
	else {
		//파일 선택 취소
		$("#checkFile")
			.prop("checked", false)
			.off('click');
	}
});


$(document.boardEnrollFrm).submit(function(){
	 var $check = $("#checkFile");
	 console.log($check);
	 //첨부파일 없는 경우 제출 불가능
	 if($check.is(":checked") == false){
		 alert("파일을 반드시 첨부해야합니다.");
		 return false;
	}
	
	 
	var $title = $("[name = title]");
	var $content = $("[name = content]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(/^.+$/.test($title.val()) == false){
		alert("제목을 입력하세요");
		return false;
	}
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("내용을 입력하세요");
		return false;
	}
	
	
	var type = $("#challengeName");
	var challenge = $("#challengeName option:selected").val();
	if(challenge=="" || challenge == null)	{
		alert("인증할 챌린지를 선택하세요");
		return false;
	}
	var type1 = $("#type");
	var challenge = $("#type option:selected").val();
	if(challenge=="" || challenge == null)	{
		alert("챌린지 타입을 선택하세요");
		return false;
	}
	return true;
})
</script>
