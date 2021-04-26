<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="onedayeco.article.model.vo.Article"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
	
	List<Article> list = (List<Article>) request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>커뮤니티게시판 </title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/CommunityBoard.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">


<%@ include file ="/WEB-INF/views/common/header.jsp" %>

  <% if(loginMember != null) %>

	
<%@include file ="/WEB-INF/views/common/containerBar.jsp" %>

<section id="board-container">
 <% if(loginMember != null) %>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath() %>/community/communityForm';" />
	<select name="category" id="category" onchange="selectcate()">
		<option value="선택">선택</option>
		<option id="community" name="type" value="커뮤니티">커뮤니티</option>
		<option id ="notice"  name="type" value="공지사항">공지사항</option>
	</select>
	
	
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>첨부파일</th>
			<th> <img src="<%= request.getContextPath()%>/image/heart.png" width="16px"/> </th>
			<th>조회수</th>
		</tr>
	<%
		if(list != null && !list.isEmpty()) {%>
	<% 		for(Article a: list){
				if(a.getArticle_type().equals("커뮤니티")){
	%>
					<tr>
					<td><%=a.getArticle_id()%> </td>
					<td>
						<a href="<%=request.getContextPath() %>/community/communityBoardView?no=<%=a.getArticle_id() %>"><%=a.getArticle_title() %></a>
					</td>
					<td><%=a.getUser_id() %></td>
					<td><%=a.getArticle_content() %></td>
					<td>
						<%if(a.getAttach()!=null && a.getAttach().isAttachment_status()==true){ %>
						<img src="<%= request.getContextPath()%>/image/file-storage.png" width="16px"/>
						<%} %>
					</td>
					<td><%=a.getArticle_like()%></td>
					<td><%=a.getArticle_read_count()%></td>
				</tr> 
				</div>
	<% 	
				}
			}
		} else {%>
		<tr>
			<td colspan="7" style="text-align:'center'">
		</tr>	
	<% } %>
	</table>

	<div id='pageBar'><%=request.getAttribute("pageBar") %></div>
</section>
<script>
function selectcate(){
	console.log("실행");
	var $cate = $("#category").val();
	console.log($cate);
	if($cate == "커뮤니티"){
		/* $("category").val('커뮤니티')prop("selected",true); */
		location.href ="<%=request.getContextPath()%>/community/communityCommunityList"
	}
	if($cate == ("공지사항")){
/* 		$("category").val('공지사항').prop("selected",true); */
		location.href ="<%=request.getContextPath()%>/community/communtiyNoticeList"
	}
	
}
</script>
</body>
</html>