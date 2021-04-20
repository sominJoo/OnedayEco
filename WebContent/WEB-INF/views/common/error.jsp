<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage ="true"%>
    
<%--
	isErrorPage 속성을 true로 지정하면,
	던져진 예외객체에 exception 키원드로 선언업시접근가능
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align : center">
	<h1>이용에 불편을 드려 죄송합니다.</h1>
	<p style="color : red;"> <%=exception.getMessage() %> </p>
</body>
</html>