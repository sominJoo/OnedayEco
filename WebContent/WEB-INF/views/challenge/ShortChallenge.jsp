<%@page import="member.model.service.MemberService"%>
<%@page import="challenge.model.vo.Challenge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Challenge.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file ="/WEB-INF/views/common/header.jsp" %>

<%
	List<Challenge> list = (List<Challenge>)request.getAttribute("list");

%>
<script>
<%-- <% if(msg != null) { %> 
	alert("<%= msg %>"); 
<% } %> --%>
</script>
</head>
<body>
<div class="Container-bar">
	<ul>
		<li class="left"><a href="<%=request.getContextPath()%>/challenge/ShortChallenge">하루 챌린지</a></li>
		<li><a href="<%=request.getContextPath()%>/challenge/LongChallenge">기간 챌린지</a></li>
		<%-- 관리자 로그인시에만 관리자 메뉴 노출 --%>
		<% if(loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())) { %>
		<li class="Right"><a href="<%=request.getContextPath()%>/challenge/UpdateChallenge">수정</a>
		<% } %>
	</ul>
</div>
<div class="box">
<div id="shortchallenge-container">
<form
	action="<%= request.getContextPath() %>/challenge/JoinChallenge"
	name="joinChallengeFrm"
	method="POST">
	<input type="hidden" name="c_id" value=""/>
	<% 
		if(list != null && !list.isEmpty()) { 
			for(Challenge c : list){
	%>	
			<ul>
				<li class="li">
					<span>*</span>
					<%= c.getChallenge_name() %>
					<span class="hidden_id"><%= c.getChallenge_id() %></span>
					<% if(loginMember != null) { %>
					<input class="join" type="button" value="도전" onclick="joinChallenge()"/>
					<input type="hidden" name="member_id" value="<%= loginMember.getMemberId()%>"/>
				</li>
			</ul>
	<% 
			}
		
	 	} } else { 
	 %>
		
	<% } %>
	
	<div id='pageBar'><%= request.getAttribute("pageBar") %></div>
	</div>
<!-- 보이지 않는 form -->
<%-- <form
	action="<%= request.getContextPath() %>/challenge/JoinChallenge"
	name="joinChallengeFrm"
	method="POST">
	<input type="hidden" name="c_id" value=""/>
	<input type="hidden" name="member_id" value="<%= loginMember.getMemberId()%>"/>
</form> --%>

<script>
function joinChallenge(){
	
	//숫자로 만들어주기 위한 *1
	var i = event.target.previousSibling.previousSibling.innerText * 1;
	var j = event.target;
	 
	console.log(i, typeof(i));
	console.log(j);

	//진짜 도전할건지 물어보기
	if(confirm("도전하시겠습니까?")){
		var form = document.forms["joinChallengeFrm"];
		form.c_id.value = i; /*몇번을 클릭한건지 value값에 넣어주기*/
		console.log("form : " + form.c_id.value);
		console.log("form.member_id.value : " + form.member_id.value);
		form.submit();
	}
	
}
</script>
</div>
</body>
</html>