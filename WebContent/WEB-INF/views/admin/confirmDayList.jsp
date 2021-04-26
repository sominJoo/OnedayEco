<%@page import="onedayeco.confirm.model.vo.Confirm"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 
 	List<Confirm> list = (List<Confirm>)request.getAttribute("list");
 
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>인증게시판 </title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ConfirmList.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">


<%@ include file ="/WEB-INF/views/common/header.jsp" %>
<%@ include file ="/WEB-INF/views/common/containerBar.jsp" %>

<section id="board-container">
 <% if(loginMember != null) %>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath() %>/community/confirmForm';" />
	<select name="category" id="category" onchange="selectcate()">
		<option name="type" value="선택">선택</option>
		<option name="type" value="하루">하루</option>
		<option name="type" value="기간">기간</option>
		<option name="type" value="팀">팀</option>
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
	<% 		for(Confirm c: list){
				if(c.getConfirm_type().equals("하루")){
	%>
					<tr>
					<td><%=c.getA_id()%> </td>
					<td>
						<a href="<%=request.getContextPath() %>/community/confirmView?no=<%=c.getA_id() %>"><%=c.getA_title() %></a>
					</td>
					<td><%=c.getUser_id() %></td>
					<td><%=c.getA_content() %></td>
					<td>
						<%-- <%if(c.getAttach()!=null && c.getAttach().isAttachment_status()==true){ %> --%>
						<img src="<%= request.getContextPath()%>/image/file-storage.png" width="16px"/>
					<%-- 	<%} %> --%>
					</td>
					<td><%=c.getA_like()%></td>
					<td><%=c.getA_read_count()%></td>
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
	if($cate == "하루"){
		location.href ="<%=request.getContextPath()%>/admin/confirmDayList"
	}
	if($cate == ("팀")){
		location.href ="<%=request.getContextPath()%>/admin/confirmTeamList"
	}
	
	if($cate == ("기간")){
		location.href ="<%=request.getContextPath()%>/admin/confirmTermList"
	}
	
}
</script>
</body>
</html>