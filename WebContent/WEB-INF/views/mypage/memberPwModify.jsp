<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보수정</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Member_Modify.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!--Alert 문구 디자인 추가 -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="<%=request.getContextPath() %>/js/Modify.js"></script>

	<%@ include file ="/WEB-INF/views/common/header.jsp" %> 
		<div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span class="title">비밀번호  수정</span></a>
                </li>
            </ul>
        </div>
        <div class="img">
            <img src="<%= request.getContextPath() %>/image/회원가입/회원가입.png" alt="">
            <h2 class="img-h2">CHANGE INFO</h2>
        </div>
    </div>	<!-- wrapper -->
    
    
	<form 
		name="updatePwdFrm" 
		action="<%=request.getContextPath()%>/mypage/modifyPassword" 
		method="POST" >
	    <div class="Board-1">
	        <hr class="Board-h3">
	        <ul>
	            <!--유효성 검사-->
	            <li><a href=""> <span class="">현재 비밀번호</span></a></li>
	            <li> </li>
	        </ul>
	        <div class="form">
	            <input type="password" name="current-password" id="current-password"  required />
	        </div>
	    </div>
	    <!-- 아이디 -->
	    <div class="Board-1">
	      	<ul>
	            <!--유효성 검사-->
	            <li><a href=""> <span class="">새로운 비밀번호</span></a></li>
	            <li> </li>
	        </ul>
	        <div class="form">
	            <input type="password" name="newPassword" id="newPassword" required />
	        </div>
	    </div>
	    <!-- 비밀번호-->
	    <div class="Board-1">
	      	<ul>
	            <!--유효성 검사-->
	            <li><a href=""> <span class="">비밀번호 확인</span></a></li>
	            <li> </li>
	        </ul>
	        <div class="form">
	            <input type="password" name="passwordCheck" id="passwordCheck" required />
	        </div>
	    </div>
	    
	    <div class="Board-1 Board-6">
        	<input type="submit" value="비밀번호 수정"/>
    	</div>
	</form>


<script>
$(document.updatePwdFrm).submit(function(){
	<%System.out.println("수정 submit");%>
	<%System.out.println(""+loginMember.getMemberPw());%>
	
	console.log("수정");

	var $p0 = $("#current-password");
	if(<%= loginMember.getMemberPw()%> != $p0.val()){
		alert("비밀번호가 동일하지 않습니다.");
		$p0.select();
		return false;
	}
	
	
	//password
	var $p1 = $("#newPassword");
	var $p2 = $("#passwordCheck");
	if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
		alert("유효한 패스워드를 입력하세요.");
		$p1.select();
		return false;
	}
	
	if($p1.val() != $p2.val()){
		alert("패스워드가 일치하지 않습니다.");
		$p1.select();
		return false;
	}
});
	

</script>
</body>
</html>