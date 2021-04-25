<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보보기</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/Member_View.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    
	<%@ include file ="/WEB-INF/views/common/header.jsp" %>
	<% 
		//PW 그대로 노출 x
		String pw = loginMember.getMemberPw();
		String pw_ = "";
		if(pw !=null){
			int pwLength = pw.length();
			
			List<String> pwArr = new ArrayList<>();
			
			for(int i =0 ;i < pwLength;i++){
				pwArr.add("*");
			}
			for(String s : pwArr){
				pw_ += s; 
			}
		}
		System.out.println("pw_ : "+pw_);

	%>
        <div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span class="title">회원 정보
                            보기</span></a>
                </li>
            </ul>
        </div>
        <div class="img">
            <img src="<%= request.getContextPath()%>/image/회원가입/회원가입.png" alt="">
            <h2 class="img-h2">MEMBER INFO</h2>
        </div>
    </div> <!-- wrapper -->
    
    
    
    <div class="Board">
        <hr class="Board-h3">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="Board-Name">이름</span></a></li>
        </ul>
        <div class="form">
            <input type="text" id="userName" placeholder="성명" value="<%= loginMember.getMemberName() == null ? "" :loginMember.getMemberName()%>" readonly>
        </div>
    </div>
    <!-- 아이디 -->
    <div class="Board-1">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardId">아이디(변경불가)</span></a></li>
        </ul>
        <div class="form1">
            <input type="text" id="userId" placeholder="ID"  value="<%= loginMember.getMemberId()%>" readonly>
        </div>
    </div>
    <!-- 비밀번호-->
    <div class="Board-2">

        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardPassword">비밀번호</span></a></li>
        </ul>
        <div class="form2">
            <input type="text" id="userPassword" placeholder="PW"  value="****" readonly>
        </div> 
    </div>
    <!-- 닉네임-->
    <div class="Board-3">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardName">닉네임(변경불가)</span></a></li>
        </ul>
        <div class="form3">
            <input type="text" id="userName" placeholder="닉네임" value="<%= loginMember.getMemberNickname() == null ? "" :loginMember.getMemberNickname()%>" readonly>
        </div>
    </div>
    <!-- 이메일-->
    <div class="Board-4">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">이메일</span></a></li>
        </ul>
        <div class="form4">
            <input type="text" id="userEmail" placeholder="example@page.com" value="<%= loginMember.getMemberEmail() == null ? "" :loginMember.getMemberEmail()%>" readonly>
        </div>
    </div>
    <!-- 전화번호-->
    <div class="Board-5">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">전화번호</span></a></li>
        </ul>
        <div class="form5">
            <input type="text" id="userEmail" placeholder="ex)010-0000-0000" value="<%= loginMember.getMemberPhone() == null ? "" :loginMember.getMemberPhone()%>" readonly>
        </div>
        <hr class="Board5-h3">
    </div>
    
</body>
</html>