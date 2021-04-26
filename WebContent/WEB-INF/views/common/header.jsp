<%@page import="member.model.vo.*"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");	
	
	Member loginMember =  (Member)session.getAttribute("loginMember");
	System.out.println("session LoginMember = "+ loginMember);
	
	
	%>
      <script>
        // 움직이는 효과   toggleclass  이벤트효과 
        $(document).ready(function () {
            $(".slide-menu").click(function () {
                $(".wrapper").toggleClass("collapse");
            });
        });
        <% if(msg != null) { %> 
    	alert("<%= msg %>"); 
    <% } %>
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
                <div class="logo"><img src="<%=request.getContextPath() %>/image/하루에코(메인로고).png" alt=""><a href="<%=request.getContextPath()%>"></a></div>
                <ul>
                	<%if(loginMember !=null) { %>
                        <li id="loginMember-Nickname">
                            <i class="fas fa-user">
                                <%= loginMember.getMemberNickname()%>님
                            </i>
                        </li>
                    <%} %>
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
               <li><a href="<%= request.getContextPath() %>/challenge/ShortChallenge">
                        <span class="icon"><i class="fas fa-bell"></i></span>
                        <span class="title">도전 챌린지</span></a></li>
                <li><a href="<%=request.getContextPath()%>/login/login">
                        <span class="icon"><i class="fas fa-address-card"></i></span>
                        <span class="title">로그인</span>
                    </a></li>
                <li><a href="<%= request.getContextPath() %>/ranking">
                        <span class="icon"><i class="fas fa-child"></i></span>
                        <span class="title">랭킹</span>
                    </a></li>
                <li><a href="<%=request.getContextPath()%>/mypage">
                        <span class="icon"><i class="far fa-calendar"></i></span>
                        <span class="title">마이페이지</span>
                    </a></li>
                <li><a href="<%=request.getContextPath()%>/community/community">
                        <span class="icon"><i class="fas fa-book-reader"></i></span>
                        <span class="title">커뮤니티</span>
                    </a></li> 
            </ul>
        </div>