<%@page import="onedayeco.confirm.model.vo.Confirm"%>
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
    <title>인증게시판 수정</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ConfirmUpdate.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
 function attachFile(){
	 var $check = $("delFile");
	 //첨부파일 없는 경우 제출 불가능
	 if($check.is(":checked") == false){
		 alert("파일을 반드시 첨부해야합니다.");
		 return false;
	 }
	 
 }
</script>
<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>

<%
	Confirm confirm= (Confirm)request.getAttribute("confirm");
%>
<form name="boardUpdateFrm" action="<%=request.getContextPath() %>/community/confirmUpdate"
enctype="multipart/form-data"
 method="post">
<section id="board-container">
	<input type="hidden" name="no" value="<%=confirm.getA_id()%>"/>
	<table id="tbl-board-view">

	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%=confirm.getA_title() %>" required></td>
			<select name="type" id="category" onchange="selectcate()">
				<option value="선택">선택</option>
				<option name="type" value="하루">하루</option>
				<option name="type" value="기간">기간</option>
				<option name="type" value="팀">팀</option>
			</select>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%=confirm.getUser_id() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td >
		<%-- input:file의 value속성은 보안성을 이유로 임의변경할 수 없다. 
			<%=board.getAttach() != null ? board.getAttach().getOrginalFileName() : ""%>--%>
			<input type="file" name="upFile" value="">
			<% if(confirm.getAttach() != null) { %>			
			<p style ="margin: 5px 0;">
				<img src="<%=request.getContextPath() %>/images/file.png" width="16px" />
				<%=confirm.getAttach().getOriginal_filename() %>
			</p>
			<% } %>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"><%=confirm.getA_content() %></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="수정하기"/>
			<input type="button" value="취소" onclick="history.go(-1);"/>
		</th>
	</tr>
</table>
</form>
</section>
<script>
$("[name=upFile]").change(function(){
	console.log($(this).val());
	if($(this).val() != ""){ //val() 소괄호 빼먹지 않기
		//파일선택
		console.log("파일선택")
		$("#delFile").prop("checked",true) //delFile이름으로 attach넘버가 넘어 간다.
					 .on('click',function(){
						 return false; //체크표시 풀지 못하도록 이벤트 핸들러 적용(붙이기)
					 });
	}
	else{
		//파일 선택 취소
		console.log("파일선택 취소")
		$("#delFile")
					 .prop("checked",false)
					 .off('click'); //이벤트 핸들러 제거
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
</body>
</html>