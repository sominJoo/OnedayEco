<%@page import="onedayeco.confirm.model.service.ConfirmService"%>
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
<%

 boolean editable =		 MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole());		//관리자면 true, 아니면 false


%>

<section id="board-container">
 <% if(loginMember != null) %>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath() %>/community/confirmForm';" />
	<select name="category" id="category" onchange="selectcate()">
		<option value="선택">선택</option>
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
			<%if(editable){ %>
			<th>인증확인</th>
			<%} %>
			
		</tr>
	<%if(list != null && !list.isEmpty()) {%>
		<%for(Confirm c: list){	%>
					<tr>
					<td id="boardNo"><%=c.getA_id()%> </td>
					<td>
						<a href="<%=request.getContextPath() %>/community/confirmView?no=<%=c.getA_id() %>"><%=c.getA_title() %></a>
					</td>
					<td><%=c.getUser_id() == null ? "" : c.getUser_id()  %></td>
					<td><%=c.getA_content() %></td>
					<td>
						<%-- <%if(c.getAttach()!=null && c.getAttach().isAttachment_status()==true){ %> --%>
						<img src="<%= request.getContextPath()%>/image/file-storage.png" width="16px"/>
					<%-- 	<%} %> --%>
					</td>
					<td><%=c.getA_like()%></td>
					<td><%=c.getA_read_count()%></td>
					<%if(editable){ %>
						<td>
							<% if(ConfirmService.POINT_CHECK_N.equals(c.getPoint_check())) { %> 
							<input id="finish" type="button" value="인증 확인 " data-board="<%=c.getA_id()%>" data-user="<%=c.getUser_id()%>" data-type ="<%=c.getConfirm_type()%>" onclick="finishChallenge(event)"/>
							<%} else {%>
							<span style="color: gray">완료</span>
							<%} %>
						</td>
					<%} %>
				</tr> 
				</div>
	<% 	
			}
		} else {%>
		<tr>
			<td colspan="7" style="text-align:'center'">
		</tr>	
	<% } %>
	</table>

	<div id='pageBar'><%=request.getAttribute("pageBar") %></div>
</section>
</body>
<script>
function selectcate(){
	var $cate = $("#category").val();
	console.log($cate);
	if($cate == "하루"){
		location.href ="<%=request.getContextPath()%>/community/confirmDayList"
	}
	if($cate == ("팀")){
		location.href ="<%=request.getContextPath()%>/community/confirmTeamList"
	}
	
	if($cate == ("기간")){
		location.href ="<%=request.getContextPath()%>/community/confirmTermList"
	}
}

//인증 완료
function finishChallenge(event){
	var $ele = $(event.target);
	var $ele_parent = $ele.parent();
	var $boardNum = $ele.attr('data-board');
	var $user = $ele.attr('data-user');
	var $type = $ele.attr('data-type');
	
	
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/confirmPointServlet?boardNo="+$boardNum +"&user="+$user+"$type="+$type,
		method : "get",
		success : function(data){
			//포인트 지급 완료 시 속성,컬러 변경
			$ele.remove();
			$ele_parent.append('<span style="color: gray">완료</span>');
		},
		error : function (xhr, status,err) {
			alert("지급 불가");
		}
	});	
}
</script>
</html>