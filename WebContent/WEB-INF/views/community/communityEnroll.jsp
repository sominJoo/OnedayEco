<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>커뮤니티 게시판 작성하기</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Community.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file = "/WEB-INF/views/common/header.jsp" %>
<%@ include file = "/WEB-INF/views/common/containerBar.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CommunityEnroll.css" />  
<%-- <%
if(loginMember == null){
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인후 이용이 가능합니다.')");
	script.println("location.href ='/WEB-INF/views/login/login.jsp'");
	script.println("</script>");
}

%> --%>
<script>
//이게 잘 작동할까?
$(document.boardEnrollFrm).submit(boardValidate);
$(window).load(function() {
	console.log("된다.")
	if(loginMember == null)
		alert("로그인 이후 이용하실수 있습니다.");
		location.href="<%=request.getContextPath()%>/login/login"; 
		});
/**
name값은 document로 직접참조 가능
* boardEnrollFrm 유효성 검사
*/

function boardValidate(){
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
	return true;
	
	};
	
function loginAlert() {
	if(loginMember == null)
		alert("로그인 이후 이용하실수 있습니다.");
		location.href="<%=request.getContextPath()%>/login/login"; 
		};
		
</script>

<section id="board-container">

<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/community/communityBoardEnroll" 
	method="post"
	enctype="multipart/form-data">

	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" id="title" name="title" required></td>
		<select name="type" id="type">
			<option value="커뮤니티">커뮤니티</option>
			<option value="공지사항">공지사항</option>
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
