<%@page import="board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% 
    Board board = (Board)request.getAttribute("board");
	%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>인기게시물 페이지  </title>
    <link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/boardList.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<%@ include file ="/WEB-INF/views/common/header.jsp" %>
	<h2>게시판 </h2>
<table id="tbl-board-view">
	<tr>
		<th>글번호</th>
		<td><%=board.getAid() %></td>
	</tr>
	<tr>
	<th>아이디</th>
	 <td><%=board.getMemberId() %></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td><%= board.getAtype() %></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><a href="<%=request.getContextPath()%>/board/boardCount?count="></a></td>
	</tr>
	<tr>
		<th>본문</th>
		<td><%=board.getContent() %></td>
	</tr>
	<tr>
		<th>날짜</th>
		<td><%=board.getRegDate() %></td>
	</tr>
	<tr>
	<!-- 조회수  카운트  -->
		<th>조회수</th>
		<td><%=board.getRedCount() %></td>
		</td>
	</tr>

</table>