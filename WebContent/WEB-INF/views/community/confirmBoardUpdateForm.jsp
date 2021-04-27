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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ConfirmUpdateForm.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>

<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>

<%
	Article article= (Article)request.getAttribute("article");
%>
<form name="boardUpdateFrm" action="<%=request.getContextPath() %>/community/communityBoardUpdate"
enctype="multipart/form-data"
 method="post">
<section id="board-container">
	<input type="hidden" name="no" value="<%=article.getArticle_id()%>"/>
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%=article.getArticle_title() %>" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%=article.getUser_id() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td >
		<%-- input:file의 value속성은 보안성을 이유로 임의변경할 수 없다. 
			<%=board.getAttach() != null ? board.getAttach().getOrginalFileName() : ""%>--%>
			<input type="file" name="upFile" value="">
			<% if(article.getAttach() != null) { %>			
			<p style ="margin: 5px 0;">
				<img src="<%=request.getContextPath() %>/images/file.png" width="16px" />
				<%=article.getAttach().getOriginal_filename() %>
				<input type="checkbox" name ="delFile" id ="delFile" value="<%=article.getAttach().getAttachment_id() %>" />
				<label for="delFile">삭제</label>
			</p>
			<% } %>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"><%=article.getArticle_content() %></textarea></td>
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