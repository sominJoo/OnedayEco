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
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span class="title">회원 정보
                            수정</span></a>
                </li>
            </ul>
        </div>
        <div class="img">
            <img src="<%= request.getContextPath() %>/image/회원가입/회원가입.png" alt="">
            <h2 class="img-h2">CHANGE INFO</h2>
        </div>
    </div>	<!-- wrapper -->
    
    <div class="Board">
        <hr class="Board-h3">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="Board-Name">이름</span></a></li>
            <li> </li>
        </ul>
        <div class="form">
            <input type="text" id="userName" placeholder="성명" value="<%= loginMember.getMemberName() == null ? "" :loginMember.getMemberName()%>">
        </div>
    </div>
    <!-- 아이디 -->
    <div class="Board-1">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardId">아이디(변경불가)</span></a></li>
        </ul>
        <div class="form1">
            <!--readonly  속성 추가-->
            <input type="text" id="userId" placeholder="ID" value="<%= loginMember.getMemberId()%>" readonly>
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
            <!--readonly  속성 추가-->
            <input type="text" id="userNickname" placeholder="닉네임" value="<%= loginMember.getMemberNickname() == null ? "" :loginMember.getMemberNickname()%>" readonly>
        </div>
    </div>
    <!-- 이메일-->
    <div class="Board-4">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">이메일</span></a></li>
        </ul>
        <div class="form4">
            <input type="text" id="userEmail" placeholder="example@page.com"  value="<%= loginMember.getMemberEmail() == null ? "" :loginMember.getMemberEmail()%>">
        </div>
    </div>
    <!-- 전화번호-->
    <div class="Board-5">

        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">전화번호</span></a></li>
        </ul>
        <div class="form5">
            <input type="text" id="userPhone" placeholder="ex)010-0000-0000"  value="<%= loginMember.getMemberPhone() == null ? "" :loginMember.getMemberPhone()%>">
        </div>
        <hr class="Board5-h3">
    </div>
    <!--회원탈퇴,회원수정 버튼 추가 -->
    <div class="Board-6">
        <input type="button" value="비밀번호 수정"  id="Pw_Modify" onclick="pwModify_click();"/>
        <input type="submit" value="수정" id="Modify" onclick="modify_click()"/>
        <input type="button" value="탈퇴" onclick="delete_click();" id="Delete"/>
    </div>
    
    
    
    <form action="<%=request.getContextPath() %>/mypage/memberModify" name="memberModify" method= "post">
    	<input type="hidden" name="memberName" />
    	<input type="hidden" name="memberId" />
    	<input type="hidden" name="memberEmail" />
    	<input type="hidden" name="memberPhone" />
    </form>
    
    <form name="memberDelFrm" action="<%= request.getContextPath() %>/mypage/memberDelete" method="POST">
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
	</form>
    <script>
    	//비밀번호 수정 => page 변경 필요
    	function pwModify_click(){
    		//회원가입을 sns로 하면 불가
    		if(/^[NGK]\_/.test("<%= loginMember.getMemberId() %>")){
    			alert("SNS 회원은 비밀번호 변경 불가!");
    			return;
    		}
    		location.href="<%= request.getContextPath()%>/mypage/modifyPassword";
    	}
    	
    	//수정
		function modify_click(){
			if(confirm("수정 하시겠습니까?") == false){
				return false;
			}	
	
			$modifyFrm = $(document.memberModify);
			console.log($modifyFrm);
			$modifyFrm.find("[name=memberName]").val($("#userName").val()); 
			$modifyFrm.find("[name=memberId]").val($("#userId").val()); 
			$modifyFrm.find("[name=memberEmail]").val($("#userEmail").val()); 
			$modifyFrm.find("[name=memberPhone]").val($("#userPhone").val()); 
			$modifyFrm.attr("action","<%=request.getContextPath() %>/mypage/memberModify")
			.attr("method", "POST")
			.submit();

		};
    	//탈퇴
    	function delete_click(){
			if(confirm("정말 탈퇴 하시겠습니까?") == false){
				return false;
			}	
			$(document.memberDelFrm).submit();
    	}
    </script>
</body>

</html>