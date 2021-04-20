<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보수정</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/Member_Sing_Up.css">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!--Alert 문구 디자인 추가 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span class="title">회원 정보
                            수정</span></a>
                </li>
            </ul>
        </div>
        <div class="img">
            <img src="<%=request.getContextPath()%>/image/회원가입/회원가입.png" alt="">
            <h2 class="img-h2">Join</h2>
        </div>
    </div>
    <form id="sing_up" action="<%=request.getContextPath()%>/login/member_sing_up"method="Post">
        <div class="Board">
            <hr class="Board-h3">
            <ul>
                <!--아이디-->
                <li><a href=""> <span class="Board-Name">아이디(변경불가)</span></a></li>
            </ul>
            <div class="form">
                <input type="text" name="memberId" id ="memberId_" placeholder="ID">
            </div>
        </div>
        <!-- 비밀번호-->
        <div class="Board-1">
            <ul>
                <!--유효성 검사-->
                <li><a href=""> <span class="BoardId">비밀번호</span></a></li>
            </ul>
            <div class="form1">
                <!--readonly  속성 추가-->
                <input type="text" name="memberPw" id="memberPw"placeholder="PW">
            </div>
        </div>
        <!-- 이름-->
        <div class="Board-2">

            <ul>
                <!--유효성 검사-->
                <li><a href=""> <span class="BoardPassword">이름</span></a></li>
            </ul>
            <div class="form2">
                <input type="text" id="memberName" name="memberName" placeholder="성명">
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
                <input type="text" id="memberNickname" name="memberNickname" placeholder="닉네임" >
            </div>
        </div>
        <!-- 이메일-->
        <div class="Board-4">
            <ul>
                <!--유효성 검사-->
                <li><a href=""> <span class="BoardEmail">이메일</span></a></li>
            </ul>
            <div class="form4">
                <input type="text" id="memberEmail" name="memberEmail" placeholder="example@page.com">
            </div>
        </div>
        <!-- 전화번호-->
        <div class="Board-5">

            <ul>
                <!--유효성 검사-->
                <li><a href=""> <span class="BoardPhone">전화번호</span></a></li>
            </ul>
            <div class="form5">
                <input type="text" id="memberPhone" name="memberPhone" placeholder="ex)010-0000-0000">
            </div>
            <hr class="Board5-h3">
        </div>
        <!--회원탈퇴,회원수정 버튼 추가 -->
        <div class="btn">
            <input type="submit" value="가입" >
        </div>
        <div class="btn1">
            <input type="button" value="취소" onclick="button1_click();" id="Delete">
        </div>
        <div class="btn2">
            <input type="button" value="중복검사" onclick="checkIdDuplicate();" />
            <input type="hidden" id="idValid" value="0" />
        </div>
    </form>


<script>
    <% if(msg != null) { %> 
    alert("<%= msg %>"); 
    <% } %>
    /**
     * 회원가입 유효성 검사
     */
     $(document.sing_up).submit(function(){
    	 //이름
    	 var $memberName = $("#memberName");
    		if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
    			alert("이름은 한글 2글자 이상이어야 합니다.");
    			$memberName.select();
    			return false;
    		}  	 
    	 //아이디 
    	var $memberId =$("#memberId");
    	//아이디는 영문자/숫자  4글자이상만 허용 
    	if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
    		alert("아이디는 최소 4자리이상이어야 합니다.");
    		$memberId.select();
    		return false;
    	}
    	//비밀번호
    	var $p1 = $("#memberPw");
    	if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
    		alert("유효한 패스워드를 입력하세요.");
    		$p1.select();
    		return false;
    	}
    	//닉네임
    	var $memberNickname =$("#memberNickname"); 
    if(/^[가-힣]{1,}$/.test($memberNickname.val()) == false){
    		alert("닉네임은 최소 1자리이상이어야 합니다.");
    		$memberNickname.select();
    		return false;
    }
  //phone
	var $memberPhone = $("#memberPhone");
	//-제거하기
	$memberPhone.val($memberPhone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	
	if(/^010[0-9]{8}$/.test($memberPhone.val()) == false){
		alert("유효한 전화번호가 아닙니다.");
		$memberPhone.select();
		return false;
	}
    	return true;
     });
    </script>
</body>

</html>