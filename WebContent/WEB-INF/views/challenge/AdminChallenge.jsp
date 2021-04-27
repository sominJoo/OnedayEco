<%@page import="challenge.model.vo.Challenge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">   
<%
	List<Challenge> list = (List<Challenge>)request.getAttribute("list");
	String nowChallenge = (String)request.getAttribute("nowChallenge");
	System.out.println("AdminChallenge.jsp : " + nowChallenge);
	//int nowChallengeId = 0;
	
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/AdminChallenge.css">

</script>
</head>
<body>
<div class="Container-bar">
	<ul>
		<li class="left"><a href="<%=request.getContextPath()%>/challenge/ShortChallenge">하루 챌린지</a></li>
		<li><a href="<%=request.getContextPath()%>/challenge/LongChallenge">기간 챌린지</a></li>
		<li class="Right"><a href="<%=request.getContextPath()%>/challenge/UpdateChallenge">수정</a>
	</ul>
</div>
<div class="box">
<div class="updateChallenge-container">
	<!-- <input class="update" type="button" value="추가" onclick="crudChallenge()"/> -->
	<input class="update" id="add" type="button" value="추가" onclick="location.href='<%= request.getContextPath() %>/challenge/ChallengeForm';"/>
	<% 
		//관리자 일때만 버튼 클릭가능하게 수정.
		//if(MemberService)
		if(nowChallenge.equals("short")){
		if(list != null && !list.isEmpty()) { 
			for(Challenge c : list){
	%>	
			<ul>
				<%-- <li class="li-id"><%= c.getChallenge_id() %></li> --%>
				<li class="li"><%= c.getChallenge_name() %>
					<span class="hidden_id"><%= c.getChallenge_id() %></span>
					<input class="update" type="button" value="삭제" onclick="adminChallenge()"/>
					<!-- <input class="update" type="button" value="삭제" onclick="deleteChallenge()"/> -->
					 | 
					<span class="hidden_id"><%= c.getChallenge_id() %></span>
					<input class="update" type="button" value="수정" onclick="adminChallenge()"/>
					<!--<input class="update" type="button" value="수정" onclick="location.href='<%= request.getContextPath() %>/challenge/ModifyChallenge?no=<%= c.getChallenge_id()%>';"/>-->
					<!-- <input class="update" type="button" value="수정" onclick="location.href='<%= request.getContextPath() %>/challenge/AdminChallenge?no=<%= c.getChallenge_id()%>';"/>-->
					
				</li>
			</ul>
	<% }
		
	 	} else { 
	 %>
			<p>null</p>
	<% } } else { 
		if(list != null && !list.isEmpty()) { 
			for(Challenge c : list){
	%>
<%-- 			<div class="flip"><%= c.getChallenge_name() %></div>
			<div class="panel">
				<p><%=c.getChallenge_info() %></p>
				<p><input class="submit" type="submit" value="도전"></p>
			</div> --%>
			
			<div class="flip">
				<%= c.getChallenge_name() %>
				<span class="hidden_id"><%= c.getChallenge_id() %></span>
				<input class="update" type="button" value="삭제" onclick="adminChallenge()"/>
				 | 
				<span class="hidden_id"><%= c.getChallenge_id() %></span>
				<input class="update" type="button" value="수정" onclick="adminChallenge()"/>
			</div>
			<div class="panel">
				<p>
					<%=c.getChallenge_info() %>
				</p>
			</div>
	<% } } else { %>
		<p>null</p>
	<% }}%>
	
	<div id='pageBar'><%= request.getAttribute("pageBar") %></div>
	</div>
<!-- 보이지 않는 form -->
<!-- 안쓸듯 -->
<form
	action="<%= request.getContextPath() %>/challenge/DeleteChallenge"
	name="delChallengeFrm"
	method="POST">
	<input type="hidden" name="c_id" value=""/>
</form>

<!-- delete, insert 하나로 합치기 -->
<form
	action="<%= request.getContextPath() %>/challenge/AdminChallenge"
	name="adminChallengeFrm"
	method="POST">
	<input type="hidden" name="c_id" value=""/>
	<input type="hidden" name="crud" value=""/>
</form>

<!-- modify : GET-->
<form
	action="<%= request.getContextPath() %>/challenge/ModifyChallenge"
	name="modifyChallengeFrm"
	method="GET"
	>
	<input type="hidden" name="c_id" value=""/>
</form>

<script> 
	$(document).ready(function() {
		$(".flip").click(function() {
			$(this).next('.panel').toggle();
		});
	});
</script>

<script>
function adminChallenge(){
	var btnValue = event.target.value;
	console.log(btnValue, typeof(btnValue));
	
	//
	var nowCId = event.target.previousSibling.previousSibling.innerText * 1;
	
	console.log(nowCId, typeof(nowCId));
	
	//다시 생각
	if(btnValue == "추가"){
		console.log("추가");
		
		var form = document.forms["adminChallengeFrm"];
		form.c_id.value = 0;
		form.crud.value = btnValue;
		form.submit();
		
	}
	else if(btnValue == "삭제") {
		console.log("삭제");
		
		//var nowCId = event.target.previousSibling.previousSibling.innerText * 1;
		console.log(nowCId, typeof(nowCId));
		
		/* 진짜 삭제할건지 물어보기 */
		if(confirm("챌린지를 삭제하시겠습니까?")){ /*진짜 삭제한다면*/
			var form = document.forms["adminChallengeFrm"];
			form.c_id.value = nowCId; /*몇번을 클릭한건지 value값에 넣어주기*/
			console.log("삭제 c_id : " + form.c_id.value);
			form.crud.value = btnValue;
			form.submit();
		}
	}
	else {
		console.log("수정");
		
 		var form = document.forms["modifyChallengeFrm"];
		form.c_id.value = nowCId;
		form.submit();
		
		
	}
}

function deleteChallenge(){
	
	//숫자로 만들어주기 위한 *1
	var i = event.target.previousSibling.previousSibling.innerText * 1;
	var j = event.target;
	 
	console.log(i, typeof(i));
	console.log(j);
	
	<%-- console.log(<%= nowChallengeId %>);
	var form = document.forms["delChallengeFrm"];
	form.c_id.value = i;
	console.log("form : " + form.c_id.value); --%>
	
	/* 진짜 삭제할건지 물어보기 */
	if(confirm("챌린지를 삭제하시겠습니까?")){ /*진짜 삭제한다면*/
		var form = document.forms["delChallengeFrm"];
		form.c_id.value = i; /*몇번을 클릭한건지 value값에 넣어주기*/
		console.log("form : " + form.c_id.value);
		form.submit();
		/*$(document.delChallengeFrm).submit();*/
	}
	
	<%-- '<%= nowChallengeId %>' = i; --%>
}
</script>
</div>
</body>
</html>