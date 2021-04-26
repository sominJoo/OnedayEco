<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <meta charset="utf-8">
    <title>환경정보5</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Community.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ include file ="/WEB-INF/views/common/header.jsp" %>
<%@ include file ="/WEB-INF/views/common/containerBar.jsp"%>

 		<div class="VEGAN">
            <a href="<%=request.getContextPath() %>/community/enviroment4"> <img src="<%=request.getContextPath() %>/image/커뮤니티/전기.png" alt="" style="width: 40vw"></a>
            <h1 style="font-style: italic; color: #00008B;">전기를 전달하기위해 지구가 희생하는 것</h1>
        </div>
 <div class="img"></div>
       	<div class="Paging">
            <a href="#">
                <i class="fas fa-chevron-circle-left" style="font-size: 30px; color: #00008B;"></i></a>
            <a href="<%=request.getContextPath()%>/community/community">
                <h3>1</h3>
            </a>
            <a href="<%=request.getContextPath()%>/community/community2">
                <h3>2</h3>
            </a>
            <a href="<%=request.getContextPath()%>/community/community3">
                <h3>3</h3>
            </a>
            <a href="<%=request.getContextPath()%>/community/community4">
                <h3>4</h3>
            </a>
            <a href="<%=request.getContextPath()%>/community/community5">
                <h3>5</h3>
            </a>
            <a href="#"><i class="fas fa-chevron-circle-right" style="font-size: 30px; color: #00008B;;"></i></a>
        </div>
    </div>
</body>
</html>