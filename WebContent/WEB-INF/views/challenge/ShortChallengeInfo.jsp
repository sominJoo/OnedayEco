<%@page import="challenge.model.vo.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Challenge challenge = (Challenge) request.getAttribute("challenge");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Challenge.css">
</head>
<body>
	<div id="shortchallengeform-container">
	<%= challenge.getChallenge_info() %>

</div>

</body>
</html>