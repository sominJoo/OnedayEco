<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String checkId = request.getParameter("checkId");
	System.out.println("checkId@checkPopup = "+checkId);
	boolean available = (boolean)request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#duplicated{color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
	<% if(available){ %>
		<%-- 아이디를 사용가능한 경우 --%>
		<p>[<%=checkId%>]는 사용가능합니다.</p>
		<input type="button" value="사용하기" onclick="setMemberId();"/>

	<% } else { %>
		<%-- 아이디를 사용불가한 경우 --%>
		<p>[<span id="duplicated"><%= checkId %></span>]는 이미 사용중입니다.</p>
		<form name="checkPopupp">
			<input type="text" name="checkId" placeholder="아이디를 입력하세요." />
			<input type="button" value="중복검사" onclick="checkIdDuplicate();" />
		</form>
	<% } %>
	</div>
<script>
/**
 * 사용가능한 아이디를 찾은경우 
 	1. 아이디를 부모윈도우의 #memberId_대입
 	2. #idValid값을 1로 변경
 */
	function setMemberId() {
		//부모의 윈도우opener
		var $sing_up=$(opener.document.sing_up);
		$sing_up.find("#memberId_").val('<%= checkId %>');
		$sing_up.find("#idValid").val(1);
		self.close();
		
	}
	/**
	 * 아이디 중복 검사함수
	 */
	function checkIdDuplicate(){
		var $memberId = $("[name=checkId]");
		if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
			alert("유효한 아이디를 입력해주세요.");
			$memberId.select();
			return;
		}
		
		$frm = $(document.checkPopupp);
		$frm.find("[name=checkId]").val($memberId.val());
		$frm.attr("action", "<%= request.getContextPath() %>/member/checkPopup")
			.attr("method", "POST")
			.submit();
	}
</script>
</body>