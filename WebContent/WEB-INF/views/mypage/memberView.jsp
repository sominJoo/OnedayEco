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
    <script>
        // 움직이는 효과   toggleclass  이벤트효과 
        $(document).ready(function () {
            $(".slide-menu").click(function () {
                $(".wrapper").toggleClass("collapse");
            });
        });
    </script>
</head>

<body>
    <div class="wrapper">
        <div class="top_navbar">
            <div class="slide-menu">
                <div class="one"></div>
                <div class="two"></div>
                <div class="three"></div>
            </div>
            <div class="top_menu">
                <div class="logo">
                    <a href="index.html"><img src="image/하루에코(메인로고).png" alt=""></a>

                </div>
                <ul>
                    <li><a href="#">
                            <i class="fas fa-search"></i></a></li>
                    <li><a href="#">
                            <i class="fas fa-bell"></i>
                        </a></li>
                </ul>
            </div>
        </div>

        <div class="sidebar">
            <ul>
                <li><a href="index.html">
                        <span class="icon"><i class="fas fa-bell"></i></span>
                        <span class="title">메인페이지</span></a></li>
                <li><a href="#">
                        <span class="icon"><i class="fas fa-address-card"></i></span>
                        <span class="title">로그인</span>
                    </a></li>
                <li><a href="#">
                        <span class="icon"><i class="fas fa-child"></i></span>
                        <span class="title">랭킹</span>
                    </a></li>

                <li><a href="MyPage(April).html">
                        <span class="icon"><i class="far fa-calendar"></i></span>
                        <span class="title">마이페이지</span>
                    </a></li>
                <li><a href="Community.html">
                        <span class="icon"><i class="fas fa-book-reader"></i></span>
                        <span class="title">커뮤니티</span>
                    </a></li>
                <li class="sidebar-menu"><a href="MyPage(April).html">
                        <span class="icon"><i class="fas fa-sign-out-alt"></i></span>
                        <span class="title">뒤로가기</span>
                    </a></li>
            </ul>
        </div>
        <div class="Container-bar">
            <ul>
                <li class="Container-bar-li-left" style="font-size: 30px;"><a href="#"><span class="title">회원 정보
                            보기</span></a>
                </li>
            </ul>
        </div>
        <div class="img">
            <img src="image/회원가입/회원가입.png" alt="">
            <h2 class="img-h2">MEMBER INFO</h2>
        </div>
    </div>
    <div class="Board">
        <hr class="Board-h3">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="Board-Name">이름</span></a></li>
        </ul>
        <div class="form">
            <input type="text" id="userName" placeholder="성명">
        </div>
    </div>
    <!-- 아이디 -->
    <div class="Board-1">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardId">아이디(변경불가)</span></a></li>
        </ul>
        <div class="form1">
            <input type="text" id="userId" placeholder="ID">
        </div>
    </div>
    <!-- 비밀번호-->
    <div class="Board-2">

        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardPassword">비밀번호</span></a></li>
        </ul>
        <div class="form2">
            <input type="text" id="userPassword" placeholder="PW">
        </div>
    </div>
    <!-- 닉네임-->
    <div class="Board-3">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardName">닉네임(변경불가)</span></a></li>
        </ul>
        <div class="form3">
            <input type="text" id="userName" placeholder="닉네임">
        </div>
    </div>
    <!-- 이메일-->
    <div class="Board-4">
        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">이메일</span></a></li>
        </ul>
        <div class="form4">
            <input type="text" id="userEmail" placeholder="example@page.com">
        </div>
    </div>
    <!-- 전화번호-->
    <div class="Board-5">

        <ul>
            <!--유효성 검사-->
            <li><a href=""> <span class="BoardEmail">전화번호</span></a></li>
        </ul>
        <div class="form5">
            <input type="text" id="userEmail" placeholder="ex)010-0000-0000">
        </div>
        <hr class="Board5-h3">
    </div>
</body>

</html>